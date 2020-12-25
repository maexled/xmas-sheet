package de.unistuttgart.iste.rss.oo.tictactoe.logic;

import de.unistuttgart.iste.rss.oo.tictactoe.model.PlayField;
import de.unistuttgart.iste.rss.oo.tictactoe.model.Player;
import de.unistuttgart.iste.rss.oo.tictactoe.model.Player.Character;
import de.unistuttgart.iste.rss.oo.tictactoe.model.Position;

/**
 * Game class which manages the playfield and players.
 * Here gets the game started.
 * 
 * @author Max Kästner
 *
 */
public class TicTacToeGame {

	private PlayField playField;
	
	private boolean finished;
	
	public TicTacToeGame() {
		restart();
	}
	
	public void restart() {
		this.playField = new PlayField();
		Player player1 = new Player(this.playField, "#D41F1F", Character.O);
		Player player2 = new Player(this.playField, "#8080FF", Character.X);
		this.playField.addPlayer(player1);
		this.playField.addPlayer(player2);
		finished = false;
	}
	
	public PlayField getPlayField() {
		return playField;
	}
	
	public void place(Position position) {
		Player player = playField.getPlayersTurn();
		player.place(position);
		if (playField.hasWon(player.getCharacter())) {
			finished = true;
		} else if (playField.isFull()) { 
			finished = true;
		} else {
			this.playField.changePlayersTurn();
		}
	}
	
	public boolean isFinished() {
		return finished;
	}
} 
