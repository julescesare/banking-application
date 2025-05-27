package Controllers;

import java.util.ArrayList;
import java.util.List;

import InterfaceGraphique.AlertBoxWindow;
import InterfaceGraphique.LigneComptableDepotFrom;
import InterfaceGraphique.RechercheForm;
import Models.CompteCourantDAO;
import Models.CompteEpargneDAO;
import Models.CompteJoinDAO;
import Models.LigneComptableDepotDAO;
import classes.Compte;
import classes.CompteCourant;
import classes.CompteEpargne;
import classes.CompteJoin;
import classes.LigneComptableDepot;

public class RechercheHandler  {


	RechercheForm formDepot = null;
	//importation des manipulations de la DB
	
	CompteJoinDAO compteJoinDao= new CompteJoinDAO();
	CompteEpargneDAO compteEpargneDao = new CompteEpargneDAO();
	CompteCourantDAO compteCourantDao = new CompteCourantDAO();
	
	private CompteJoin compteJoin;
	private CompteEpargne compteEpargne;
	private CompteCourant compteCourant;
	
	private String numeroDuCompte ;

	

	
	public  RechercheHandler(RechercheForm formDepot) {
		super();
		this.formDepot = formDepot;
	}
	
	public void addClick() {
	    String numeroDuCompte = formDepot.getNumeroDuCompteTextField().getText();

	    
	    // Vérifier si les champs sont vides ou null
	    if (numeroDuCompte == null || numeroDuCompte.isEmpty() ) {
	        new AlertBoxWindow("Erreur", "Remplissez correctement les informations !!");
	        return;
	    }
	    
	    try {
	     
	        
	       
	        Compte compte = this.trouverCompte(numeroDuCompte);
	        
	     

	        
	      
	            // Vérification du type de compte retourné
	            if (compte instanceof CompteJoin) {
	                System.out.println("Le compte est de type CompteJoin");
	                
	                String message ="Le Type Du Compte : "+ compte.getTypeCompte()+"\n les Comptes Associés : "+compte.getNumeroDuCompte()+" et "
	                +((CompteJoin)compte).getNumeroCompte2()+"\n Montant Du Compte : "+compte.getValeur()+ "\n Date De Creation : "+compte.getDate();
	               
	                new AlertBoxWindow("Succes", message);
	                
	            } else if (compte instanceof CompteEpargne) {
	                System.out.println("Le compte est de type CompteEpargne");
	                String message ="Le Type Du Compte : "+ compte.getTypeCompte()+"\n le Compte : "+compte.getNumeroDuCompte()+"\n Taux De Placement : "
	    	                +((CompteEpargne)compte).getTauxPlacement()+"\n Montant Du Compte : "+compte.getValeur()+ "\n Date De Creation : "+compte.getDate();
	    	               
	    	                new AlertBoxWindow("Succes", message);
	    	                
	            } else if (compte instanceof CompteCourant) {
	                System.out.println("Le compte est de type CompteCourant");
	                String message ="Le Type Du Compte : "+ compte.getTypeCompte()+"\n le Compte: "+compte.getNumeroDuCompte()
	    	                +"\n Montant Du Compte : "+compte.getValeur()+ "\n Date De Creation : "+compte.getDate();
	    	               
	    	                new AlertBoxWindow("Succes", message);
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
	        new AlertBoxWindow("Erreur", "Aucun compte trouvé Veuillez en Creer");
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
