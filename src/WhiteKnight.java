import java.util.ArrayList;

public class WhiteKnight extends Piece {

	/**
	 * Makes a white knight at (x, y)
	 * 
	 * @param x the x location of the knight
	 * @param y the y location of the knight
	 */
	public WhiteKnight(int x, int y) {
		super(x, y);
	}

	/**
	 * Calculates the current possible moves for this piece
	 * 
	 * @param b the board on which to calculate moves
	 */
	@Override
	public void calculatePossibleMoves(Board b) {
		Position p = new Position(this.getPos().getX(), this.getPos().getY());
		ArrayList<Position> positions = new ArrayList<Position>();
		if (b.isValid(new Position(p.getX() - 1, p.getY() + 2))
				&& !b.hasWhite(new Position(p.getX() - 1, p.getY() + 2)))
			positions.add(new Position(p.getX() - 1, p.getY() + 2));
		if (b.isValid(new Position(p.getX() + 1, p.getY() + 2))
				&& !b.hasWhite(new Position(p.getX() + 1, p.getY() + 2)))
			positions.add(new Position(p.getX() + 1, p.getY() + 2));
		if (b.isValid(new Position(p.getX() - 1, p.getY() - 2))
				&& !b.hasWhite(new Position(p.getX() - 1, p.getY() - 2)))
			positions.add(new Position(p.getX() - 1, p.getY() - 2));
		if (b.isValid(new Position(p.getX() + 1, p.getY() - 2))
				&& !b.hasWhite(new Position(p.getX() + 1, p.getY() - 2)))
			positions.add(new Position(p.getX() + 1, p.getY() - 2));
		if (b.isValid(new Position(p.getX() - 2, p.getY() + 1))
				&& !b.hasWhite(new Position(p.getX() - 2, p.getY() + 1)))
			positions.add(new Position(p.getX() - 2, p.getY() + 1));
		if (b.isValid(new Position(p.getX() - 2, p.getY() - 1))
				&& !b.hasWhite(new Position(p.getX() - 2, p.getY() - 1)))
			positions.add(new Position(p.getX() - 2, p.getY() - 1));
		if (b.isValid(new Position(p.getX() + 2, p.getY() + 1))
				&& !b.hasWhite(new Position(p.getX() + 2, p.getY() + 1)))
			positions.add(new Position(p.getX() + 2, p.getY() + 1));
		if (b.isValid(new Position(p.getX() + 2, p.getY() - 1))
				&& !b.hasWhite(new Position(p.getX() + 2, p.getY() - 1)))
			positions.add(new Position(p.getX() + 2, p.getY() - 1));

		this.possibleMoves = positions;
	}

	@Override
	public boolean isWhite() {
		return true;
	}

	@Override
	public boolean isBlack() {
		return false;
	}

	@Override
	public String toString() {
		return "resources/WhiteKnight.png";
	}

}
