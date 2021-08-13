/*
 * Modèle représentant un événement du fils RSS
 */
package com.alefebvre.inf1420;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;  

/**
 *
 * @author Alek
 */
public class ModelEvenement {
    protected String titre;
    protected String description;
    protected String lien;
    protected String date;
    
    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }
    
    public String getLien() {
        return lien;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setLien(String lien) {
        this.lien = lien;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public Object clone() throws CloneNotSupportedException {
        ModelEvenement clone = new ModelEvenement();
        clone.date = this.date;
        clone.titre = this.titre;
        clone.description = this.description;
        clone.lien = this.lien;
        return clone;
    }
}
