
public class DefaultPiece extends Piece {

	/**
	 * Creates a new defualt piece at (x, y)
	 * @param x the x location of the defualt piece
	 * @param y the y location of the default piece
	 */
	DefaultPiece(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isWhite() {
		return false;
	}

	@Override
	public boolean isBlack() {
		return false;
	}

	@Override
	public void calculatePossibleMoves(Board b) {
		// Can't move, so possible moves is null
		this.possibleMoves = null;
	}
}
