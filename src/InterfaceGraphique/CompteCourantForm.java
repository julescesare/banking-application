package InterfaceGraphique;

import Controllers.CompteCourantAddHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CompteCourantForm {

	VBox root = new VBox();
	HBox buttonBox = new HBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Creer votre compte Courant");
	/*Label typeCompteLabel = new Label("Le type Compte ");
	TextField typeCompteTextField = new TextField();*/
	Label numeroDuCompteLabel = new Label("Numero Compte");
	TextField numeroDuCompteTextField = new TextField();
	Label valeurLabel = new Label("Premiere Valeur Creditée");
	TextField valeurTextField = new TextField();
	
	

	public TextField getNumeroDuCompteTextField() {
		return numeroDuCompteTextField;
	}

	public void setNumeroDuCompteTextField(TextField numeroDuCompteTextField) {
		this.numeroDuCompteTextField = numeroDuCompteTextField;
	}

	public TextField getValeurTextField() {
		return valeurTextField;
	}

	public void setValeurTextField(TextField valeurTextField) {
		this.valeurTextField = valeurTextField;
	}


	Button soumisCourant = new Button("Rechercher");
	Button soumisAnnuler = new Button("Annuler");
	
	
	CompteCourantAddHandler CourantAddHandler = new CompteCourantAddHandler(this);
	
	
	//fonction de manipulation de la fentre
	private void initWindow() {
		addEvent();
		addNodesToWindow();
		 addStylesToNodes();
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(500);
		window.setTitle("Recherche De Compte");
		root.setSpacing(15);
		
	
	
	}
	
	//fonction d'ajout d'element a notre fenetre
	private void addNodesToWindow() {
		root.setAlignment(Pos.CENTER);  // Centre tous les éléments du VBox root
		root.getChildren().add(titleLabel);
		numeroDuCompteTextField.setMaxWidth(400);
		valeurTextField.setMaxWidth(400);
		root.getChildren().addAll(numeroDuCompteLabel,
				numeroDuCompteTextField,valeurLabel,valeurTextField);
		buttonBox.getChildren().addAll(soumisCourant,soumisAnnuler);
		buttonBox.setAlignment(Pos.CENTER);
		root.getChildren().add(buttonBox);
		buttonBox.setSpacing(20);
	}
	
	public CompteCourantForm() {
		System.out.println("Rechercher");
		initWindow();
		window.show();
	}
	
	public void addStylesToNodes(){
		scene.getStylesheets().add("form.css");
		root.getStyleClass().add("root-container");
		titleLabel.getStyleClass().add("title-label");
	
		numeroDuCompteLabel.getStyleClass().add("label");
		
		valeurLabel.getStyleClass().add("label");
	
		soumisCourant.getStyleClass().add("submit-button");
		soumisAnnuler.getStyleClass().add("cancel-button");
		
		
		
	}
	
	private void addEvent() {
	soumisAnnuler.setOnAction(event->{
		window.close();
	});
	soumisCourant.setOnAction(event->{
		System.out.println("Traitement pour ajouter");
		
		CourantAddHandler.addClick();
		//window.close();
		
	});

}

	public Stage getWindow() {
		return window;
	}

	public void setWindow(Stage window) {
		this.window = window;
	}
}
