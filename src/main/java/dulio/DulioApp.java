package dulio;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import dulio.gui.MainWindow;

/**
 * Displays the initial Dulio JavaFX window.
 */
public class DulioApp extends Application {
    private Dulio dulio = new Dulio();

    /**
     * Creates and displays the initial Dulio window.
     *
     * @param stage the primary stage for the application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DulioApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane mainLayout = fxmlLoader.load();
            Scene scene = new Scene(mainLayout);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDulio(dulio);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
