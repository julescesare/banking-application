package InterfaceGraphique;

import Controllers.LigneComptableVirementAddHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LigneComptableVirementFrom {



	VBox root = new VBox();
	HBox buttonBox = new HBox();
	public Stage getWindow() {
		return window;
	}

	public void setWindow(Stage window) {
		this.window = window;
	}


	HBox radioTheme = new HBox();
	HBox radioMotif = new HBox();
	HBox radioMoyenPayement = new HBox();
	
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Creer votre  ligne comptable");
	Label themeLabel = new Label("Le theme : ");
	
	Label motifLabel = new Label("Le Motif : ");
	
	Label moyenPayementLabel = new Label("Le Moyen De Payement : ");
	
	Label numeroDuCompteExpediteurLabel = new Label("Numero Compte Expediteur : ");
	TextField numeroDuCompteExepditeurTextField = new TextField();
	Label numeroDuCompteRecepteurLabel = new Label("Numero Compte Recepteur : ");
	TextField numeroDuCompteRecepteurTextField = new TextField();
	Label valeurLabel = new Label("Motant :");
	TextField valeurTextField = new TextField();
	
	//les differents button radio
	
	
	RadioButton salaireRadio = new RadioButton("Salaire");
	RadioButton alimentaireRadio = new RadioButton("Alimentaire");
	RadioButton loyerRadio = new RadioButton("Loyers");
	RadioButton diversRadio = new RadioButton("Divers");
	RadioButton cbRadio = new RadioButton("CB");
	RadioButton virementRadio = new RadioButton("Virement");
	RadioButton chequeRadio = new RadioButton("Cheque");
	
	//groupe de button
	
    private ToggleGroup motifGroup = new ToggleGroup();
  
	
	



	public TextField getNumeroDuCompteExepditeurTextField() {
		return numeroDuCompteExepditeurTextField;
	}

	public void setNumeroDuCompteExepditeurTextField(TextField numeroDuCompteExepditeurTextField) {
		this.numeroDuCompteExepditeurTextField = numeroDuCompteExepditeurTextField;
	}

	public TextField getNumeroDuCompteRecepteurTextField() {
		return numeroDuCompteRecepteurTextField;
	}

	public void setNumeroDuCompteRecepteurTextField(TextField numeroDuCompteRecepteurTextField) {
		this.numeroDuCompteRecepteurTextField = numeroDuCompteRecepteurTextField;
	}

	public TextField getValeurTextField() {
		return valeurTextField;
	}

	public void setValeurTextField(TextField valeurTextField) {
		this.valeurTextField = valeurTextField;
	}


	Button soumisLigne = new Button("envoie");
	Button soumisAnnuler = new Button("Annuler");
	
	
	LigneComptableVirementAddHandler LigneAddHandler = new LigneComptableVirementAddHandler(this);
	
	
	//fonction de manipulation de la fentre
	private void initWindow() {
		addEvent();
		addNodesToWindow();
		addStylesToNodes();
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(600);
		window.setTitle("Effectuer le virement");
		root.setSpacing(15);
	
	
	}
	
	//fonction d'ajout d'element a notre fenetre
	private void addNodesToWindow() {
		
		// Assignation des groupes aux boutons radio
      
		root.setAlignment(Pos.CENTER);  // Centre tous les éléments du VBox root
		
		radioMotif.setAlignment(Pos.CENTER);
		radioTheme.setAlignment(Pos.CENTER);
		radioMoyenPayement.setAlignment(Pos.CENTER);
		
        salaireRadio.setToggleGroup(getMotifGroup());
        alimentaireRadio.setToggleGroup(getMotifGroup());
        loyerRadio.setToggleGroup(getMotifGroup());
        diversRadio.setToggleGroup(getMotifGroup());


      
        radioMotif.getChildren().addAll(salaireRadio, loyerRadio, alimentaireRadio, diversRadio);
        numeroDuCompteExepditeurTextField.setMaxWidth(400);
        numeroDuCompteRecepteurTextField.setMaxWidth(400);
        valeurTextField.setMaxWidth(400);
        root.getChildren().add(titleLabel);
      
        root.getChildren().addAll(numeroDuCompteExpediteurLabel, numeroDuCompteExepditeurTextField
        		,numeroDuCompteRecepteurLabel, numeroDuCompteRecepteurTextField);
        root.getChildren().addAll(motifLabel, radioMotif);
        root.getChildren().addAll(valeurLabel, valeurTextField);
        
        buttonBox.getChildren().addAll(soumisLigne, soumisAnnuler);
        root.getChildren().add(buttonBox);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(20);
	}
	
	// Méthode pour récupérer le texte du RadioButton sélectionné dans un groupe
    public String getSelectedRadioText(ToggleGroup group) {
        RadioButton selectedRadio = (RadioButton) group.getSelectedToggle();
        return selectedRadio != null ? selectedRadio.getText() : null;
    }
	public LigneComptableVirementFrom() {
		System.out.println("Enregistrer");
		initWindow();
		window.show();
	}
	
	
	public void addStylesToNodes(){
		scene.getStylesheets().add("form.css");
		root.getStyleClass().add("root-container");
		titleLabel.getStyleClass().add("title-label");
		motifLabel.getStyleClass().add("label");
		numeroDuCompteExpediteurLabel.getStyleClass().add("label");
		numeroDuCompteRecepteurLabel.getStyleClass().add("label");
		valeurLabel.getStyleClass().add("label");
		radioMotif.getStyleClass().add("radio-box");
		salaireRadio.getStyleClass().add("radio-button");
		alimentaireRadio.getStyleClass().add("radio-button");
		loyerRadio.getStyleClass().add("radio-button");
		diversRadio.getStyleClass().add("radio-button");
		soumisLigne.getStyleClass().add("submit-button");
		soumisAnnuler.getStyleClass().add("cancel-button");

		
		
	}
	
	
	private void addEvent() {
	soumisAnnuler.setOnAction(event->{
		window.close();
	});
	soumisLigne.setOnAction(event->{
		System.out.println("Traitement pour ajouter");
		
		LigneAddHandler.addClick();
		
	
	});
	
}


	public Label getNumeroDuCompteExpediteurLabel() {
		return numeroDuCompteExpediteurLabel;
	}

	public void setNumeroDuCompteExpediteurLabel(Label numeroDuCompteExpediteurLabel) {
		this.numeroDuCompteExpediteurLabel = numeroDuCompteExpediteurLabel;
	}

	public Label getNumeroDuCompteRecepteurLabel() {
		return numeroDuCompteRecepteurLabel;
	}

	public void setNumeroDuCompteRecepteurLabel(Label numeroDuCompteRecepteurLabel) {
		this.numeroDuCompteRecepteurLabel = numeroDuCompteRecepteurLabel;
	}

	public ToggleGroup getMotifGroup() {
		return motifGroup;
	}

	public void setMotifGroup(ToggleGroup motifGroup) {
		this.motifGroup = motifGroup;
	}

	
}
