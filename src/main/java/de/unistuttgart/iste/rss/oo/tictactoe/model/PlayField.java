package de.unistuttgart.iste.rss.oo.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

import de.unistuttgart.iste.rss.oo.tictactoe.model.Player.Character;
import javafx.geometry.Pos;

/**
 * Class of Playfield which manages a playfield..
 * 
 * @author Max Kästner
 *
 */
public class PlayField {
	
	public final static int FIELD_WIDTH = 3;
	public final static int FIELD_HEIGHT = 3;
	
	private List<Player> players;
	
	private Player playersTurn = null;
	
	private final String[][] field;
	
	public PlayField() {
		this.field = new String[FIELD_WIDTH][FIELD_HEIGHT];
		this.players = new ArrayList<>();
	}
	
	/*@
	 @ requires players.size() >= 2;
	 @ ensures players.contains(player);
	 @*/
	/**
	 * Add a player to the playfield.
	 * 
	 * Attention that one playfield can only has maximum of 2 players.
	 * 
	 * @param player that should be added to the playfield.
	 * @throws IllegalStateException when there are already more than 2 players on the playfield
	 */
	public void addPlayer(final Player player) {
		if (players.size() >= 2) {
			throw new IllegalStateException("This field has already 2 players.");
		}
		players.add(player);
		if (this.playersTurn == null) {
			this.playersTurn = player;
		}
	}
	
	/**
	 * 
	 * @return the player whose turn it is
	 */
	public Player getPlayersTurn() {
		return playersTurn;
	}
	
	/**
	 * Change the players turn to another player.
	 */
	public void changePlayersTurn() {
		for (Player player : players) {
			if (playersTurn != player) {
				playersTurn = player;
				return;
			}
		}
		throw new IllegalStateException("Could not change the players turn to another player");
	}
	
	/*@
	 @  requires (position.getX() < FIELD_WIDTH && position.getX() >= 0) 
	 @			&& (position.getY() < FIELD_HEIGHT && position.getY() >= 0);
 @*/
	/**
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @return the player whose character is on the coordinate. Return null if it is empty.
	 */
	public Player getAt(final Position position) {
		if ((position.getX() >= FIELD_WIDTH || position.getX() < 0) 
				|| (position.getY() >= FIELD_HEIGHT || position.getY() < 0)) {
			throw new IllegalArgumentException("The x or y coordinate from the postion is not in the playfield");
		}
		final String characterString =  this.field[position.getX()][position.getY()];
		if (characterString == null) {
			return null;
		}
		for (Player player : players) {
			if (player.getCharacter().toString() == characterString) {
				return player;
			}
		}
		return null;
	}
	
	/*@
	 @ requires this.field[position.getX()][position.getY()] == null;
	 @ ensures this.field[position.getX()][position.getY()] == character.toString();
	 @*/
	/**
	 * Place a character at a specific position on the playfield.
	 * 
	 * @param position that should the character placed at
	 * @param character that should be placed at the position
	 * 
	 * @throws IllegalStateException when on the position is already a character.
	 */
	public void placeAt(final Position position, final Character character) {
		if (this.field[position.getX()][position.getY()] != null) {
			throw new IllegalStateException("On this field is already a character");
		}
		if (this.playersTurn.getCharacter() != character) {
			throw new IllegalStateException("How can you place the character? It is not your turn!");
		}
		this.field[position.getX()][position.getY()] = character.toString();
	}
	
	/**
	 * Check if a character won.
	 * 
	 * @param character that should be checked.
	 * @return wether the character has won or not.
	 */
	public boolean hasWon(final Player player) {
		final Character character = player.getCharacter();
		PlayFieldChecker playFieldChecker = new PlayFieldChecker(field, player.getCharacter(), 3);
		return playFieldChecker.isOneStreak();
	}
	
	/**
	 * Check if all fields are already filled with a character.
	 * 
	 * @return wether all fields are already filled with a character or not.
	 */
	public boolean isFull() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}

}
