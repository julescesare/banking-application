package InterfaceGraphique;


import Controllers.CompteEpargneAddHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CompteEpargneForm {
	
	VBox root = new VBox();
	HBox buttonBox = new HBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Creer votre compte Epargne");
	/*Label typeCompteLabel = new Label("Le type Compte ");
	TextField typeCompteTextField = new TextField();*/
	Label numeroDuCompteLabel = new Label("Numero Compte");
	TextField numeroDuCompteTextField = new TextField();
	Label valeurLabel = new Label("Premiere Valeur Creditée");
	TextField valeurTextField = new TextField();
	Label tauxPlacementLabel = new Label("Le taux de placement");
	TextField tauxPlacementTextField = new TextField();
	Button soumisEpargne = new Button("Enregistrer");
	Button soumisAnnuler = new Button("Annuler");
	
	CompteEpargneAddHandler EpargneAddHandler = new CompteEpargneAddHandler(this);
	
	
	


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

	public TextField getTauxPlacementTextField() {
		return tauxPlacementTextField;
	}

	public void setTauxPlacementTextField(TextField tauxPlacementTextField) {
		this.tauxPlacementTextField = tauxPlacementTextField;
	}

	//fonction de manipulation de la fentre
	private void initWindow() {
		addEvent();
		addNodesToWindow();
		 addStylesToNodes();
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(500);
		window.setTitle("Creation du compte epargne");
		root.setSpacing(15);
	
	
	}
	
	//fonction d'ajout d'element a notre fenetre
	private void addNodesToWindow() {
		root.setAlignment(Pos.CENTER);  // Centre tous les éléments du VBox root
		root.getChildren().add(titleLabel);
		
		numeroDuCompteTextField.setMaxWidth(400);
		valeurTextField.setMaxWidth(400);
		tauxPlacementTextField.setMaxWidth(400);
		
		
		root.getChildren().addAll(numeroDuCompteLabel,
				numeroDuCompteTextField,valeurLabel,valeurTextField,
				tauxPlacementLabel,tauxPlacementTextField);
		buttonBox.getChildren().addAll(soumisEpargne,soumisAnnuler);
		buttonBox.setAlignment(Pos.CENTER);
		root.getChildren().add(buttonBox);
		buttonBox.setSpacing(20);
	}
	
	public CompteEpargneForm() {
		System.out.println("Enregistrer");
		initWindow();
		window.show();
	}
	
	
	public void addStylesToNodes(){
		scene.getStylesheets().add("form.css");
		root.getStyleClass().add("root-container");
		titleLabel.getStyleClass().add("title-label");
	
		numeroDuCompteLabel.getStyleClass().add("label");
		
		valeurLabel.getStyleClass().add("label");
		tauxPlacementLabel.getStyleClass().add("label");
		soumisEpargne.getStyleClass().add("submit-button");
		soumisAnnuler.getStyleClass().add("cancel-button");
		
		
		
	}
	
	
	private void addEvent() {
	soumisAnnuler.setOnAction(event->{
		window.close();
	});
	soumisEpargne.setOnAction(event->{
		System.out.println("Traitement pour ajouter");
		
		EpargneAddHandler.addClick();
	
		
	});

}

	public Stage getWindow() {
		return window;
	}

	public void setWindow(Stage window) {
		this.window = window;
	}
 
}
