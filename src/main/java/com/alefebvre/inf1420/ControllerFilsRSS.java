package com.alefebvre.inf1420;
import java.util.Stack;

/**
 * Controlleur du fils RSS
 * @author Alek
 */
public class ControllerFilsRSS {
      
    protected AccueilJFrame accueilJFrame;
    protected ModelFilsRSS fils;
    protected ModelListeEvenementsTable modelListeEvenementsTable;
    Stack<Command> commands = new Stack<>();
    Stack<Command> refaireCommands = new Stack<>();
    
    //Constructeurs avec et sans modèle existant
    public ControllerFilsRSS() {
        this.fils = new ModelFilsRSS();
        this.modelListeEvenementsTable = new ModelListeEvenementsTable(fils);
    }
    
    public ControllerFilsRSS(ModelFilsRSS fils, ModelListeEvenementsTable modelListeEvenementsTable, AccueilJFrame accueilJFrame) {
        this.fils = fils;
        this.modelListeEvenementsTable = modelListeEvenementsTable;
        this.accueilJFrame = accueilJFrame;
    }
    
    //Ajouter un événement. Usage du template Command pour pouvoir annuler/refaire.
    public void ajouterEvenement(ModelEvenement evenement) {
        Command command = new AjouterCommand(this.fils, evenement, this.modelListeEvenementsTable.getRowCount());
        ((AjouterCommand)command).ajouter();
        commands.push(command);
        this.accueilJFrame.setEnableAnnuler(true);
        this.modelListeEvenementsTable.fireTableDataChanged();
    }
    
    //Modifications d'un événement
    public void modifierEvenement(ModelEvenement evenement, int row) {
        this.fils.modifierEvenement(evenement, row);
        this.modelListeEvenementsTable.fireTableDataChanged();
    }
    
    //Supprimer un événement. Usage du template Command pour pouvoir annuler/refaire.
    public void supprimerEvenement(int row) {
        Command command = new SupprimerCommand(this.fils, row);
        ((SupprimerCommand)command).supprimer();
        commands.push(command);
        this.accueilJFrame.setEnableAnnuler(true);
        this.modelListeEvenementsTable.fireTableDataChanged();
    }
    
    //Ouvrir un fils RSS
    public void ouvrir(String fichier) {
        this.fils.ouvrir(fichier);
        this.modelListeEvenementsTable.fireTableDataChanged();
    }
    
    //Sauvegarder le fils RSS courant
    public void enregistrer(String fichier) {
        this.fils.enregistrer(fichier);
    }
    
    //Annules la dernière action
    public void annuler() {
        //Pris de la semaine 12 du cours
        //Annuler la commande en prenant le premier item de la pile de commands
        Command command = commands.pop();
        command.annuler();
        
        //Mettre la command annulée dans la pile de command à refaire
        refaireCommands.push(command);
                
        //S'il n'y a plus de command, rendre l'option inaccessible
        if(commands.size() == 0) {
            accueilJFrame.setEnableAnnuler(false);
        }
        
        //rende l'option refaire disponible
        accueilJFrame.setEnableRefaire(true);
    }
    
    //Refaire la dernière action annulée
    public void refaire() {
        //Pris de la semaine 12 du cours
        // Refaire la command qui a été annulée
        Command command = refaireCommands.pop();
        command.refaire();
        
        // Remettre dans la liste des items à annuler
        commands.push(command);
        //S'il n'y a plus de command, rendre l'option inaccessible
        if(refaireCommands.size() == 0) {
            accueilJFrame.setEnableRefaire(false);
        }
        
        accueilJFrame.setEnableAnnuler(true);
    }
    
}
