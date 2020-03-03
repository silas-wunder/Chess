
@SuppressWarnings("rawtypes")
public class DefaultPiece extends Piece {

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
