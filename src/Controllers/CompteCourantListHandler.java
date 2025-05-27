package Controllers;

import java.util.List;

import InterfaceGraphique.ListeCompteCourantWindow;
import Models.CompteCourantDAO;

import classes.CompteCourant;


public class CompteCourantListHandler {


	
	ListeCompteCourantWindow lcew = null;

	public CompteCourantListHandler(ListeCompteCourantWindow listeCompteCourantWindow) {
		this.lcew = listeCompteCourantWindow;
	}

	public void getCompteEpargne() {
		
		CompteCourantDAO compteADAO = new 	CompteCourantDAO();

		List<CompteCourant> listEpargne = 	compteADAO.getAll();
		
		lcew.getCpteEpgneObservableList().addAll(listEpargne);
		
		System.out.println(listEpargne);
	}

	public ListeCompteCourantWindow getLcew() {
		return lcew;
	}

	public void setLcew(ListeCompteCourantWindow lcew) {
		this.lcew = lcew;
	}



}
