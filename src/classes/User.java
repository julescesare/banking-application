package classes;

import java.time.LocalDate;

public  class User {
	public User(int id, String nom, String motDepasse) {
		super();
		this.id = id;
		this.nom= nom;
		this.motDePasse = motDepasse;
		
	}

	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	private int id;
	private String nom;
    private String motDePasse;
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
    
    
}
