package Controllers;

import java.util.ArrayList;
import java.util.List;

import InterfaceGraphique.AlertBoxWindow;
import InterfaceGraphique.LigneComptableDepotFrom;
import Models.CompteCourantDAO;
import Models.CompteEpargneDAO;
import Models.CompteJoinDAO;
import Models.LigneComptableDepotDAO;
import classes.Compte;
import classes.CompteCourant;
import classes.CompteEpargne;
import classes.CompteJoin;
import classes.LigneComptableDepot;

public class LigneComptableDepotAddHandler  {

	LigneComptableDepotDAO cED = new LigneComptableDepotDAO ();
	LigneComptableDepotFrom  formDepot = null;
	//importation des manipulations de la DB
	
	CompteJoinDAO compteJoinDao= new CompteJoinDAO();
	CompteEpargneDAO compteEpargneDao = new CompteEpargneDAO();
	CompteCourantDAO compteCourantDao = new CompteCourantDAO();
	
	private CompteJoin compteJoin;
	private CompteEpargne compteEpargne;
	private CompteCourant compteCourant;
	
	private String numeroDuCompte ;
	private String theme;
	private String moyenPayement;
	private String motif ;
	private Double valeur;
	

	
	public  LigneComptableDepotAddHandler(LigneComptableDepotFrom formDepot) {
		super();
		this.formDepot = formDepot;
	}
	
	public void addClick() {
	    String numeroDuCompte = formDepot.getNumeroDuCompteTextField().getText();
	    String theme = formDepot.getSelectedRadioText(formDepot.getThemeGroup());
	    String motif = formDepot.getSelectedRadioText(formDepot.getMotifGroup());
	    String moyenPayement = formDepot.getSelectedRadioText(formDepot.getMoyenPayementGroup());
	    String valeurText = formDepot.getValeurTextField().getText();
	    
	    // Vérifier si les champs sont vides ou null
	    if (numeroDuCompte == null || numeroDuCompte.isEmpty() || 
	        theme == null || theme.isEmpty() || 
	        motif == null || motif.isEmpty() || 
	        moyenPayement == null || moyenPayement.isEmpty() || 
	        valeurText == null || valeurText.isEmpty()) {
	        new AlertBoxWindow("Erreur", "Remplissez correctement les informations !!");
	        return;
	    }
	    
	    try {
	        Double valeur = Double.valueOf(valeurText);
	        
	        LigneComptableDepot cpteDepot = new LigneComptableDepot(theme, motif, moyenPayement, valeur, numeroDuCompte);
	        
	        Compte compte = this.trouverCompte(numeroDuCompte);
	        
	        if (compte == null) {
	            new AlertBoxWindow("Erreur", "Aucun compte trouvé echec d'enregistrement");
	          
	        }
	        
	        // Vérification du type d'opération à effectuer
	        if (theme.equals("Depot")) {
	            Double newValeur = compte.getValeur() + valeur;
	            compte.setValeur(newValeur);
	        } else if (theme.equals("Retrait")) {
	            if (valeur <= compte.getValeur()) {
	                Double newValeur = compte.getValeur() - valeur;
	                compte.setValeur(newValeur);
	            } else {
	                new AlertBoxWindow("Erreur", "Solde insuffisant pour faire cette transaction : \n solde en compte : " + compte.getValeur());
	              
	            }
	        }
	        
	        try {
	            // Vérification du type de compte retourné
	            if (compte instanceof CompteJoin) {
	                System.out.println("Le compte est de type CompteJoin");
	                compteJoinDao.update(compte.getId(), compte.getValeur());
	                
	            } else if (compte instanceof CompteEpargne) {
	                System.out.println("Le compte est de type CompteEpargne");
	                compteEpargneDao.update(compte.getId(), compte.getValeur());
	            } else if (compte instanceof CompteCourant) {
	                System.out.println("Le compte est de type CompteCourant");
	                compteCourantDao.update(compte.getId(), compte.getValeur());
	            }
	            cED.add(cpteDepot);
	            formDepot.getWindow().close();
	            new AlertBoxWindow("Succes", "Operation de "+theme+" Reussie");
	        } catch (Exception e) {
	            new AlertBoxWindow("Erreur", "Erreur lors de la mise à jour du compte");
	        }
	    } catch (NumberFormatException e) {
	        new AlertBoxWindow("Erreur", "Veuillez entrer une valeur numérique valide pour le champ 'valeur' !!");
	    }
	}

	
	
	public Compte trouverCompte(String numeroDuCompte) {
	    // Algorithme de recherche du bon compte
	    Compte compteJoin = compteJoinDao.getOneByNum(numeroDuCompte);
	    Compte compteEpargne = compteEpargneDao.getOneByNum(numeroDuCompte);
	    Compte compteCourant = compteCourantDao.getOneByNum(numeroDuCompte);
	    
	    if (compteEpargne == null && compteJoin == null && compteCourant == null) {
	        new AlertBoxWindow("Erreur", "Aucun compte trouvé echec d'enregistrement");
	        return null;
	    } else if (compteEpargne == null && compteJoin != null && compteCourant == null) {
	        return compteJoin;
	    } else if (compteEpargne == null && compteJoin == null && compteCourant != null) {
	        return compteCourant;
	    } else if (compteEpargne != null && compteJoin == null && compteCourant == null) {
	        return compteEpargne;
	    }
	    
	    return null;
	}

	
}
