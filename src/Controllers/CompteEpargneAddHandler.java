package Controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import InterfaceGraphique.AlertBoxWindow;
import InterfaceGraphique.CompteEpargneForm;
import Models.CompteEpargneDAO;
import classes.CompteEpargne;

public class CompteEpargneAddHandler {

	CompteEpargneDAO cED = new CompteEpargneDAO();
	CompteEpargneForm formEpargne = null;
	
	public CompteEpargneAddHandler(CompteEpargneForm formEpargne) {
		super();
		this.formEpargne = formEpargne;
	}
	

	
	public void addClick() {
	    String numeroDuCompte = formEpargne.getNumeroDuCompteTextField().getText();
		String typeCompte = "Epargne";
	    String valeurText = formEpargne.getValeurTextField().getText();
	    String tauxPlacementText =formEpargne.getTauxPlacementTextField().getText();
	    
	    // Vérifier si les champs sont vides
	    if (numeroDuCompte.isEmpty() || valeurText.isEmpty() || tauxPlacementText.isEmpty()) {
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
	        int tauxDePlacement = Integer.valueOf( tauxPlacementText);
	        
	        CompteEpargne cpteEpargne = new CompteEpargne(numeroDuCompte,typeCompte,valeur,tauxDePlacement);
			cED.add(cpteEpargne);
			   formEpargne.getWindow().close();
	        new AlertBoxWindow("Succes", "Votre compte est créé, merci !!");
	        
	    } catch (NumberFormatException e) {
	    	
	        new AlertBoxWindow("Erreur", "Veuillez entrer une valeur numérique valide pour le champ 'valeur' et 'taux de placement!!");
	    }
	}

	
}
