package InterfaceGraphique;


import Controllers.CompteJoinAddHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CompteJoinForm {
	
	VBox root = new VBox();
	HBox buttonBox = new HBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Creer votre compte Join");
	public TextField getNumeroDuComptePremierTextField() {
		return numeroDuComptePremierTextField;
	}

	public void setNumeroDuComptePremierTextField(TextField numeroDuComptePremierTextField) {
		this.numeroDuComptePremierTextField = numeroDuComptePremierTextField;
	}

	public TextField getNumeroDuCompteSecondTextField() {
		return numeroDuCompteSecondTextField;
	}

	public void setNumeroDuCompteSecondTextField(TextField numeroDuCompteSecondTextField) {
		this.numeroDuCompteSecondTextField = numeroDuCompteSecondTextField;
	}


	/*Label typeCompteLabel = new Label("Le type Compte ");
	TextField typeCompteTextField = new TextField();*/
	Label numeroDuComptePremierLabel = new Label("Numero Compte 1:");
	TextField numeroDuComptePremierTextField = new TextField();
	Label numeroDuCompteSecondLabel = new Label("Numero Compte 2 :");
	TextField numeroDuCompteSecondTextField = new TextField();
	Label valeurLabel = new Label("Premiere Valeur Creditée");
	TextField valeurTextField = new TextField();
	
	Button soumisJoin = new Button("Enregistrer");
	Button soumisAnnuler = new Button("Annuler");
	
	CompteJoinAddHandler JoinAddHandler = new CompteJoinAddHandler(this);
	



	public TextField getValeurTextField() {
		return valeurTextField;
	}

	public Stage getWindow() {
		return window;
	}

	public void setWindow(Stage window) {
		this.window = window;
	}

	public void setValeurTextField(TextField valeurTextField) {
		this.valeurTextField = valeurTextField;
	}



	//fonction de manipulation de la fentre
	private void initWindow() {
		addEvent();
		addNodesToWindow();
		 addStylesToNodes();
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(500);
		window.setTitle("Creation du compte");
		root.setSpacing(15);
	
	
	}
	
	//fonction d'ajout d'element a notre fenetre
	private void addNodesToWindow() {
		root.setAlignment(Pos.CENTER);  // Centre tous les éléments du VBox root
		root.getChildren().add(titleLabel);
		numeroDuComptePremierTextField.setMaxWidth(400);
		numeroDuCompteSecondTextField.setMaxWidth(400);
		valeurTextField.setMaxWidth(400);
		
		
		root.getChildren().addAll(numeroDuComptePremierLabel,
				numeroDuComptePremierTextField,numeroDuCompteSecondLabel,numeroDuCompteSecondTextField,valeurLabel,valeurTextField
				);
		buttonBox.getChildren().addAll(soumisJoin,soumisAnnuler);
		
		root.getChildren().add(buttonBox);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(20);
	}
	
	public CompteJoinForm() {
		System.out.println("Enregistrer");
		initWindow();
		window.show();
	}
	
	
	public void addStylesToNodes(){
		scene.getStylesheets().add("form.css");
		root.getStyleClass().add("root-container");
		titleLabel.getStyleClass().add("title-label");
	
		numeroDuComptePremierLabel.getStyleClass().add("label");
		numeroDuCompteSecondLabel.getStyleClass().add("label");
		
		valeurLabel.getStyleClass().add("label");
	
		soumisJoin.getStyleClass().add("submit-button");
		soumisAnnuler.getStyleClass().add("cancel-button");
		
		
		
	}
	
	private void addEvent() {
	soumisAnnuler.setOnAction(event->{
		window.close();
	});
	soumisJoin.setOnAction(event->{
		System.out.println("Traitement pour ajouter");
		
		JoinAddHandler.addClick();
		

	});
	
}
 
}
