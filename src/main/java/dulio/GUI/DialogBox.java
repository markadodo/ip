package dulio.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/** Displays a message alongside the image of its sender. */
public class DialogBox extends HBox {
    private static final double IMAGE_SIZE = 100.0;
    private static final double MESSAGE_MAX_WIDTH = 240.0;

    /**
     * Creates a dialog box containing a message and sender image.
     *
     * @param message the message to display
     * @param image the sender image to display
     */
    public DialogBox(String message, Image image) {
        Label text = new Label(message);
        text.setWrapText(true);
        text.setMaxWidth(MESSAGE_MAX_WIDTH);
        text.setStyle("-fx-background-color: #e8eef5; -fx-background-radius: 12;"
            + " -fx-padding: 10 14 10 14;");

        ImageView displayPicture = new ImageView(image);
        displayPicture.setFitWidth(IMAGE_SIZE);
        displayPicture.setFitHeight(IMAGE_SIZE);
        displayPicture.setPreserveRatio(true);

        setSpacing(12);
        setAlignment(Pos.TOP_RIGHT);
        setPadding(new Insets(6, 8, 6, 8));
        setMaxWidth(Double.MAX_VALUE);
        getChildren().addAll(text, displayPicture);
    }
}