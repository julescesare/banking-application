package Controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import InterfaceGraphique.AlertBoxWindow;
import InterfaceGraphique.CompteJoinForm;

import Models.CompteJoinDAO;
import classes.CompteJoin;

public class CompteJoinAddHandler {

	CompteJoinDAO cED = new CompteJoinDAO();
	CompteJoinForm formJoin = null;
	
	public CompteJoinAddHandler(CompteJoinForm formJoin) {
		super();
		this.formJoin = formJoin;
	}
	public void addClick() {
	    String numeroDuComptePremier = formJoin.getNumeroDuComptePremierTextField().getText();
	    String typeCompte = "Join";
	    String valeurText = formJoin.getValeurTextField().getText();
	    String numeroDuCompteSecond = formJoin.getNumeroDuCompteSecondTextField().getText();
	    
	    // Vérifier si les champs sont vides
	    if (numeroDuComptePremier.isEmpty() || valeurText.isEmpty() || numeroDuCompteSecond.isEmpty()) {
	        new AlertBoxWindow("Erreur", "Remplissez correctement les informations !!");
	        return;
	    }
	    // Vérifier si le numéro du compte contient seulement des chiffres et des traits d'union
	    String regex = "^[0-9-]+$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcherPremier = pattern.matcher(numeroDuComptePremier);
	    Matcher matcherSecond = pattern.matcher(numeroDuCompteSecond);
	    
	    if (!matcherPremier.matches()|| !matcherSecond.matches()) {
	        new AlertBoxWindow("Erreur", "Le numéro du compte doit contenir uniquement des chiffres et des traits d'union(-) !!");
	        return;
	    }
	    
	    try {
	        Double valeur = Double.valueOf(valeurText);
	        
	        CompteJoin cpteJoin = new CompteJoin(numeroDuComptePremier, typeCompte, valeur, numeroDuCompteSecond);
	        cED.add(cpteJoin);
	        formJoin.getWindow().close();
	        new AlertBoxWindow("Succes", "Votre compte est créé, merci !!");
	    } catch (NumberFormatException e) {
	        new AlertBoxWindow("Erreur", "Veuillez entrer une valeur numérique valide pour le champ 'valeur' !!");
	    }
	}


}
