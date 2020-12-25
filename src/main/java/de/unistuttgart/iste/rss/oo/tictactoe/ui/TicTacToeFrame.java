package de.unistuttgart.iste.rss.oo.tictactoe.ui;

import de.unistuttgart.iste.rss.oo.tictactoe.logic.TicTacToeGame;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * 
 */
public class TicTacToeFrame extends Parent {

    /**
     * @param game TicTacToe game linked to this frame.
     */
    public TicTacToeFrame(final TicTacToeGame game) {
        super();

        getChildren().add(generateRootNode());
    }

    /**
     * Generates a sample JavaFX {@link Parent} object which displays two texts: "Hello Santa" and "Hohoho".
     * 
     * @return Generates {@link Parent} object.
     */
    private Parent generateRootNode() {
        // TODO: This is sample code and not needed for the exercise in general.
        //       It is just here to demonstrate a basic use case of JavaFX.
        final VBox root = new VBox();
        final Text helloSanta = new Text("Hello Santa");
        final Text hohoho = new Text("Hohoho");

        root.setPadding(new Insets(16));
        root.setSpacing(8);
        root.getChildren().addAll(helloSanta, hohoho);
        return root;
    }

}
