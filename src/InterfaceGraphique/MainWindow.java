package InterfaceGraphique;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import Models.CompteCourantDAO;
import Models.CompteEpargneDAO;
import Models.CompteJoinDAO;
import Models.LigneComptableDepotDAO;
import Models.LigneComptableVirementDAO;

public class MainWindow extends Application {

    BorderPane root = new BorderPane();
    Scene scene = new Scene(root);
    HBox hbox = new HBox();
    HBox hboxsecond = new HBox();
    Stage window = new Stage();
    MenuItem creerCompteMenuItem = new MenuItem("Ajouter un compte");
    MenuItem afficherCompteMenuItem = new MenuItem("Liste des comptes");
    MenuItem creerLigneCompteDepotMenuItem = new MenuItem("Depot/retrait");
    MenuItem creerLigneCompteVirementMenuItem = new MenuItem("Virement");
    MenuItem afficherLigneCompteDepotMenuItem = new MenuItem("Depot/retrait ");
    MenuItem afficherLigneCompteVirementMenuItem = new MenuItem("Virement");
    MenuItem creerCompteEpargneItem = new MenuItem("Epargne");
    MenuItem creerCompteCourantItem = new MenuItem("Courant");
    MenuItem creerCompteJointItem = new MenuItem("Join");
    MenuItem afficherCompteEpargneMenuItem = new MenuItem("Epargne");
    MenuItem afficherCompteCourantMenuItem = new MenuItem("Courant");
    MenuItem afficherCompteJoinMenuItem = new MenuItem("Join");
    MenuItem sortieMenuItem = new MenuItem("Sortie");
    MenuItem aLaideMenuItem = new MenuItem("a l'aide ");
    MenuItem  rechercheMenuItem = new MenuItem(" Effectuer Une recherche ");
    MenuBar menuBar = new MenuBar();
    Menu creerCompteMenu = new Menu("Ajouter un compte");
    Menu afficherCompteMenu = new Menu("Liste des comptes");
    Menu creerLigneCompteMenu = new Menu("Ajouter une ligne de ");
    Menu afficherLigneCompteMenu = new Menu("Liste des lignes de ");
    Menu compteMenu = new Menu("Compte");
    Menu ligneComptableMenu = new Menu("Ligne comptable");
    Menu aideMenu = new Menu("Aide");
    Menu sortieMenu = new Menu("Sortie");
    Menu rechercheMenu = new Menu("Recherche");
    
    Stage wind;
    CompteJoinDAO compteJoin = new CompteJoinDAO();
    CompteEpargneDAO compteEpargne = new CompteEpargneDAO();
    CompteCourantDAO compteCourant = new CompteCourantDAO();
    LigneComptableDepotDAO depotRetrait = new LigneComptableDepotDAO();
    LigneComptableVirementDAO virement = new LigneComptableVirementDAO();
    Set<Date> datesRecup = new TreeSet<>();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Group diagrammeCData;
    private Group graphiqueAire;
    Group bandes;
    Group grahiqueAireMontant;
    HBox buttonBox = new HBox();
    Button refresh = new Button("Actualiser");

    public void start(Stage primaryStage) {
        window = primaryStage; // Utiliser primaryStage
        creationDashboad();
        createMenu();
        addEvent();
        addStylesToNodes();
        window.setScene(scene);
        window.setWidth(1100);
        window.setHeight(697);
        window.setTitle("Banque");
        window.show();
    }

    public void addStylesToNodes() {
        scene.getStylesheets().add("style.css");
        root.getStyleClass().add("mainWindows");
        menuBar.getStyleClass().add("menu-bar");
        creerCompteMenu.getStyleClass().add("menu");
        afficherCompteMenu.getStyleClass().add("menu");
        creerLigneCompteMenu.getStyleClass().add("menu");
        afficherLigneCompteMenu.getStyleClass().add("menu");
        aideMenu.getStyleClass().add("menu");
        sortieMenu.getStyleClass().add("menu");
        rechercheMenu.getStyleClass().add("menu");
        
        creerCompteEpargneItem.getStyleClass().add("menu-item");
        afficherCompteEpargneMenuItem.getStyleClass().add("menu-item");
        creerCompteCourantItem.getStyleClass().add("menu-item");
        afficherCompteCourantMenuItem.getStyleClass().add("menu-item");
        creerCompteJointItem.getStyleClass().add("menu-item");
        afficherCompteJoinMenuItem.getStyleClass().add("menu-item");
        sortieMenuItem.getStyleClass().add("menu-item");
        aLaideMenuItem.getStyleClass().add("menu-item");
        rechercheMenuItem.getStyleClass().add("menu-item");
        creerLigneCompteDepotMenuItem.getStyleClass().add("menu-item");
        creerLigneCompteVirementMenuItem.getStyleClass().add("menu-item");
        afficherLigneCompteDepotMenuItem.getStyleClass().add("menu-item");
        afficherLigneCompteVirementMenuItem.getStyleClass().add("menu-item");
        refresh.getStyleClass().add("button");
    }

