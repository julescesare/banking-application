package InterfaceGraphique;

import Controllers.LigneComptableDepotAddHandler;
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

public class LigneComptableDepotFrom {



	VBox root = new VBox();
	HBox buttonBox = new HBox();
	HBox radioTheme = new HBox();
	HBox radioMotif = new HBox();
	HBox radioMoyenPayement = new HBox();
	
	Scene scene = new Scene(root);
	Stage window = new Stage();
	public Stage getWindow() {
		return window;
	}

	public void setWindow(Stage window) {
		this.window = window;
	}


	Label titleLabel = new Label("Creer votre  ligne comptable");
	Label themeLabel = new Label("Le theme : ");
	
	Label motifLabel = new Label("Le Motif : ");
	
	Label moyenPayementLabel = new Label("Le Moyen De Payement : ");
	
	Label numeroDuCompteLabel = new Label("Numero Compte Associé : ");
	TextField numeroDuCompteTextField = new TextField();
	Label valeurLabel = new Label("Motant :");
	TextField valeurTextField = new TextField();
	
	//les differents button radio
	RadioButton depotRadio = new RadioButton("Depot");
	RadioButton retraitRadio = new RadioButton("Retrait");
	RadioButton salaireRadio = new RadioButton("Salaire");
	RadioButton alimentaireRadio = new RadioButton("Alimentaire");
	RadioButton loyerRadio = new RadioButton("Loyers");
	RadioButton diversRadio = new RadioButton("Divers");
	RadioButton cbRadio = new RadioButton("CB");
	//RadioButton virementRadio = new RadioButton("Virement");
	RadioButton chequeRadio = new RadioButton("Cheque");
	
	//groupe de button
	private ToggleGroup themeGroup = new ToggleGroup();
    private ToggleGroup motifGroup = new ToggleGroup();
    private ToggleGroup moyenPayementGroup = new ToggleGroup();
	
	

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


	Button soumisLigne = new Button("envoie");
	Button soumisAnnuler = new Button("Annuler");
	
	
	LigneComptableDepotAddHandler LigneAddHandler = new LigneComptableDepotAddHandler(this);
	
	
	//fonction de manipulation de la fentre
	private void initWindow() {
		addEvent();
		addNodesToWindow();
		addStylesToNodes();
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(600);
		window.setTitle("Creation de le ligne comptable");
		root.setSpacing(15);
	
	
	}
	
	//fonction d'ajout d'element a notre fenetre
	private void addNodesToWindow() {
		root.setAlignment(Pos.CENTER);  // Centre tous les éléments du VBox root
		
		radioMotif.setAlignment(Pos.CENTER);
		radioTheme.setAlignment(Pos.CENTER);
		radioMoyenPayement.setAlignment(Pos.CENTER);
		
		
		// Assignation des groupes aux boutons radio
        depotRadio.setToggleGroup(getThemeGroup());
        retraitRadio.setToggleGroup(getThemeGroup());

        salaireRadio.setToggleGroup(getMotifGroup());
        alimentaireRadio.setToggleGroup(getMotifGroup());
        loyerRadio.setToggleGroup(getMotifGroup());
        diversRadio.setToggleGroup(getMotifGroup());

        cbRadio.setToggleGroup(getMoyenPayementGroup());
        //virementRadio.setToggleGroup(getMoyenPayementGroup());
        chequeRadio.setToggleGroup(getMoyenPayementGroup());

        radioTheme.getChildren().addAll(depotRadio, retraitRadio);
        radioMotif.getChildren().addAll(salaireRadio, loyerRadio, alimentaireRadio, diversRadio);
        radioMoyenPayement.getChildren().addAll( chequeRadio, cbRadio);
        
        numeroDuCompteTextField.setMaxWidth(400);
        valeurTextField.setMaxWidth(400);
        root.getChildren().add(titleLabel);
        root.getChildren().addAll(themeLabel, radioTheme);
        root.getChildren().addAll(numeroDuCompteLabel, numeroDuCompteTextField);
        root.getChildren().addAll(motifLabel, radioMotif);
        root.getChildren().addAll(valeurLabel, valeurTextField);
        root.getChildren().addAll(moyenPayementLabel, radioMoyenPayement);
        buttonBox.getChildren().addAll(soumisLigne, soumisAnnuler);
        buttonBox.setAlignment(Pos.CENTER);
        root.getChildren().add(buttonBox);

        buttonBox.setSpacing(20);
	}
	
	// Méthode pour récupérer le texte du RadioButton sélectionné dans un groupe
    public String getSelectedRadioText(ToggleGroup group) {
        RadioButton selectedRadio = (RadioButton) group.getSelectedToggle();
        return selectedRadio != null ? selectedRadio.getText() : null;
    }
	public LigneComptableDepotFrom() {
		System.out.println("Enregistrer");
		initWindow();
		window.show();
	}
	
	
	public void addStylesToNodes(){
		scene.getStylesheets().add("form.css");
		root.getStyleClass().add("root-container");
		titleLabel.getStyleClass().add("title-label");
		motifLabel.getStyleClass().add("label");
		numeroDuCompteLabel.getStyleClass().add("label");
		
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

	public ToggleGroup getThemeGroup() {
		return themeGroup;
	}

	public void setThemeGroup(ToggleGroup themeGroup) {
		this.themeGroup = themeGroup;
	}

	public ToggleGroup getMotifGroup() {
		return motifGroup;
	}

	public void setMotifGroup(ToggleGroup motifGroup) {
		this.motifGroup = motifGroup;
	}

	public ToggleGroup getMoyenPayementGroup() {
		return moyenPayementGroup;
	}

	public void setMoyenPayementGroup(ToggleGroup moyenPayementGroup) {
		this.moyenPayementGroup = moyenPayementGroup;
	}
	
}
