package InterfaceGraphique;

import Controllers.UserLoginHandler;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

public class UserLoginForm extends Application {
	
	VBox root = new VBox();
	HBox buttonBox = new HBox();
	Scene scene = new Scene(root);
	Stage window;
	Label titleLabel = new Label("CONNEXION");

	Label nomLabel = new Label("Nom:");
	TextField nomTextField = new TextField();
	Label motDePasseLabel = new Label("Mot De Passe :");
	//TextField motDePasseTextField = new TextField();
	PasswordField motDePasseTextField = new PasswordField();
	
	Button soumisConnexion = new Button("Connexion");
	
	UserLoginHandler userLoginHandler = new UserLoginHandler(this);
	
	@Override
	public void start(Stage window) throws Exception {
		this.window = window;
		addEvent();
		addNodesToWindow();
		addStylesToNodes();
		window.setScene(scene);
		window.setWidth(600);
		window.setHeight(400);
		window.setTitle("Authentification ");
		root.setSpacing(15);
		window.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	// Fonction d'ajout d'éléments à notre fenêtre
	private void addNodesToWindow() {
		root.setAlignment(Pos.CENTER);  // Centre tous les éléments du VBox root
		
		root.getChildren().add(titleLabel);
		nomTextField.setMaxWidth(400);
		motDePasseTextField.setMaxWidth(400);
		root.getChildren().addAll(nomLabel, nomTextField, motDePasseLabel, motDePasseTextField);
		
		buttonBox.getChildren().addAll(soumisConnexion);
		buttonBox.setAlignment(Pos.CENTER);
		root.getChildren().add(buttonBox);
		
		buttonBox.setSpacing(20);
	}
	
	public void addStylesToNodes(){
		scene.getStylesheets().add("form.css");
		root.getStyleClass().add("root-container");
		titleLabel.getStyleClass().add("title-label");
		nomLabel.getStyleClass().add("label");
		motDePasseLabel.getStyleClass().add("label");
		soumisConnexion.getStyleClass().add("submit-button");
	}
	
	public TextField getNomTextField() {
		return nomTextField;
	}

	public void setNomTextField(TextField nomTextField) {
		this.nomTextField = nomTextField;
	}


	public PasswordField getMotDePasseTextField() {
		return motDePasseTextField;
	}

	public void setMotDePasseTextField(PasswordField motDePasseTextField) {
		this.motDePasseTextField = motDePasseTextField;
	}

	private void addEvent() {
		soumisConnexion.setOnAction(event -> {
			System.out.println("Traitement pour ajouter");
			UserLoginHandler.addClick();
		});
		
	}

	public Stage getWindow() {
		return window;
	}

	public void setWindow(Stage window) {
		this.window = window;
	}
}
