/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.LibCore.NiconTwitt;

import java.io.Serializable;
import twitter4j.auth.AccessToken;

/**
 * Esta clase permite serializar el objeto de tipo AccesToken para la configuracion de NiconTwitt.
 * 
 * @author frederick
 */
public class TwitterConfigAcount implements Serializable {
    
    private AccessToken dataAccessToken;
    
    public TwitterConfigAcount(AccessToken dataAccesToken){
        this.dataAccessToken=dataAccesToken;
    }

    public AccessToken getDataAccessToken() {
        return dataAccessToken;
    }    
}
