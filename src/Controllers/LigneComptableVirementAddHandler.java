package Controllers;

import java.util.ArrayList;
import java.util.List;

import InterfaceGraphique.AlertBoxWindow;
import InterfaceGraphique.LigneComptableDepotFrom;
import InterfaceGraphique.LigneComptableVirementFrom;
import Models.CompteCourantDAO;
import Models.CompteEpargneDAO;
import Models.CompteJoinDAO;
import Models.LigneComptableVirementDAO;
import classes.Compte;
import classes.CompteCourant;
import classes.CompteEpargne;
import classes.CompteJoin;
import classes.LigneComptableVirement;

public class LigneComptableVirementAddHandler  {

	LigneComptableVirementDAO cED = new LigneComptableVirementDAO ();
	LigneComptableVirementFrom  formVirement = null;
	//importation des manipulations de la DB
	
	CompteJoinDAO compteJoinDao= new CompteJoinDAO();
	CompteEpargneDAO compteEpargneDao = new CompteEpargneDAO();
	CompteCourantDAO compteCourantDao = new CompteCourantDAO();
	
	private CompteJoin compteJoin;
	private CompteEpargne compteEpargne;
	private CompteCourant compteCourant;
	
	private String numeroDuCompteExpediteur ;
	private String numeroDuCompteRecepteur ;
	private String theme;
	private String moyenPayement;
	private String motif ;
	private Double valeur;
	

	
	public  LigneComptableVirementAddHandler(LigneComptableVirementFrom formVirement) {
		super();
		this.formVirement = formVirement;
	}
	
	public void addClick() {
	    // Récupération des données des champs
	    String numeroDuCompteExpediteur = formVirement.getNumeroDuCompteExepditeurTextField().getText();
	    String numeroDuCompteRecepteur = formVirement.getNumeroDuCompteRecepteurTextField().getText();
	    String theme = "Virement";
	    String motif = formVirement.getSelectedRadioText(formVirement.getMotifGroup());
	    String moyenPayement = "Virement";
	    String valeurText = formVirement.getValeurTextField().getText();
	    
	    // Vérifier si les champs sont vides
	    if (numeroDuCompteExpediteur == null || numeroDuCompteRecepteur == null || motif == null || valeurText == null) {
	        new AlertBoxWindow("Erreur", "Remplissez correctement les informations !!");
	        return;
	    }
	    
	    try {
	        Double valeur = Double.valueOf(valeurText);

	        Compte compteExpediteur = this.trouverCompte(numeroDuCompteExpediteur);
	        Compte compteRecepteur = this.trouverCompte(numeroDuCompteRecepteur);

	        if (compteExpediteur == null || compteRecepteur == null) {
	            new AlertBoxWindow("Erreur", "Aucun compte trouvé pour l'expéditeur ou le récepteur");
	            return;
	        }

	        // Vérification du type d'opération à effectuer
	        if (valeur <= compteExpediteur.getValeur()) {
	            Double nouvelleValeurExpediteur = compteExpediteur.getValeur() - valeur;
	            compteExpediteur.setValeur(nouvelleValeurExpediteur);

	            Double nouvelleValeurRecepteur = compteRecepteur.getValeur() + valeur;
	            compteRecepteur.setValeur(nouvelleValeurRecepteur);

	            // Mise à jour des comptes dans la base de données
	            this.mettreAJourCompte(compteExpediteur);
	            this.mettreAJourCompte(compteRecepteur);

	            // Ajout de la ligne comptable du virement
	            LigneComptableVirement cpteVirement = new LigneComptableVirement(theme, motif, moyenPayement, valeur, numeroDuCompteExpediteur, numeroDuCompteRecepteur);
	            cED.add(cpteVirement);
	            formVirement.getWindow().close();
	            new AlertBoxWindow("Succes", "Virement Effectuer Avec Succes");
	        } else {
	            new AlertBoxWindow("Erreur", "Solde insuffisant pour effectuer ce virement");
	        }
	    } catch (NumberFormatException e) {
	        new AlertBoxWindow("Erreur", "Veuillez entrer une valeur numérique valide pour le champ 'valeur' !!");
	    }
	}

	private void mettreAJourCompte(Compte compte) {
	    if (compte instanceof CompteJoin) {
	        compteJoinDao.update(compte.getId(), compte.getValeur());
	    } else if (compte instanceof CompteEpargne) {
	        compteEpargneDao.update(compte.getId(), compte.getValeur());
	    } else if (compte instanceof CompteCourant) {
	        compteCourantDao.update(compte.getId(), compte.getValeur());
	    }
	}

	public Compte trouverCompte(String num) {
	    // Algorithme de recherche du bon compte
	    Compte compteJoin = compteJoinDao.getOneByNum(num);
	    Compte compteEpargne = compteEpargneDao.getOneByNum(num);
	    Compte compteCourant = compteCourantDao.getOneByNum(num);

	    if (compteEpargne == null && compteJoin == null && compteCourant == null) {
	        new AlertBoxWindow("Erreur", "Aucun compte trouvé");
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
