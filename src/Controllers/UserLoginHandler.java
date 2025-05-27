package Controllers;

import InterfaceGraphique.AlertBoxWindow;
import InterfaceGraphique.MainWindow;
import InterfaceGraphique.UserLoginForm;

import Models.UserDAO;

import classes.User;
import javafx.application.Platform;
import javafx.stage.Stage;

public class UserLoginHandler {

	UserDAO cED = new UserDAO();
	static UserLoginForm formLogin = null;
	static UserDAO UserDao = new UserDAO();

	public UserLoginHandler(UserLoginForm formLogin) {
		super();
		this.formLogin = formLogin;
	}
	
	public static void addClick() {
	    String nom = formLogin.getNomTextField().getText();
	    String motDePasse = formLogin.getMotDePasseTextField().getText();
	    
	    // Vérifier si les champs sont vides ou null
	    if (nom == null || nom.isEmpty() || 
	        motDePasse == null || motDePasse.isEmpty() 
	        ) {
	        new AlertBoxWindow("Erreur", "Remplissez correctement les informations !!");
	        return;
	    }
	    
	    User User = trouverUser(nom);
	    
	    if (User == null) {
	        new AlertBoxWindow("Erreur", "Erreur d'Authentification");
	        return;
	    } else {
	        Platform.runLater(() -> {
	            try {
	                formLogin.getWindow().close(); // Fermer la fenêtre de connexion
	                new MainWindow().start(new Stage()); // Ouvrir la fenêtre principale
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });
	    }
	}

	public static User trouverUser(String numeroDuUser) {
	    User user = UserDao.getOneByNum(numeroDuUser);
	    return user;
	}
}
