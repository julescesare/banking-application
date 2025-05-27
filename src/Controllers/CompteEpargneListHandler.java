package Controllers;

import java.util.ArrayList;
import java.util.List;

import InterfaceGraphique.ListeCompteEpargneWindow;
import Models.CompteEpargneDAO;
import classes.CompteEpargne;

public class CompteEpargneListHandler {
	
ListeCompteEpargneWindow lcew = null;

public void getCompteEpargne() {
	
	CompteEpargneDAO compteEDAO = new 	CompteEpargneDAO();

	List<CompteEpargne> listEpargne = 	compteEDAO.getAll();
	
	lcew.getCpteEpgneObservableList().addAll(listEpargne);
	
	System.out.println(listEpargne);
}

public ListeCompteEpargneWindow getLcew() {
	return lcew;
}

public CompteEpargneListHandler(ListeCompteEpargneWindow lcew) {
	this.lcew = lcew;
}
}
