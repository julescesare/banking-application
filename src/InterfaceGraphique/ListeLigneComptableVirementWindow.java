package InterfaceGraphique;

import java.time.LocalDate;

import Controllers.LigneComptableVirementListHandler;
import classes.LigneComptableVirement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListeLigneComptableVirementWindow {
	
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	
	TableView<LigneComptableVirement> compteTableView = new TableView<>();
	
	private ObservableList<LigneComptableVirement> cpteEpgneObservableList = FXCollections.observableArrayList();
	
	Label titleLabel = new Label("L'Ensembles Des Virements Effectuées"); 
	//creation des columns
	TableColumn<LigneComptableVirement,Long> idColumn = new TableColumn("ID"); 
	TableColumn<LigneComptableVirement,String> themeColumn = new TableColumn("Theme"); 
	TableColumn<LigneComptableVirement,Double> valeurColumn = new TableColumn("Montant"); 
	TableColumn<LigneComptableVirement,LocalDate> dateColumn = new TableColumn("Date De Transaction"); 
	TableColumn<LigneComptableVirement,String> moyenPayementColumn = new TableColumn("Moyen De Payement"); 
	TableColumn<LigneComptableVirement,String> numeroDuCompteExepediteurColumn = new TableColumn("Compte Expediteur");
	TableColumn<LigneComptableVirement,String> numeroDuCompteRecepteurColumn = new TableColumn("Compte Recevant"); 
	TableColumn<LigneComptableVirement,String> motifColumn = new TableColumn("Motif"); 
	
	
	LigneComptableVirementListHandler cpteEpgneListHandeler;
	
	public ListeLigneComptableVirementWindow() {
		super();
	    cpteEpgneListHandeler = new LigneComptableVirementListHandler(this); //pour un acces a notre controllolleur
	
		cpteEpgneListHandeler.getLigneComptableVirement();
		initListeLigneComptableVirementWindow();
		window.show();
	}
	
	public void addColumnToTableView() {
		compteTableView.getColumns().addAll(idColumn,themeColumn,numeroDuCompteExepediteurColumn,numeroDuCompteRecepteurColumn,dateColumn,valeurColumn,motifColumn,moyenPayementColumn);
		compteTableView.setItems(cpteEpgneObservableList);
	}
	
	public void addNodesToPane() {
		root.getChildren().addAll(titleLabel,compteTableView);
		root.setAlignment(Pos.CENTER);
	}
	
	//initialise notre fenetre
	public void initListeLigneComptableVirementWindow() {
		
		addColumnToTableView();
		
		updateColumn();
		addNodesToPane();
		addStylesToNodes();
		window.setScene(scene);
		window.setWidth(1020);
		window.setHeight(600);  
		window.setTitle("L'Ensembles Des Depôts Et Retraits Effectuées");
		
		root.setSpacing(17);
		
	}
	
	public void updateColumn() {
		idColumn.setCellValueFactory(new PropertyValueFactory("id"));
		idColumn.setPrefWidth(50);
		
		
		themeColumn.setCellValueFactory(new PropertyValueFactory("theme"));
		themeColumn.setPrefWidth(150);
		
		numeroDuCompteExepediteurColumn.setCellValueFactory(new PropertyValueFactory("numeroCompte"));
		numeroDuCompteExepediteurColumn.setPrefWidth(150); 
		
		numeroDuCompteRecepteurColumn.setCellValueFactory(new PropertyValueFactory("numeroCompteRecepteur"));
		numeroDuCompteRecepteurColumn.setPrefWidth(150); 
		
		valeurColumn.setCellValueFactory(new PropertyValueFactory("valeur"));
		valeurColumn.setPrefWidth(150);
		
		dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
		dateColumn.setPrefWidth(150);
		
		moyenPayementColumn.setCellValueFactory(new PropertyValueFactory("moyenPayement"));
		moyenPayementColumn.setPrefWidth(150);

		motifColumn.setCellValueFactory(new PropertyValueFactory("motif"));
		motifColumn.setPrefWidth(150);
		
	
		
	}
	
	public void addStylesToNodes(){
		scene.getStylesheets().add("list.css");
		root.getStyleClass().add("root-container");
		titleLabel.getStyleClass().add("title-label");
		compteTableView.getStyleClass().add("table-view");
				
	}
	
	 
	
	
	private void addEvent() {
  
	window.setOnCloseRequest(event->{
		event.consume();
	});//On descative la croit de sortie pour que le user cliqe sur annuler avant de quitter 

}

	public ObservableList<LigneComptableVirement> getCpteEpgneObservableList() {
		return cpteEpgneObservableList;
	}

	public void setCpteEpgneObservableList(ObservableList<LigneComptableVirement> cpteEpgneObservableList) {
		this.cpteEpgneObservableList = cpteEpgneObservableList;
	}
}
