/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.LibCore.NiconTwitt;

import java.io.Serializable;

/**
 * Esta clase es la encargada de crear objetos con informacion basica de la cuenta de el usuario
 * tales como la cantidad de amigos, seguidores, numero de twetts enviados, el lenguaje configurado.
 * es usada para mostrar los datos en pantalla al usuario.
 * @author frederick
 */
public class InformationTwitterAccount implements Serializable{
    
    private int friends;
    private int followers;
    private int favorites;
    private int statuses;
    private String Languaje;

    /**
     * 
     * @param friends
     * @param followers
     * @param favorites
     * @param statuses
     * @param Languaje
     */
    public InformationTwitterAccount(int friends, int followers, int favorites, int statuses, String Languaje) {
        this.friends = friends;
        this.followers = followers;
        this.favorites = favorites;
        this.statuses = statuses;
        this.Languaje = Languaje;
    }
    
    public InformationTwitterAccount(){
        
    }

    /**
     * 
     * @return
     */
    public String getLanguaje() {
        return Languaje;
    }

    /**
     * 
     * @param Languaje
     */
    public void setLanguaje(String Languaje) {
        this.Languaje = Languaje;
    }

    /**
     * 
     * @return
     */
    public int getFavorites() {
        return favorites;
    }

    /**
     * 
     * @param favorites
     */
    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    /**
     * 
     * @return
     */
    public int getFollowers() {
        return followers;
    }

    /**
     * 
     * @param followers
     */
    public void setFollowers(int followers) {
        this.followers = followers;
    }

    /**
     * 
     * @return
     */
    public int getFriends() {
        return friends;
    }

    /**
     * 
     * @param friends
     */
    public void setFriends(int friends) {
        this.friends = friends;
    }

    /**
     * 
     * @return
     */
    public int getStatuses() {
        return statuses;
    }

    /**
     * 
     * @param statuses
     */
    public void setStatuses(int statuses) {
        this.statuses = statuses;
    }

    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return "BasicDataNiconTwittAcount{" + "friends=" + friends + ", followers=" + followers + ", favorites=" + favorites + ", statuses=" + statuses + ", Languaje=" + Languaje + '}';
    }
    
    
    
    
    
}
