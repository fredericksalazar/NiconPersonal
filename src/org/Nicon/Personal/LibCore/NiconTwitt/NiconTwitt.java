/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.LibCore.NiconTwitt;

import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.Sbin.NiconFileAdministrator;
import twitter4j.AccountSettings;
import twitter4j.AccountTotals;
import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * NiconTwitt es el cliente oficial de NiconPersonal para Twitter. esta clase define los metodos de configuracion
 * metodos de acceso y de publicacion de estados. 
 * esta clase pertenece al nucleo NiconLibCore y no tiene contacto alguno con el usuario final. 
 * @author Frederick Adolfo Salazar Sanchez
 * 
 */
public class NiconTwitt {

    private RequestToken requestToken;
    private AccessToken accesToken;
    private NiconFileAdministrator NiconFileAdmin;
    private InformationTwitterAccount basicDataAccount;
    private TwitterConfigAcount tca;
    private static Twitter twitter;
    private TwitterFactory factory;
    private ConfigurationBuilder configuration;
    private static boolean stateOP;
    private AccountTotals totals;
    private AccountSettings settings;
    private ArrayList contactsTwitter;
    private Iterator iter;

    /**
     * 
     */
    public NiconTwitt() {
        NiconFileAdmin = new NiconFileAdministrator();
        getConfigurationAccount();
        getInformationUserAccount();
    }

