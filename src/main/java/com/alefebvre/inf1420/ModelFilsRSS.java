package com.alefebvre.inf1420;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
/**
 * Controlleur pour le fils RSS.
 * Garde la liste d'événements courants, permet de la modifier, de la sauvegarder ou d'en ouvrir une nouvelle.
 * @author Alek
 */
public class ModelFilsRSS {
    
    protected ArrayList<ModelEvenement> listeEvenements = new ArrayList<ModelEvenement>();
    
    //Permet d'accéder à la liste par d'autres classes
    public ArrayList<ModelEvenement> getListeEvenements() {
        return listeEvenements;
    }

    //Permet d'assigner une nouvelle liste
    public void setListeEvenements(ArrayList<ModelEvenement> listeEvenements) {
        this.listeEvenements = listeEvenements;
    }
    
    //Insert un événement dans la liste
    public void ajouterEvenement(ModelEvenement evenement) {
        this.listeEvenements.add(evenement);
    }
    
    //Retire un événement de la liste
    public ModelEvenement supprimerEvenement(int row) {
       return this.listeEvenements.remove(row);
    }
    
    //Remplace les attributs d'un élément de la liste par d'autres
    public void modifierEvenement(ModelEvenement evenement, int row) {
        this.listeEvenements.get(row).setTitre(evenement.titre);
        this.listeEvenements.get(row).setDescription(evenement.description);
        this.listeEvenements.get(row).setLien(evenement.lien);
        this.listeEvenements.get(row).setDate(evenement.date);
    }
    
    //Remplace la liste courante par une se trouvant dans un fichier XML.
    //Rafraichit aussi la table sur la page d'accueil.
    public void ouvrir(String fichier)
    {
       SAXBuilder sxb = new SAXBuilder();

       try
       {
          Document document = sxb.build(new File(fichier));
          Element racine = document.getRootElement();
          List<Element> newListeEvenements = racine.getChildren();
          Iterator i = newListeEvenements.iterator();
          this.setListeEvenements(new ArrayList<ModelEvenement>());
          while (i.hasNext()) {
              Element evenementXML = (Element)i.next();
              ControllerEvenement evenementControlleur = new ControllerEvenement();
              evenementControlleur.setTitre(evenementXML.getChild("titre").getText());
              evenementControlleur.setDescription(evenementXML.getChild("description").getText());
              evenementControlleur.setLien(evenementXML.getChild("lien").getText());
              evenementControlleur.setDate(evenementXML.getChild("date").getText());
              this.ajouterEvenement(evenementControlleur.evenement);
              
          }
       }
       catch (java.io.IOException e){}
       catch (JDOMException e){}
       
    }

    //Sauvegarde la liste courante dans un fichier XML
    public void enregistrer(String fichier)
    {
       try
       {
          Element racine = new Element("evenements");
          
          for (ModelEvenement evenement : this.listeEvenements) {
              Element evenement_xml = new Element("evenement");
              
              Element titre_xml = new Element("titre");
              titre_xml.setText(evenement.getTitre());
              evenement_xml.addContent(titre_xml);
              
              Element description_xml = new Element("description");
              description_xml.setText(evenement.getDescription());
              evenement_xml.addContent(description_xml);
              
              Element lien_xml = new Element("lien");
              lien_xml.setText(evenement.getLien());
              evenement_xml.addContent(lien_xml);

              Element date_xml = new Element("date");
              date_xml.setText(evenement.getDate().toString());
              evenement_xml.addContent(date_xml);
                      
              racine.addContent(evenement_xml);
          }
          

          //On utilise ici un affichage classique avec getPrettyFormat()
          XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
          //Remarquez qu'il suffit de créer une instance de FileOutputStream
          //avec en argument le nom du fichier pour effectuer la sérialisation.
          Document document = new Document(racine);
          sortie.output(document, new FileOutputStream(fichier));
       }
       catch (java.io.IOException e){}
    }
}
