package classes;


import java.sql.Date;
import java.time.LocalDate;

public class CompteCourant extends Compte {
	
	
	
	public CompteCourant(int id, String string, String typeCompte, Double valeur) {
		super(id, string, typeCompte, valeur);
		this.date = LocalDate.now();
	}
	public CompteCourant(String numeroDuCompte, String typeCompte, Double valeur) {
		super(numeroDuCompte, typeCompte, valeur);
		this.date = LocalDate.now();
		
	
}
}