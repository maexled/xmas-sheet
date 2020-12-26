package de.unistuttgart.iste.rss.oo.tictactoe.ui;


import java.util.HashMap;
import java.util.Map;

import de.unistuttgart.iste.rss.oo.tictactoe.logic.TicTacToeGame;
import de.unistuttgart.iste.rss.oo.tictactoe.model.Player;
import de.unistuttgart.iste.rss.oo.tictactoe.model.Position;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * Creates the content of the UI.
 */
public class TicTacToeFrame extends Parent {

	final private TicTacToeGame game;
	
    /**
     * @param game TicTacToe game linked to this frame.
     */
    public TicTacToeFrame(final TicTacToeGame game) {
		super();
		this.game = game;

        getChildren().add(generateRootNode());
    }

    private Map<Button, Position> positionByButton = new HashMap<>();
    
    private Label playersTurnTextLabel;
    private Label winLabel;

    /**
     * Generates a sample JavaFX {@link Parent} object which displays the tictactoe playfield with additional informations.
     * 
     * @return Generates {@link Parent} object.
     */
    private Parent generateRootNode() {
        final VBox root = new VBox();
        
        // set all the fields (3 x 3)
        for (int y = 0; y < 3; y++) {
        	final TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        	tileButtons.setPadding(new Insets(0, 0, 0, 0));
        	tileButtons.setHgap(5.0);
        	tileButtons.setVgap(0.0);
    		for (int x = 0; x < 3; x++) {
    			Position position = new Position(x, y);
    			Button button = new Button(position.toString());
    			button.setPrefSize(100, 100);
    			button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						buttonClick(button);
					}
				});
    			tileButtons.getChildren().add(button);
    			positionByButton.put(button, position);
    		}
    		root.getChildren().add(tileButtons);
    	} 
        
        final TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
    	tileButtons.setPadding(new Insets(20, 0, 0, 0));
    	tileButtons.setHgap(5.0);
    	tileButtons.setVgap(0.0);
        
        final Label dran = new Label("Es ist dran: ");
        playersTurnTextLabel = new Label("UNKNOWN");
        changePlayersTurn();
        
    	final Button reset = new Button("Reset");
        reset.setPrefSize(80, 40);
        reset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				game.restart();
				getChildren().clear();
				getChildren().add(generateRootNode());
			}
		});
		
        tileButtons.getChildren().addAll(dran, playersTurnTextLabel, reset);
        root.getChildren().add(tileButtons);
        
        winLabel = new Label("");
        winLabel.setPrefSize(300, 40);
        winLabel.setPadding(new Insets(10));
        root.getChildren().add(winLabel);
     
        return root;
    }
    
    private void buttonClick(Button button) {
    	if (game.isFinished()) {
    		return;
    	}
    	final Player playersTurn = game.getPlayField().getPlayersTurn();
    	final Position position = positionByButton.get(button);
    	try {
    		game.place(position);
    		clearWinLabel();
    	} catch (IllegalStateException ex) {
    		alertClickEmptyField();
        	return;
    	}
    	claimButton(button, playersTurn);
    	
    	if (game.getPlayField().hasWon(playersTurn)) {
    		clearPlayerTurnsLabel();
        	alertPlayerWon(playersTurn);
    	} else if (game.getPlayField().isFull()) {
    		clearPlayerTurnsLabel();
        	alertNobodyWon();
    	}  else {
    		changePlayersTurn();
    	}
    }

    /**
     * Claims the button as selected from the player.
     * 
     * @param button that is clicked
     * @param playersTurn who claims the field
     */
	private void claimButton(Button button, final Player playersTurn) {
		button.setText(playersTurn.getCharacter().toString());   	
    	button.setStyle("-fx-background-color: " + playersTurn.getColor());
	}

	/**
	 * Updates the label who is next.
	 */
	private void changePlayersTurn() {
		playersTurnTextLabel.setText(game.getPlayField().getPlayersTurn().getCharacter().toString());
		playersTurnTextLabel.setStyle("-fx-background-color: " + game.getPlayField().getPlayersTurn().getColor());
	}
	
	/**
	 * Show the text that nobody won.
	 */
	private void alertNobodyWon() {
		winLabel.setText("Keiner hat gewonnen!");
		winLabel.setStyle("-fx-font-size: 20; -fx-background-color: #FFC080");
	}

	/**
	 * Show who won the game.
	 * 
	 * @param playersTurn who has won
	 */
	private void alertPlayerWon(final Player playersTurn) {
		winLabel.setText(playersTurn.getCharacter() + " hat gewonnnen!");
		winLabel.setStyle("-fx-font-size: 20; -fx-background-color: " + game.getPlayField().getPlayersTurn().getColor());
	}

	/**
	 * Clears the win label.
	 */
	private void clearWinLabel() {
		winLabel.setText("");
		winLabel.setStyle("");
	}
	
	/**
	 * clears the label who is next.
	 */
	private void clearPlayerTurnsLabel() {
		playersTurnTextLabel.setText("");
		playersTurnTextLabel.setStyle("");
	}

	/**
	 * Alert that the player clicked a field that is already claimed.
	 */
	private void alertClickEmptyField() {
		winLabel.setText("Klicke auf ein leeres Feld!");
		winLabel.setStyle("-fx-background-color: #FFC080");
	}

}
