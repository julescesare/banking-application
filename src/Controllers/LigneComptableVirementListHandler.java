package Controllers;

import java.util.List;

import InterfaceGraphique.ListeLigneComptableVirementWindow;
import Models.LigneComptableVirementDAO;
import classes.LigneComptableVirement;

public class LigneComptableVirementListHandler {
	
	ListeLigneComptableVirementWindow lcew = null;

	public void getLigneComptableVirement() {
		
		LigneComptableVirementDAO compteEDAO = new 	LigneComptableVirementDAO();

		List<LigneComptableVirement> Virement = 	compteEDAO.getAll();
		
		lcew.getCpteEpgneObservableList().addAll(Virement);
		
		System.out.println(Virement);
	}

	public ListeLigneComptableVirementWindow getLcew() {
		return lcew;
	}

	public LigneComptableVirementListHandler(ListeLigneComptableVirementWindow lcew) {
		this.lcew = lcew;
	}
}
