package InterfaceGraphique;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlaideWindow {

	public AlaideWindow() {
		System.out.println("de l'aide");
	  initWindow();
	  window.show();
	}
	
	VBox root = new VBox();
	Stage window = new Stage();
	Scene scene = new Scene(root);
	Label descriptionLabel= new Label("Voici une explication detaillee :  \n  ");
	Label descriptionCompte = new Label ("1- Le menu compte : \n Il est composer de deux sous menus creer un compte et La liste \n des "
			+ "comptes chacun d'eux a aussi son sous menu");
	Label descriptionLigneComptable = new Label("2- Le menu Liste Comptable : \n Vous pourrez ainsi creer  votre ligne \n comptable et par la même occasion les afficher");
	Label descriptionAide = new Label("3- Le menu Aide :\n Trouver de l'aide pour un comprehension du programme ");
	Label descriptionSortie = new Label("4- Le menu sortie :\n Pour quitter le programme");
	public void addNodesToWindow() {
		root.getChildren().addAll(descriptionLabel,descriptionCompte,descriptionLigneComptable,descriptionAide,descriptionSortie);
	}
	public void initWindow() {
		addNodesToWindow();
		addStyleToWindow();
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(600);
		window.setTitle("Aide");
		root.setSpacing(20);
		root.setAlignment(Pos.CENTER);
	}
	
	public void addStyleToWindow() {
        scene.getStylesheets().add("Aide.css");  // Charger le fichier CSS
        descriptionLabel.getStyleClass().add("description-label");
        descriptionCompte.getStyleClass().add("description-text");
        descriptionLigneComptable.getStyleClass().add("description-text");
        descriptionAide.getStyleClass().add("description-text");
        descriptionSortie.getStyleClass().add("description-text");
    }
	private void addEvent() {
		
	}
	
}
