/**
 * CopyRigth (C) 2013 NiconSystem Incorporated. 
 * 
 * NiconSystem Inc.
 * Cll 9a#6a-09 Florida Valle del cauca Colombia
 * 318 437 4382
 * fredefass01@gmail.com
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */

package org.Nicon.Personal.LibCore.NiconTwitt;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.Sbin.NiconFileAdministrator;

import twitter4j.AccountSettings;
import twitter4j.DirectMessage;
import twitter4j.ResponseList;
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
    
    private final static String PATH_CONFIG_FILE="./Config/TCA.npc";
    
    private static ResponseList messages;
    private static DirectMessage SendMessage;

    private RequestToken requestToken;
    private AccessToken accesToken;
    private static Twitter twitter;
    private TwitterFactory factory;
    private ConfigurationBuilder configuration;    
    private AccountSettings settings;
    private User user;
    
    private NiconFileAdministrator NiconFileAdmin;
    private InformationTwitterAccount basicDataAccount;
    private TwitterConfigAcount tca;
        
    private static boolean stateOP;
    private Iterator iter;
    
    private int controlAcces=0;

    /**
     * 
     */
    public NiconTwitt() throws TwitterException, IOException, URISyntaxException {
        NiconFileAdmin = new NiconFileAdministrator();
    }
    
     /**
     * Este metodo se encarga de obtener la configuracion de el AccesToken para
     * poder conectarse con el servicio de twiiter, en caso de no encontrar el archivo
     * de configuracion llama al metodo encargado de obtener la autorizacion del usuario
     * para el uso de su cuenta twitter. 
     */    
    public void accesTwitterAcount() throws TwitterException, IOException, URISyntaxException {
        
        /**
         * se verifica la existencia el archivo Twitter config Acount que define la configuracion de acceso
         * a la cuenta de Twitter definida posteriormente, en caso de que dicho archivo no se encuentre en 
         * el path de configuraciones, se procede a obtener la autorizacion del usuario para el acceso a su
         * cuenta.
         */
        if(controlAcces==0){
           if (NiconFileAdmin.verifyFileExistSimple("TCA.npc", "./Config")) {
                tca=(TwitterConfigAcount) NiconFileAdmin.readFileObject(PATH_CONFIG_FILE);
                accesToken=tca.getDataAccessToken();
                    configuration = new ConfigurationBuilder();
                    configuration.setDebugEnabled(true);
                    configuration.setOAuthConsumerKey(GlobalConfigSystem.getCONSUMER_KEY());
                    configuration.setOAuthConsumerSecret(GlobalConfigSystem.getCONSUMER_SECRET());
                    configuration.setOAuthAccessToken(accesToken.getToken());
                    configuration.setOAuthAccessTokenSecret(accesToken.getTokenSecret());
                    factory = new TwitterFactory(configuration.build());
                    twitter = factory.getInstance();
                    controlAcces++;
            } else {
                loginTwitterAccount();
            }    
        }               
    }

    /**
     * Este metodo permite que un usuario administrador de NiconPersonal pueda darle permisos a NiconPersonal para 
     * usar su informacion de twitter (logea a NiconPersonal con su cuenta de twitter, loginTwitterAccount() hace 
     * uso de la libreria Twitter4j  que usa el API de Twitter, este proceso hace uso del CONSUMER_KEY y
     * CONSUMER_SECRET para obtener la configuracion basica de NiconPersonal para Twitter, posteriormente y haciendo 
     * uso de OAuth obtiene el denominado token y el tokenScret que serán usados en la configuracion de acceso al
     * sistema
     * 
     * @throws TwitterException
     * @throws IOException
     * @throws URISyntaxException 
     */
    private void loginTwitterAccount() throws TwitterException, IOException, URISyntaxException {       
            configuration = new ConfigurationBuilder();
            configuration.setDebugEnabled(true);
            configuration.setOAuthConsumerKey(GlobalConfigSystem.getCONSUMER_KEY());
            configuration.setOAuthConsumerSecret(GlobalConfigSystem.getCONSUMER_SECRET());
            twitter = new TwitterFactory(configuration.build()).getInstance();
            requestToken = twitter.getOAuthRequestToken();
            Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
            String Pin = JOptionPane.showInputDialog("Ingrese por favor el pin obtenido:");
            accesToken = twitter.getOAuthAccessToken(requestToken, Pin);
            tca = new TwitterConfigAcount(accesToken);
            NiconFileAdmin = new NiconFileAdministrator(tca, "./Config", "TCA.npc");
            NiconFileAdmin.writeFileObject();               
    }

    /**
     * este metodo permite actualizar el estado en twitter de la cuenta del usuario propietario
     * hace uso de ConfigurationBuider para hacer la configuracion de login con el AccesToken
     * capturado desde el archivo de configruacion de cuentas en ./Config
     * 
     * @param status
     * @return  
     */
    public  boolean updateStatus(String status) throws TwitterException {
        if (status != null) {            
                twitter.updateStatus(status);
                stateOP = true;            
        }
        return stateOP;
    }
    
    /**
     * permite eliminar un status de su cuenta de twitter.
     * @param id
     * @return
     * @throws TwitterException 
     */
    public  boolean deleteStatus(long id) throws TwitterException{        
            twitter.destroyStatus(id);
            stateOP=true;        
        return stateOP;
    }
    
    /*
     * Este metodo permite enviar un mensaje directo a un usuario de twitter, recibe como
     * parametros el userName y el Mensaje a enviar.
     */
    public boolean sendDirectMessage(String UserName,String Message) throws TwitterException{
            SendMessage = twitter.sendDirectMessage(UserName, Message);
            SendMessage.getId();
            SendMessage.getRecipient();
            stateOP=true;
            System.out.println("Message Details: \n"+SendMessage.getId()+"/ "+SendMessage.getCreatedAt()+" / "+SendMessage.getSenderScreenName()+"\n"+SendMessage.getText());
          
        return stateOP;
    }
    
    /**
     * este metodo permite obtener un listado con todos los mensajes directos que el usuario ha recibido en su cuenta.
     * @return 
     */
    public ResponseList getSentDirectMessage() throws TwitterException{        
            messages = twitter.getSentDirectMessages();                   
            return messages;
    }

    /**
     * Este metodo se encarga de obtener la informacion basica de la cuenta del usuario, informacion
     * basica que puede ser mostrada en el panel de cuentas de NiconTwitt la informacion obtenida
     * es acerca de cuantos seguidores posee un determinado usuario, cuantos amigos tiene, cuantos
     * favoritos a marcado entre otros.
     * 
     * @Author Frederick Adolfo Salazar Sanchez
     * @return ArrayList con todos los datos obtenidos de la cuenta
     *
     */
    public InformationTwitterAccount getInformationUserAccount() throws TwitterException {        
            settings=twitter.getAccountSettings();
            user = twitter.verifyCredentials();
            basicDataAccount=new InformationTwitterAccount(user.getFriendsCount(),user.getFollowersCount(),user.getFavouritesCount(),user.getStatusesCount(),user.getLang(),user.getScreenName(),user.getURL());
            basicDataAccount.saveInformationTwitterAccount();        
        return basicDataAccount;
    }
    
    private InformationTwitterAccount getLocalDataAccount(){
        System.out.println("No se puede cargar Informacion de www.Twitter.com API");
        System.out.println("Cargando datos locales ...");
        basicDataAccount = basicDataAccount.readInformationTwitterAccount();
        return basicDataAccount;
    }

   
}
