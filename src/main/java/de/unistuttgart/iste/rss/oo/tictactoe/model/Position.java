package de.unistuttgart.iste.rss.oo.tictactoe.model;

/**
 * Position class which contains x and y.
 * 
 * @author Max Kästner
 *
 */
public class Position {
	
	private final int x;
	private final int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean equals(Position position) {
		if (position.getX() == x && position.getY() == y) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "[" + x + " / " + y + "]";
	}
}