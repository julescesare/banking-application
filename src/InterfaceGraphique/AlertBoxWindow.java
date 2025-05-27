package InterfaceGraphique;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlertBoxWindow {

    VBox root = new VBox(); // Crée un conteneur VBox pour organiser les éléments verticalement
    Scene scene = new Scene(root); // Crée une nouvelle scène avec le VBox comme racine
    Stage window = new Stage(); // Crée une nouvelle fenêtre (Stage)

    /**
     * Constructeur de la classe AlertBoxWindow.
     * Affiche une boîte d'alerte avec un titre et un message donnés.
     * 
     * @param titre   Le titre de l'alerte (peut être "Erreur" ou "Succes")
     * @param message Le message à afficher dans l'alerte
     */
    public AlertBoxWindow(String titre, String message) {
        System.out.println("alert");
        initWindow(titre, message); // Initialise la fenêtre d'alerte
    }

    /**
     * Initialise la fenêtre d'alerte en fonction du titre et du message donnés.
     * 
     * @param titre   Le titre de l'alerte (peut être "Erreur" ou "Succes")
     * @param message Le message à afficher dans l'alerte
     */
    private void initWindow(String titre, String message) {
        Alert alert;
        
        if (titre.equals("Erreur")) {
            alert = new Alert(AlertType.ERROR);
            alert.getDialogPane().getStyleClass().add("alert-error");
        } else if (titre.equals("Succes")) {
            alert = new Alert(AlertType.INFORMATION);
            alert.getDialogPane().getStyleClass().add("alert-success");
        } else {
            alert = new Alert(AlertType.NONE);
        }
        
        alert.setTitle(titre); // Définit le titre de la fenêtre d'alerte
        alert.setHeaderText(null); // Pas d'en-tête pour l'alerte
        alert.setContentText(message); // Définit le message de contenu
        
        // Ajouter la feuille de style CSS
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/Alert.css").toExternalForm());

        
        alert.showAndWait(); // Affiche l'alerte et attend la réponse de l'utilisateur
    }
}
