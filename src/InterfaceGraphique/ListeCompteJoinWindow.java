package InterfaceGraphique;

import java.time.LocalDate;

import Controllers.CompteJoinListHandler;

import classes.CompteJoin;

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

public class ListeCompteJoinWindow {
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	
	TableView<CompteJoin> compteTableView = new TableView<>();
	
	private ObservableList<CompteJoin> cpteEpgneObservableList = FXCollections.observableArrayList();
	
	Label titleLabel = new Label("La Liste Des Comptes Joins"); 
	//creation des columns
	TableColumn<CompteJoin,Long> idColumn = new TableColumn("ID"); 
	TableColumn<CompteJoin,String> typeCompteColumn = new TableColumn("Type de compte"); 
	TableColumn<CompteJoin,Double> valeurColumn = new TableColumn("Valeur initiale a creditée"); 
	TableColumn<CompteJoin,LocalDate> dateColumn = new TableColumn("Date de creation"); 
	
	TableColumn<CompteJoin,String> numeroDuComptePremierColumn = new TableColumn("Le numero du compte 1"); 
	TableColumn<CompteJoin,String> numeroDuCompteSecondColumn = new TableColumn("Le numero du compte 2"); 
	TableColumn<CompteJoin,HBox> actionColumn = new TableColumn("Actions"); 
	
	CompteJoinListHandler cpteJoinListHandeler;
	public ListeCompteJoinWindow() {
		super();
	    cpteJoinListHandeler = new CompteJoinListHandler(this); //pour un acces a notre controllolleur
	
		cpteJoinListHandeler.getCompteJoin();
		initListeCompteEpargneWindow();
		window.show();
	}
	
	public void addColumnToTableView() {
		compteTableView.getColumns().addAll(idColumn,typeCompteColumn,numeroDuComptePremierColumn,numeroDuCompteSecondColumn,dateColumn,valeurColumn);
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
		window.setScene(scene);
		window.setWidth(1020);
		window.setHeight(600);  
		window.setTitle("La Liste Des Comptes Joins");
		
		root.setSpacing(17);
		
	}
	
	public void updateColumn() {
		idColumn.setCellValueFactory(new PropertyValueFactory("id"));
		idColumn.setPrefWidth(50);
		
		typeCompteColumn.setCellValueFactory(new PropertyValueFactory("typeCompte"));
		typeCompteColumn.setPrefWidth(200);
		
		numeroDuComptePremierColumn.setCellValueFactory(new PropertyValueFactory("numeroDuCompte"));
		numeroDuComptePremierColumn.setPrefWidth(200);

		numeroDuCompteSecondColumn.setCellValueFactory(new PropertyValueFactory("numeroCompte2"));
		numeroDuCompteSecondColumn.setPrefWidth(200);
		
		valeurColumn.setCellValueFactory(new PropertyValueFactory("valeur"));
		valeurColumn.setPrefWidth(200);
		
		dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
		dateColumn.setPrefWidth(200);
		
	
		
		
		
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

	public ObservableList<CompteJoin> getCpteEpgneObservableList() {
		return cpteEpgneObservableList;
	}

	public void setCpteEpgneObservableList(ObservableList<CompteJoin> cpteEpgneObservableList) {
		this.cpteEpgneObservableList = cpteEpgneObservableList;
	}
}
