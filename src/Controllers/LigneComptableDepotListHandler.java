package Controllers;

import java.util.List;

import InterfaceGraphique.ListeLigneComptableDepotWindow;
import Models.LigneComptableDepotDAO;
import classes.LigneComptableDepot;

public class LigneComptableDepotListHandler {
	
	ListeLigneComptableDepotWindow lcew = null;

	public void getLigneComptableDepot() {
		
		LigneComptableDepotDAO compteEDAO = new 	LigneComptableDepotDAO();

		List<LigneComptableDepot> depot = 	compteEDAO.getAll();
		
		lcew.getCpteEpgneObservableList().addAll(depot);
		
		System.out.println(depot);
	}

	public ListeLigneComptableDepotWindow getLcew() {
		return lcew;
	}

	public LigneComptableDepotListHandler(ListeLigneComptableDepotWindow lcew) {
		this.lcew = lcew;
	}
}
