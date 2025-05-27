package classes;

import java.time.LocalDate;

public  class Compte {
	public Compte(int id, String string, String typeCompte, Double valeur) {
		super();
		this.id = id;
		this.numeroDuCompte = string;
		this.typeCompte = typeCompte;
		this.valeur = valeur;
		this.date = LocalDate.now();
	}
	public Compte(String numeroDuCompte, String typeCompte, Double valeur) {
		this.numeroDuCompte = numeroDuCompte;
		this.typeCompte = typeCompte;
		this.valeur = valeur;
		this.date = LocalDate.now();
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumeroDuCompte() {
		return numeroDuCompte;
	}
	public void setNumeroDuCompte(String numeroDuCompte) {
		this.numeroDuCompte = numeroDuCompte;
	}
	public String getTypeCompte() {
		return typeCompte;
	}
	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}
	public Double getValeur() {
		return valeur;
	}
	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	protected int id;
	protected String numeroDuCompte;
    protected String typeCompte;
    protected Double valeur;
    protected LocalDate date;
    
}
