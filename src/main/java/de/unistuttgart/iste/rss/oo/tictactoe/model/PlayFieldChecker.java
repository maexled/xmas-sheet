package de.unistuttgart.iste.rss.oo.tictactoe.model;

import de.unistuttgart.iste.rss.oo.tictactoe.model.Player.Character;

/**
 * PlayFieldChecker class which checks if a characater has at least one streak or not.
 * 
 * @author Max Kästner
 *
 */
public class PlayFieldChecker {
	
	private final String[][] field;
	private final Character character;
	private final int needToWin;
	
	public PlayFieldChecker(final String[][] field, final Character character, final int needToWin) {
		this.field = field;
		this.character = character;
		this.needToWin = needToWin;
	}
	
	/**
	 * Check if there is one streak with the character on the field.
	 * 
	 * @return wether there is one streak with the character or not.
	 */
	public boolean isOneStreak() {
		return checkVertical() || checkDiagonal() || checkDiagonal();
	}
	
	/**
	 * 
	 * @return wether the is one streak with the character on the vertical.
	 */
	private boolean checkVertical() {
		for (int x = 0; x < PlayField.FIELD_WIDTH; x++) {
			int fields = 0;
			for (int y = 0; y < PlayField.FIELD_HEIGHT; y++) {
				if (this.field[x][y] == character.toString()) {
					fields++;
				} 
			}
			if (fields == needToWin) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return wether the is one streak with the character on the horizontal.
	 */
	private boolean checkHorizontal() {
		for (int y = 0; y < PlayField.FIELD_HEIGHT; y++) {
			int fields = 0;
			for (int x = 0; x < PlayField.FIELD_WIDTH; x++) {
				if (this.field[x][y] == character.toString()) {
					fields++;
				} 
			}
			if (fields == needToWin) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return wether the is one streak with the character on the diagonal.
	 */
	private boolean checkDiagonal() {
		int fields = 0;
		for (int x = 0; x < PlayField.FIELD_WIDTH; x++) {
			if (this.field[x][x] == character.toString()) {
				fields++;
			}
			if (fields == 3) {
				return true;
			}
		}
		fields = 0;
		for (int x = 0; x < PlayField.FIELD_WIDTH; x++) {
			if (this.field[x][PlayField.FIELD_HEIGHT - 1 - x] == character.toString()) {
				fields++;
			}
			if (fields == 3) {
				return true;
			}
		}
		return false;
	}
 
}
