package dulio;

import dulio.GUI.DialogBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Displays the initial Dulio JavaFX window.
 */
public class DulioApp extends Application {
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 600;
    private static final String USER_IMAGE_PATH = "/images/account-avatar-profile-user-svgrepo-com.png";
    private static final String DULIO_IMAGE_PATH = "/images/account-avatar-profile-user-3-svgrepo-com.png";

    private VBox dialogContainer;
    private TextField userInput;
    private Image userImage;
    private Image dulioImage;

    /**
     * Creates and displays the initial Dulio window.
     *
     * @param stage the primary stage for the application
     */
    @Override
    public void start(Stage stage) {
        dialogContainer = new VBox(8);
        dialogContainer.setPadding(new Insets(12));

        userImage = loadImage(USER_IMAGE_PATH);
        dulioImage = loadImage(DULIO_IMAGE_PATH);
        dialogContainer.getChildren().addAll(
            new DialogBox("Hello!", userImage),
            new DialogBox("Hello! I'm Dulio. What can I do for you?", dulioImage));

        ScrollPane scrollPane = new ScrollPane(dialogContainer);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput = new TextField();
        userInput.setPrefWidth(325.0);
        userInput.setPromptText("Type a command");
        userInput.setOnAction(event -> sendMessage());

        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(55.0);
        sendButton.setDefaultButton(true);
        sendButton.setOnAction(event -> sendMessage());

        HBox inputBar = new HBox(8, userInput, sendButton);
        inputBar.setAlignment(Pos.CENTER);
        inputBar.setPadding(new Insets(10));
        HBox.setHgrow(userInput, Priority.ALWAYS);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(scrollPane);
        mainLayout.setBottom(inputBar);

        mainLayout.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        Scene scene = new Scene(mainLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle("Dulio");
        stage.setResizable(false);
        stage.setMinHeight(WINDOW_HEIGHT);
        stage.setMinWidth(WINDOW_WIDTH);
        stage.setScene(scene);
        stage.show();
    }

    private void sendMessage() {
        String message = userInput.getText().trim();
        if (message.isEmpty()) {
            return;
        }

        dialogContainer.getChildren().addAll(
            new DialogBox(message, userImage),
            new DialogBox("I received your command.", dulioImage));
        userInput.clear();
    }

    private Image loadImage(String path) {
        return new Image(getClass().getResourceAsStream(path));
    }
}
