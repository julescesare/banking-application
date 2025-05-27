package classes;

import java.time.LocalDate;

public class LigneComptableDepot {
	private String theme,motif,moyenPayement,numeroCompte;
	
	private int id;
	private LocalDate date;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private double valeur;
	

	public LigneComptableDepot(String theme, String motif, String moyenPayement, double valeur,String numeroCompte) {
		super();
		this.theme = theme;
		this.motif = motif;
		this.moyenPayement = moyenPayement;
		this.valeur = valeur;
		this.date =  LocalDate.now();
		this.numeroCompte = numeroCompte;
	}

	public LigneComptableDepot(int int1, String theme, String moyenPayement, double valeur, String motif, String numeroCompte) {
		// TODO Auto-generated constructor stub
		this.id = int1;
		this.theme = theme;
		this.motif = motif;
		this.moyenPayement = moyenPayement;
		this.valeur = valeur;
		this.date =  LocalDate.now();
		this.numeroCompte = numeroCompte;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getMoyenPayement() {
		return moyenPayement;
	}

	public void setMoyenPayement(String moyenPayement) {
		this.moyenPayement = moyenPayement;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getValeur() {
		return valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}
	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	
}
