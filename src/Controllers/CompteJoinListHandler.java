package Controllers;

import java.util.ArrayList;
import java.util.List;

import InterfaceGraphique.ListeCompteJoinWindow;
import Models.CompteJoinDAO;
import classes.CompteJoin;

public class CompteJoinListHandler {
	
ListeCompteJoinWindow lcew = null;

public void getCompteJoin() {
	
	CompteJoinDAO compteEDAO = new 	CompteJoinDAO();

	List<CompteJoin> listJoin = 	compteEDAO.getAll();
	
	lcew.getCpteEpgneObservableList().addAll(listJoin);
	
	System.out.println(listJoin);
}

public ListeCompteJoinWindow getLcew() {
	return lcew;
}

public CompteJoinListHandler(ListeCompteJoinWindow lcew) {
	this.lcew = lcew;
}
}