    public void createMenu() {
        sortieMenu.getItems().add(sortieMenuItem);
        aideMenu.getItems().add(aLaideMenuItem);
        creerCompteMenu.getItems().addAll(creerCompteCourantItem, creerCompteEpargneItem, creerCompteJointItem);
        afficherCompteMenu.getItems().addAll(afficherCompteCourantMenuItem, afficherCompteEpargneMenuItem, afficherCompteJoinMenuItem);
        creerLigneCompteMenu.getItems().addAll(creerLigneCompteDepotMenuItem, creerLigneCompteVirementMenuItem);
        afficherLigneCompteMenu.getItems().addAll(afficherLigneCompteDepotMenuItem, afficherLigneCompteVirementMenuItem);
        rechercheMenu.getItems().add(rechercheMenuItem);
        compteMenu.getItems().addAll(creerCompteMenu, afficherCompteMenu);
        ligneComptableMenu.getItems().addAll(creerLigneCompteMenu, afficherLigneCompteMenu);
        menuBar.getMenus().addAll(compteMenu, ligneComptableMenu, aideMenu, rechercheMenu,sortieMenu);
        root.setTop(menuBar);
    }

    public void creationDashboad() {
        diagrammeCirculaire();
        diagrammeAire();
        diagrammeBande();
        diagrammeAireMontant();
        buttonBox.getChildren().add(refresh);
        buttonBox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(diagrammeCData, graphiqueAire, buttonBox);
        hbox.setSpacing(15);
        hbox.setAlignment(Pos.CENTER);
        hboxsecond.getChildren().addAll(bandes, grahiqueAireMontant);
        hboxsecond.setAlignment(Pos.CENTER);
        root.setCenter(hbox);
        root.setBottom(hboxsecond);
    }

    public void diagrammeCirculaire() {
        ObservableList<PieChart.Data> compteStateData = FXCollections.observableArrayList(
                new PieChart.Data("Compte Epargne", compteEpargne.nombreCompte()),
                new PieChart.Data("Compte Courant", compteCourant.nombreCompte()),
                new PieChart.Data("Compte Join", compteJoin.nombreCompte())
        );
        PieChart diagrammeCirculaire = new PieChart(compteStateData);
        diagrammeCirculaire.setTitle("Les Proportions Des Comptes");
        diagrammeCirculaire.setClockwise(true);
        diagrammeCirculaire.setLabelsVisible(false);
        diagrammeCirculaire.setStartAngle(180);
        diagrammeCirculaire.setPrefSize(400, 300);
        this.diagrammeCData = new Group(diagrammeCirculaire);
    }

    public void diagrammeAire() {
        CategoryAxis datexAxis = new CategoryAxis();
        NumberAxis nombreCompteYAxis = new NumberAxis(0, 6, 1);
        nombreCompteYAxis.setLabel("Nbre crees");
        LineChart linechart = new LineChart(datexAxis, nombreCompteYAxis);
        linechart.setTitle("Les comptes creer par date");
        linechart.setPrefSize(400, 290);
        // Appelons notre tree de date afin d'avoir les mêmes coordonnées
        this.treeDate();
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Compte Join");
       
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Compte Courant");
       
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Compte Epargne");
      
        for(Date d: this.datesRecup) {
        	if(compteJoin.nombreCompteParDate(d)!=0 || compteCourant.nombreCompteParDate(d)!=0 || compteEpargne.nombreCompteParDate(d)!=0) {
        		 series1.getData().add(new XYChart.Data<>(d.toLocalDate().format(dateFormatter), compteJoin.nombreCompteParDate(d)));
        		 series2.getData().add(new XYChart.Data<>(d.toLocalDate().format(dateFormatter), compteCourant.nombreCompteParDate(d)));
        		 series3.getData().add(new XYChart.Data<>(d.toLocalDate().format(dateFormatter), compteEpargne.nombreCompteParDate(d)));
        	}
        }
        linechart.getData().addAll(series1, series2, series3);
        this.graphiqueAire = new Group(linechart);
    }

