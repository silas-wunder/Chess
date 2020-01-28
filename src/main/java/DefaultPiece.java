package src.main.java;
import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class DefaultPiece extends Piece{

	DefaultPiece(int x, int y) {
		super(x, y);
	}

	/**
	 * Can't move
	 */
	public ArrayList<Position> getPossibleMoves(Board b) {
		return null;
	}

	@Override
	public boolean isWhite() {
		return false;
	}

	@Override
	public boolean isBlack() {
		return false;
	}

	
	
}
