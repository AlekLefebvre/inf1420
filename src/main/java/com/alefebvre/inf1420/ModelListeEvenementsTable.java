package com.alefebvre.inf1420;
import javax.swing.table.AbstractTableModel;
import javax.swing.JButton;
/**
 * Modèle pour remplir le tableau d'événement sur la page d'accueil.
 * Contient un ModelFilsRSS et un array avec le nom des colonnes.
 * @author Alek
 */


public class ModelListeEvenementsTable extends AbstractTableModel {
  private ModelFilsRSS fils;
  private String[] columns ; 

  // Constructeur. On commence avec un fils RSS vide.
  public ModelListeEvenementsTable(ModelFilsRSS fils){
    super();
    this.fils = fils ;
    columns = new String[]{"Titre","Description","Lien", "Date"};
  }

  // Nombre de colonnes
  public int getColumnCount() {
    return columns.length ;
  }

  // Nombre de rangées
  public int getRowCount() {
    return fils.getListeEvenements().size();
  }

  // Retourne l'information à afficher dans chaque cellule
  public Object getValueAt(int row, int col) {
    ModelEvenement evenement = fils.getListeEvenements().get(row);
    
    switch(col) {
      case 0: return evenement.getTitre();
      case 1: return evenement.getDescription();
      case 2: return evenement.getLien();
      case 3: return evenement.getDate();
      default: return null;
    }
  }

  // Optional, the name of your column
  public String getColumnName(int col) {
    return columns[col] ;
  }
  
  public void setFilsRSS(ModelFilsRSS fils) {
      this.fils = fils;
  }

}
