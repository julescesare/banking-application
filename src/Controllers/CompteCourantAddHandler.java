package Controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import InterfaceGraphique.AlertBoxWindow;
import InterfaceGraphique.CompteCourantForm;
import Models.CompteCourantDAO;
import classes.CompteCourant;
import InterfaceGraphique.CompteCourantForm;


public class CompteCourantAddHandler {

	CompteCourantDAO cED = new CompteCourantDAO();
	CompteCourantForm formCourant = null;
	
	public CompteCourantAddHandler(CompteCourantForm formCourant) {
		super();
		this.formCourant = formCourant;
	}
	
	public void addClick() {
	    String numeroDuCompte = formCourant.getNumeroDuCompteTextField().getText();
	    String typeCompte = "Courant";
	    String valeurText = formCourant.getValeurTextField().getText();
	    
	    // Vérifier si les champs sont vides
	    if (numeroDuCompte.isEmpty() || valeurText.isEmpty()) {
	        new AlertBoxWindow("Erreur", "Remplissez correctement les informations !!");
	     
	    }
	    // Vérifier si le numéro du compte contient seulement des chiffres et des traits d'union
	    String regex = "^[0-9-]+$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(numeroDuCompte);
	    
	    if (!matcher.matches()) {
	        new AlertBoxWindow("Erreur", "Le numéro du compte doit contenir uniquement des chiffres et des traits d'union(-) !!");
	        return;
	    }
	    
	    try {
	        Double valeur = Double.valueOf(valeurText);
	        
	        CompteCourant cpteCourant = new CompteCourant(numeroDuCompte, typeCompte, valeur);
	        cED.add(cpteCourant);
	        formCourant.getWindow().close();
	        new AlertBoxWindow("Succes", "Votre compte est créé, merci !!");
	        
	        
	    } catch (NumberFormatException e) {
	        new AlertBoxWindow("Erreur", "Veuillez entrer une valeur numérique valide pour le champ 'valeur' !!");
	    }
	}

}
