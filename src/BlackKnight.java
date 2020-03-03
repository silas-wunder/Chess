import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class BlackKnight extends Piece {

	/**
	 * Makes a Knight object
	 * 
	 * @param x the x position of the knight
	 * @param y the y position of the knight
	 */
	public BlackKnight(int x, int y) {
		super(x, y);
	}

	/**
	 * Returns the current possible moves for the knight
	 * 
	 * @return ArrayList<Position> the ArrayList of all possible positions
	 * @param b the board of the piece
	 */
	public void calculatePossibleMoves(Board b) {
		Position p = new Position(this.getPos().getX(), this.getPos().getY());
		ArrayList<Position> positions = new ArrayList<Position>();
		if (b.isValid(new Position(p.getX() - 1, p.getY() + 2))
				&& !b.hasBlack(new Position(p.getX() - 1, p.getY() + 2)))
			positions.add(new Position(p.getX() - 1, p.getY() + 2));
		if (b.isValid(new Position(p.getX() + 1, p.getY() + 2))
				&& !b.hasBlack(new Position(p.getX() + 1, p.getY() + 2)))
			positions.add(new Position(p.getX() + 1, p.getY() + 2));
		if (b.isValid(new Position(p.getX() - 1, p.getY() - 2))
				&& !b.hasBlack(new Position(p.getX() - 1, p.getY() - 2)))
			positions.add(new Position(p.getX() - 1, p.getY() - 2));
		if (b.isValid(new Position(p.getX() + 1, p.getY() - 2))
				&& !b.hasBlack(new Position(p.getX() + 1, p.getY() - 2)))
			positions.add(new Position(p.getX() + 1, p.getY() - 2));
		if (b.isValid(new Position(p.getX() - 2, p.getY() + 1))
				&& !b.hasBlack(new Position(p.getX() - 2, p.getY() + 1)))
			positions.add(new Position(p.getX() - 2, p.getY() + 1));
		if (b.isValid(new Position(p.getX() - 2, p.getY() - 1))
				&& !b.hasBlack(new Position(p.getX() - 2, p.getY() - 1)))
			positions.add(new Position(p.getX() - 2, p.getY() - 1));
		if (b.isValid(new Position(p.getX() + 2, p.getY() + 1))
				&& !b.hasBlack(new Position(p.getX() + 2, p.getY() + 1)))
			positions.add(new Position(p.getX() + 2, p.getY() + 1));
		if (b.isValid(new Position(p.getX() + 2, p.getY() - 1))
				&& !b.hasBlack(new Position(p.getX() + 2, p.getY() - 1)))
			positions.add(new Position(p.getX() + 2, p.getY() - 1));

		for (int i = 0; i < positions.size() - 1; i++) {
			if (b.isValid(positions.get(i))) {
				if (b.get(positions.get(i)) instanceof WhiteKing) {
					((WhiteKing) b.get(positions.get(i))).setCheck(true);
				}
			}
		}

		this.possibleMoves = positions;
	}

	@Override
	public boolean isWhite() {
		return false;
	}

	@Override
	public boolean isBlack() {
		return true;
	}

	public String toString() {
		return "resources/BlackKnight.png";
	}

}
