package InterfaceGraphique;

import java.time.LocalDate;

import Controllers.CompteCourantListHandler;

import classes.CompteCourant;

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

public class ListeCompteCourantWindow {
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	
	TableView<CompteCourant> compteTableView = new TableView<>();
	
	private ObservableList<CompteCourant> cpteEpgneObservableList = FXCollections.observableArrayList();
	
	Label titleLabel = new Label("La Liste Des Comptes Courants"); 
	//creation des columns
	TableColumn<CompteCourant,Long> idColumn = new TableColumn("ID"); 
	TableColumn<CompteCourant,String> typeCompteColumn = new TableColumn("Type de compte"); 
	TableColumn<CompteCourant,Double> valeurColumn = new TableColumn("Valeur initiale a creditée"); 
	TableColumn<CompteCourant,LocalDate> dateColumn = new TableColumn("Date de creation"); 
	
	TableColumn<CompteCourant,String> numeroDuCompteColumn = new TableColumn("Le numero du compte"); 
	TableColumn<CompteCourant,HBox> actionColumn = new TableColumn("Actions"); 
	
	CompteCourantListHandler cpteCourantListHandeler;
	public ListeCompteCourantWindow() {
		super();
	    cpteCourantListHandeler = new CompteCourantListHandler(this); //pour un acces a notre controllolleur
	
		cpteCourantListHandeler.getCompteEpargne();
		initListeCompteEpargneWindow();
		window.show();
	}
	
	public void addColumnToTableView() {
		compteTableView.getColumns().addAll(idColumn,typeCompteColumn,numeroDuCompteColumn,dateColumn,valeurColumn);
		compteTableView.setItems(cpteEpgneObservableList);
	}
	
	public void addNodesToPane() {
		root.getChildren().addAll(titleLabel,compteTableView);
		root.setAlignment(Pos.CENTER);
		
	}
	
	//initialise notre fenetre
	public void initListeCompteEpargneWindow() {
		
		addColumnToTableView();
		
		updateColumn();
		addNodesToPane();
		addStylesToNodes();
		addStylesToNodes();
		window.setScene(scene);
		window.setWidth(1000);
		window.setHeight(600);  
		window.setTitle("La liste des comptes Courants");
		
		root.setSpacing(17);
		
	}
	
	public void updateColumn() {
		idColumn.setCellValueFactory(new PropertyValueFactory("id"));
		idColumn.setPrefWidth(50);
		
		typeCompteColumn.setCellValueFactory(new PropertyValueFactory("typeCompte"));
		typeCompteColumn.setPrefWidth(250);
		
		numeroDuCompteColumn.setCellValueFactory(new PropertyValueFactory("numeroDuCompte"));
		numeroDuCompteColumn.setPrefWidth(250);
		
		valeurColumn.setCellValueFactory(new PropertyValueFactory("valeur"));
		valeurColumn.setPrefWidth(250);
		
		dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
		dateColumn.setPrefWidth(250);
		
	
		
		
		
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

	public ObservableList<CompteCourant> getCpteEpgneObservableList() {
		return cpteEpgneObservableList;
	}

	public void setCpteEpgneObservableList(ObservableList<CompteCourant> cpteEpgneObservableList) {
		this.cpteEpgneObservableList = cpteEpgneObservableList;
	}
}
