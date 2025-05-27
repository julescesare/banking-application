package classes;

public class CompteJoin  extends Compte {
	private String numeroCompte2;

	public CompteJoin(String numeroCompte1,String typeCompte, Double valeur, String numeroCompte2) {
		super(numeroCompte1, typeCompte, valeur);
		this.numeroCompte2 = numeroCompte2;
	}

	public CompteJoin(int int1, String string, String string2, String string3, double double1) {
		// TODO Auto-generated constructor stub
		super(int1,string,string3,double1);
		this.numeroCompte2= string2;
	}

	public String getNumeroCompte2() {
		return numeroCompte2;
	}

	public void setNumeroCompte2(String numeroCompte2) {
		this.numeroCompte2 = numeroCompte2;
	}
	

}
