package classes;


import java.sql.Date;
import java.time.LocalDate;

public class CompteEpargne extends Compte{
	


	public CompteEpargne(int id, String string, String typeCompte, Double valeur, int taux) {
		super(id, string, typeCompte, valeur);
		// TODO Auto-generated constructor stub
		tauxPlacement = taux;
	}

	public CompteEpargne(String numeroDuCompte, String typeCompte, Double valeur, int tauxDePlacement) {
		super(numeroDuCompte, typeCompte, valeur);
		tauxPlacement = tauxDePlacement;
	}

	public int getTauxPlacement() {
		return tauxPlacement;
	}

	public void setTauxPlacement(int tauxPlacement) {
		this.tauxPlacement = tauxPlacement;
	}

	protected int tauxPlacement;
}
