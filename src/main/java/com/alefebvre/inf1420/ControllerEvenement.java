package com.alefebvre.inf1420;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Controlleur des événements du fils RSS
 * @author Alek
 */
public class ControllerEvenement {
    
    protected ModelEvenement evenement;
    
    // Constructeurs avec et sans modèle existant
    public ControllerEvenement() {
        this.evenement = new ModelEvenement();
    }
    
    public ControllerEvenement(ModelEvenement evenement) {
        this.evenement = evenement;
    }
    
    //Modifications du modèle 
    public void setTitre(String titre) {
        evenement.setTitre(titre);
    }
    
    public void setDescription(String description) {
        evenement.setDescription(description);
    }
    
    public void setLien(String lien) {
        evenement.setLien(lien);
    }
    
    public void setDate(String date) {
        evenement.setDate(date);
    }
    
    public ModelEvenement getEvenement() {
        return evenement;
    }
    
}