    public void diagrammeAireMontant() {
        CategoryAxis datexAxis = new CategoryAxis();
        NumberAxis nombreCompteYAxis = new NumberAxis(0, 20000000, 100000);
        nombreCompteYAxis.setLabel("Montants");
        AreaChart areaChart = new AreaChart(datexAxis, nombreCompteYAxis);
        areaChart.setTitle("Evolutions Financieres");
        areaChart.setPrefSize(400, 290);
        // Appelons notre tree de date afin d'avoir les mêmes coordonnées
        this.treeDate();
        
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Les Courants");
        double montantCumuleCourant = 0;   
        
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Les Epargnes");
        double montantCumuleEpargne = 0;

        
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Les Joins");
        double montantCumuleJoin = 0;
      
        for(Date d: this.datesRecup) {
        	
        	if(compteCourant.montantTotalParDate(d)!=0 || compteEpargne.montantTotalParDate(d)!=0 ||  compteJoin.montantTotalParDate(d) !=0) {
        		
        		Double montantTotalCourant = compteCourant.montantTotalParDate(d);
                montantCumuleCourant += montantTotalCourant;
                series1.getData().add(new XYChart.Data<>(d.toLocalDate().format(dateFormatter), montantCumuleCourant));
                
                Double montantTotalEpargne = compteEpargne.montantTotalParDate(d) ;
                montantCumuleEpargne += montantTotalEpargne;
                series2.getData().add(new XYChart.Data<>(d.toLocalDate().format(dateFormatter), montantCumuleEpargne));
        	
                Double montantTotalJoin = compteJoin.montantTotalParDate(d) ;
                montantCumuleJoin += montantTotalJoin;
                series3.getData().add(new XYChart.Data<>(d.toLocalDate().format(dateFormatter), montantCumuleJoin));
        	}
        }
        
        areaChart.getData().addAll(series1,series2,series3);
        this.grahiqueAireMontant = new Group(areaChart);
    }

    // Fonction pour trier les dates
    public void treeDate() {
        compteCourant.recupDate();
        compteEpargne.recupDate();
        compteJoin.recupDate();
        for (Date d : compteCourant.getDatesBase()) {
            this.datesRecup.add(d);
        }
        for (Date d : compteJoin.getDatesBase()) {
            this.datesRecup.add(d);
        }
        for (Date d : compteEpargne.getDatesBase()) {
            this.datesRecup.add(d);
        }
    }

    // Fonction pour le diagramme en bande
    public void diagrammeBande() {
        this.treeDate();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Dates");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Score");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Statistic des differentes transactions");
        barChart.setPrefSize(400, 300);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Virement");
      
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Depot");
       
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Retrait");
       
        for(Date d: this.datesRecup) {
        	if(depotRetrait.nombreTransactionsDepot(d)!=0 || depotRetrait.nombreTransactionsRetrait(d)!=0 || virement.nombreCompteParDate(d)!=0) {
        		 series1.getData().add(new XYChart.Data<>(d.toLocalDate().format(dateFormatter), depotRetrait.nombreTransactionsDepot(d)));
        		 series2.getData().add(new XYChart.Data<>(d.toLocalDate().format(dateFormatter), virement.nombreCompteParDate(d)));
        		 series3.getData().add(new XYChart.Data<>(d.toLocalDate().format(dateFormatter),  depotRetrait.nombreTransactionsRetrait(d)));
        	}
        }
        barChart.getData().addAll(series1, series2, series3);
        this.bandes = new Group(barChart);
    }

    private void addEvent() {
        creerCompteEpargneItem.setOnAction(event -> {
            System.out.println("Nouvelle fenetre d'enregistrement de compte epargne");
            new CompteEpargneForm();
        });
        afficherCompteEpargneMenuItem.setOnAction(event -> new ListeCompteEpargneWindow());
        creerCompteCourantItem.setOnAction(event -> {
            System.out.println("Nouvelle fenetre d'enregistrement de compte quelconque");
            new CompteCourantForm();
        });
        afficherCompteCourantMenuItem.setOnAction(event -> new ListeCompteCourantWindow());
        sortieMenu.setOnAction(event -> {
            System.out.println("Sortie");
            Platform.exit();
        });
        aLaideMenuItem.setOnAction(event -> new AlaideWindow());
        creerLigneCompteDepotMenuItem.setOnAction(event -> {
            System.out.println("Creation de notre ligne");
            new LigneComptableDepotFrom();
        });
        afficherCompteCourantMenuItem.setOnAction(event -> new ListeCompteCourantWindow());
        creerCompteJointItem.setOnAction(event -> {
            System.out.println("Creation de notre compte join");
            new CompteJoinForm();
        });
        afficherCompteJoinMenuItem.setOnAction(event -> {
            System.out.println("Affichage join");
            new ListeCompteJoinWindow();
        });
        creerLigneCompteVirementMenuItem.setOnAction(event -> {
            System.out.println("ligne virement");
            new LigneComptableVirementFrom();
        });
        refresh.setOnAction(event -> {
            window.close();
            new MainWindow().start(window);
        });
        rechercheMenuItem.setOnAction(event->{
        	new RechercheForm();
        });
        afficherLigneCompteDepotMenuItem.setOnAction(event -> new ListeLigneComptableDepotWindow());
        afficherLigneCompteVirementMenuItem.setOnAction(event -> new ListeLigneComptableVirementWindow());
    }
}
