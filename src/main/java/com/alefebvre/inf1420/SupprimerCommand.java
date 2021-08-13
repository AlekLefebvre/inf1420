/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alefebvre.inf1420;

/**
 *
 * @author Alek
 */
public class SupprimerCommand implements Command {
    
    protected ModelFilsRSS fils;
    protected ModelEvenement evenement;
    protected int row;
    
    public SupprimerCommand(ModelFilsRSS fils, int row) {
        this.row = row;
        this.fils = fils;
    }
    
    public void supprimer() {
        ModelEvenement evenement = this.fils.supprimerEvenement(this.row);
        try {
            this.evenement = (ModelEvenement)evenement.clone();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void annuler() {                
        this.fils.ajouterEvenement(this.evenement);  
    }

    @Override
    public void refaire() {
        supprimer();
    }
}
    