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
public class AjouterCommand implements Command {
    
    protected ModelFilsRSS fils;
    protected ModelEvenement evenement;
    protected int row;
    
    public AjouterCommand(ModelFilsRSS fils, ModelEvenement evenement, int row) {
        this.row = row;
        this.fils = fils;
        try {
            this.evenement = (ModelEvenement)evenement.clone();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void ajouter() {
        this.fils.ajouterEvenement(evenement);
    }

    @Override
    public void annuler() {                
        this.fils.supprimerEvenement(this.row);     
    }

    @Override
    public void refaire() {
        ajouter();
    }
}
    