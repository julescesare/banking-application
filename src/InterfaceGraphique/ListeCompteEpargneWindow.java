package InterfaceGraphique;

import java.time.LocalDate;

import Controllers.CompteEpargneListHandler;
import classes.CompteEpargne;
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

public class ListeCompteEpargneWindow {
	
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	
	TableView<CompteEpargne> compteTableView = new TableView<>();
	
	private ObservableList<CompteEpargne> cpteEpgneObservableList = FXCollections.observableArrayList();
	
	Label titleLabel = new Label("La Liste Des Comptes Epargnes"); 
	//creation des columns
	TableColumn<CompteEpargne,Long> idColumn = new TableColumn("ID"); 
	TableColumn<CompteEpargne,String> typeCompteColumn = new TableColumn("Type de compte"); 
	TableColumn<CompteEpargne,Double> valeurColumn = new TableColumn("Valeur initiale a creditée"); 
	TableColumn<CompteEpargne,LocalDate> dateColumn = new TableColumn("Date de creation"); 
	TableColumn<CompteEpargne,Double> tauxPlacementColumn = new TableColumn("Taux de placement"); 
	TableColumn<CompteEpargne,String> numeroDuCompteColumn = new TableColumn("Le numero du compte"); 
	
	
	CompteEpargneListHandler cpteEpgneListHandeler;
	public ListeCompteEpargneWindow() {
		super();
	    cpteEpgneListHandeler = new CompteEpargneListHandler(this); //pour un acces a notre controllolleur
	
		cpteEpgneListHandeler.getCompteEpargne();
		initListeCompteEpargneWindow();
		window.show();
	}
	
	public void addColumnToTableView() {
		compteTableView.getColumns().addAll(idColumn,typeCompteColumn,numeroDuCompteColumn,dateColumn,valeurColumn,tauxPlacementColumn);
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
		window.setTitle("La liste des comptes epargnes");
		
		root.setSpacing(17);
		
	}
	
	public void updateColumn() {
		idColumn.setCellValueFactory(new PropertyValueFactory("id"));
		idColumn.setPrefWidth(50);
		
		typeCompteColumn.setCellValueFactory(new PropertyValueFactory("typeCompte"));
		typeCompteColumn.setPrefWidth(200);
		
		numeroDuCompteColumn.setCellValueFactory(new PropertyValueFactory("numeroDuCompte"));
		numeroDuCompteColumn.setPrefWidth(200); 
		
		valeurColumn.setCellValueFactory(new PropertyValueFactory("valeur"));
		valeurColumn.setPrefWidth(200);
		
		dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
		dateColumn.setPrefWidth(200);
		
		tauxPlacementColumn.setCellValueFactory(new PropertyValueFactory("tauxPlacement"));
		tauxPlacementColumn.setPrefWidth(150);
		
	
		
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

	public ObservableList<CompteEpargne> getCpteEpgneObservableList() {
		return cpteEpgneObservableList;
	}

	public void setCpteEpgneObservableList(ObservableList<CompteEpargne> cpteEpgneObservableList) {
		this.cpteEpgneObservableList = cpteEpgneObservableList;
	}
}
