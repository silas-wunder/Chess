import java.util.ArrayList;

public abstract class Piece {
	/**
	 * Holds x and y position of piece
	 */
	private Position pos;

	/**
	 * Holds all possible moves for this piece
	 */
	protected ArrayList<Position> possibleMoves;

	/**
	 * Creates a new piece at (x, y)
	 * 
	 * @param x the x location of the piece
	 * @param y the y location of the piece
	 */
	Piece(int x, int y) {
		this.pos = new Position(x, y);
	}

	/**
	 * @return Position of piece
	 */
	public Position getPos() {
		return this.pos;
	}

	/**
	 * Defines how each piece can move
	 * 
	 * @param b Board containing piece
	 * @return ArrayList of all possible Positions
	 */
	public ArrayList<Position> getPossibleMoves(Board b) {
		return this.possibleMoves;
	}

	/**
	 * Calculates the possible moves for this piece, implemented in sub-classes
	 * 
	 * @param b the board to calculate the moves on
	 */
	public abstract void calculatePossibleMoves(Board b);

	/**
	 * Returns whether the piece is white or not, implemented in sub-classes
	 * 
	 * @return If piece is White
	 */
	public abstract boolean isWhite();

	/**
	 * Returns whether the piece is black or not, implemented in sub-classes
	 * 
	 * @return If piece is Black
	 */
	public abstract boolean isBlack();

	/**
	 * The string representation of this piece, used to grab to image of the piece
	 */
	public String toString() {
		return "";
	}
}