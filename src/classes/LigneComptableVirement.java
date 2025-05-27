package classes;

public class LigneComptableVirement extends LigneComptableDepot {
	
	public String getNumeroCompteRecepteur() {
		return numeroCompteRecepteur;
	}
	public void setNumeroCompteRecepteur(String numeroCompteRecepteur) {
		this.numeroCompteRecepteur = numeroCompteRecepteur;
	}
	private String numeroCompteRecepteur;

	public LigneComptableVirement(int int1, String theme, String moyenPayement, double valeur, String motif,
			String numeroCompte,String numeroCompteRecepteur) {
		super(int1, theme, moyenPayement, valeur, motif, numeroCompte);
		this.numeroCompteRecepteur = numeroCompteRecepteur;
	
	}
	public 	LigneComptableVirement( String theme, String moyenPayement, String motif, double valeur,
			String numeroCompte,String numeroCompteRecepteur) {
		super(theme, moyenPayement, motif, valeur, numeroCompte);
		this.numeroCompteRecepteur = numeroCompteRecepteur;
	
	}

}