    /**
     * Este es el metodo que permite el registro y Login de un usuario de NiconPersonal a la API
     * de twitter. hace uso de la libreria Twitter4J que a su vez implementa el protocolo de 
     * registro denominado OAuth. toma la configracion proveniente de Api.twiiter.com y la guarda
     * en el archivo de configuracion de cuentas en ./Config
     * 
     */
    public void loginTwitterAccount() {
        
        try {
            configuration = new ConfigurationBuilder();
            configuration.setDebugEnabled(true);
            configuration.setOAuthConsumerKey(GlobalConfigSystem.getCONSUMER_KEY());
            configuration.setOAuthConsumerSecret(GlobalConfigSystem.getCONSUMER_SECRET());
            twitter = new TwitterFactory(configuration.build()).getInstance();
            requestToken = twitter.getOAuthRequestToken();
            System.out.println("Obtenido request token.");
            System.out.println("Request token: " + requestToken.getToken());
            System.out.println("Request token secret: " + requestToken.getTokenSecret());
            Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
            String Pin = JOptionPane.showInputDialog("Ingrese por favor el pin obtenido:");
            accesToken = twitter.getOAuthAccessToken(requestToken, Pin);
            System.out.println("Obtenido el access token.");
            System.out.println("Access token: " + accesToken.getToken());
            System.out.println("Access token secret: " + accesToken.getTokenSecret());
            TwitterConfigAcount tca = new TwitterConfigAcount(accesToken);
            NiconFileAdmin = new NiconFileAdministrator(tca, "./Config", "TCA.npc");
            NiconFileAdmin.writeFileObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * este metodo permite actualizar el estado en twitter de la cuenta del usuario propietario
     * hace uso de ConfigurationBuider para hacer la configuracion de login con el AccesToken
     * capturado desde el archivo de configruacion de cuentas en ./Config
     * 
     * @param status
     * @return  
     */
    public boolean updateStatus(String status) {
        if (status != null) {
            try {
                Status updateStatus = twitter.updateStatus(status);
                stateOP = true;
            } catch (TwitterException ex) {
                Logger.getLogger(NiconTwitt.class.getName()).log(Level.SEVERE, null, ex);
                stateOP = false;
            }
        }
        return stateOP;
    }
    
    public static boolean deleteStatus(long id){
        try{
            Status destroyStatus = twitter.destroyStatus(id);
            stateOP=true;
        }catch(Exception e){
            System.out.println("Ocurrio un error en NiconTwitt.deleteStatus(Long id) detail"+e.getStackTrace());
            stateOP=false;
            
        }
        return stateOP;
    }
    
    /*
     * Este metodo permite enviar un mensaje directo a un usuario de twitter, recibe como
     * parametros el userName y el Mensaje a enviar.
     */
    public static boolean sendDirectMessage(String UserName,String Message){
        try{
            System.out.println("Inciando evio de mensaje a twitter ...");
            DirectMessage SendMessage = twitter.sendDirectMessage(UserName, Message);
            System.out.println("Mensaje enviado correctamente ...");
            SendMessage.getId();
            SendMessage.getRecipient();
            stateOP=true;
            System.out.println("Message Details: \n"+SendMessage.getId()+"/ "+SendMessage.getCreatedAt()+" / "+SendMessage.getSenderScreenName()+"\n"+SendMessage.getText());
          }catch(Exception e){
            System.out.println("Ocurrio el siguiente error y el mensaje no se envio: \n"+ e);
            stateOP=false;
        }
        return stateOP;
    }

    /**
     * 
     */
    public void getTimeLine() {
        try {
            int index=0;
            ResponseList<twitter4j.Status> homeTimeline = twitter.getHomeTimeline();            
        } catch (Exception ex) {
            Logger.getLogger(NiconTwitt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getContactList(){
        try{
           
        }catch(Exception e){
              e.printStackTrace();      
        }
//        return contactsTwitter;
    }
    
    public boolean searchUser(String email){
        try{
            ResponseList<User> searchUsers = twitter.searchUsers(email, 20);
                if(searchUsers!=null){
                User get = searchUsers.get(0);
                System.out.println(get.getName());
                }
        }catch(Exception e){
            e.printStackTrace();
        }
        return stateOP;
    }

    /**
     * Este metodo se encarga de obtener la informacion basica de la cuenta del usuario, informacion
     * basica que puede ser mostrada en el panel de cuentas de NiconTwitt. la informacion obtenida
     * es acerca de cuantos seguidores posee un determinado usuario, cuantos amigos tiene, cuantos
     * favoritos a marcado entre otros.
     * 
     * @Author Frederick Adolfo Salazar Sanchez
     * @return ArrayList con todos los datos obtenidos de la cuenta
     *
     */
    public InformationTwitterAccount getInformationUserAccount() {
        try {
            settings=twitter.getAccountSettings();
            User user = twitter.verifyCredentials();
            int friendsCount = user.getFriendsCount();
            int followersCount = user.getFollowersCount();
            int favouritesCount = user.getFavouritesCount();
            int statusesCount = user.getStatusesCount();
            String lang = user.getLang();
            String screenName = user.getScreenName();
            String uRL = user.getURL();
            basicDataAccount=new InformationTwitterAccount(friendsCount,followersCount,favouritesCount,statusesCount,lang,screenName,uRL);
            basicDataAccount.saveInformationTwitterAccount();
            System.out.println(basicDataAccount.toString());
        } catch (Exception e) {
            
        } 
        return basicDataAccount;
    }
    
    private InformationTwitterAccount getLocalDataAccount(){
        System.out.println("No se puede cargar Informacion de www.Twitter.com API");
        System.out.println("Cargando datos locales ...");
        basicDataAccount = basicDataAccount.readInformationTwitterAccount();
        return basicDataAccount;
    }

    /*
     * Este metodo se encarga de obtener la configuracion de el AccesToken para
     * poder conectarse con el servicio de twiiter. en caso de no encontrar el archivo
     * de configuracion llama al metodo encargado de obtener la autorizacion del usuario
     * para el uso de su cuenta twitter.
     * 
     * 
     */
    public void getConfigurationAccount() {
        try {            
            System.out.println("Comprobando configuracion de Acceso a cuenta de twiter ...");
            if (NiconFileAdmin.verifyFileExistSimple("TCA.npc", "./Config")) {
                tca=(TwitterConfigAcount) NiconFileAdmin.readFileObject("./Config/TCA.npc");
                accesToken=tca.getDataAccessToken();
                configuration = new ConfigurationBuilder();
                configuration.setDebugEnabled(true);
                configuration.setOAuthConsumerKey(GlobalConfigSystem.getCONSUMER_KEY());
                configuration.setOAuthConsumerSecret(GlobalConfigSystem.getCONSUMER_SECRET());
                configuration.setOAuthAccessToken(accesToken.getToken());
                configuration.setOAuthAccessTokenSecret(accesToken.getTokenSecret());
                factory = new TwitterFactory(configuration.build());
                twitter = factory.getInstance();
                System.out.println("Configuracion terminada exitosamente ....");
            } else {
                loginTwitterAccount();
            }
        }  catch(NullPointerException ne){
                System.out.println("El AccesToken hesta deteriorado, se cargara uno nuevo ...");
                loginTwitterAccount();
            }
    }
}
