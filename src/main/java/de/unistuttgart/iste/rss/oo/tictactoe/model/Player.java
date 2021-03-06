package de.unistuttgart.iste.rss.oo.tictactoe.model;

/**
 * Class of Player which contains the players color and the players character.
 * The player can also place his character at a position.
 * 
 * @author Max Kästner
 *
 */
public class Player {
	
	private PlayField playField;
	private String color;
	private Character character;
	
	public Player(PlayField playField, String color, Character character) {
		this.playField = playField;
		this.color = color;
		this.character = character;
	}
	
	/**
	 * 
	 * @return the players character
	 */
	public Character getCharacter() {
		return character;
	}
	
	/**
	 * 
	 * @return the color as hex string
	 */
	public String getColor() {
		return color;
	}
	
	/*@
	 @ requires playField.getAt(position) == null; 
	 @ ensures playField.getAt(position) == this;
	 @*/
	/**
	 * Let the player place the character from him on a field on the given position.
	 * 
	 * @param position where the character should be placed
	 */
	public void place(Position position) {
		playField.placeAt(position, character);
	}
	
	public enum Character {
		X,
		O;
	} 

}
