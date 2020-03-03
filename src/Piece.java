import java.util.ArrayList;

@SuppressWarnings("rawtypes")
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
	 * Holds current board in use
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
	public ArrayList<Position> getPossibleMoves(Board b){
		return this.possibleMoves;
	}

	public abstract void calculatePossibleMoves(Board b);

	/**
	 * Implemented in all sub-classes
	 * 
	 * @return If piece is White
	 */
	public abstract boolean isWhite();

	/**
	 * Implemented in all sub-classes
	 * 
	 * @return If piece is Black
	 */
	public abstract boolean isBlack();

	/**
	 * Implemented in sub-classes
	 */
	public String toString() {
		return "";
	}
}