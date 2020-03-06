import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class BlackKing extends Piece {

	/**
	 * Stores whether or not the king has moved, used to determine castling validity
	 */
	private boolean hasMoved;

	/**
	 * Creates a black king at (x, y)
	 * 
	 * @param x the x location of the king
	 * @param y the y location of the king
	 */
	public BlackKing(int x, int y) {
		super(x, y);
		hasMoved = false;
	}

	/**
	 * Calculates the current possible moves for this piece
	 * 
	 * @param b the board on which to calculate moves
	 */
	public void calculatePossibleMoves(Board b) {
		ArrayList<Position> positions = new ArrayList<Position>();
		Position p = new Position(this.getPos().getX(), this.getPos().getY());

		// Checks the position to the right

		if (b.isValid(new Position(p.getX() + 1, p.getY())) && !b.hasBlack(new Position(p.getX() + 1, p.getY())))
			positions.add(new Position(p.getX() + 1, p.getY()));

		// Checks the position to the left

		if (b.isValid(new Position(p.getX() - 1, p.getY())) && !b.hasBlack(new Position(p.getX() - 1, p.getY())))
			positions.add(new Position(p.getX() - 1, p.getY()));

		// Checks the position up

		if (b.isValid(new Position(p.getX(), p.getY() + 1)) && !b.hasBlack(new Position(p.getX(), p.getY() + 1)))
			positions.add(new Position(p.getX(), p.getY() + 1));

		// Checks the position down

		if (b.isValid(new Position(p.getX(), p.getY() - 1)) && !b.hasBlack(new Position(p.getX(), p.getY() - 1)))
			positions.add(new Position(p.getX(), p.getY() - 1));

		// Checks the position to the diagonal up - right

		if (b.isValid(new Position(p.getX() + 1, p.getY() + 1))
				&& !b.hasBlack(new Position(p.getX() + 1, p.getY() + 1)))
			positions.add(new Position(p.getX() + 1, p.getY() + 1));

		// Checks the position to the diagonal down - right

		if (b.isValid(new Position(p.getX() + 1, p.getY() - 1))
				&& !b.hasBlack(new Position(p.getX() + 1, p.getY() - 1)))
			positions.add(new Position(p.getX() + 1, p.getY() - 1));

		// Checks the position to the diagonal up - left

		if (b.isValid(new Position(p.getX() - 1, p.getY() + 1))
				&& !b.hasBlack(new Position(p.getX() - 1, p.getY() + 1)))
			positions.add(new Position(p.getX() - 1, p.getY() + 1));

		// Checks the position to the diagonal down - left

		if (b.isValid(new Position(p.getX() - 1, p.getY() - 1))
				&& !b.hasBlack(new Position(p.getX() - 1, p.getY() - 1)))
			positions.add(new Position(p.getX() - 1, p.getY() - 1));

		// Checks conditions for the castle move
		if (!hasMoved) {
			// Checks if the spaces to the right are empty and that the rook to the right
			// also has not moved
			if (b.isEmpty(new Position(5, 7)) && b.isEmpty(new Position(6, 7))
					&& b.getType(new Position(7, 7)) instanceof BlackRook
					&& !((BlackRook) (b.get(new Position(7, 7)))).hasMoved())
				positions.add(new Position(6, 7));
			// Checks if the spaces to the left are empty and that the rook to the left also
			// has not moved
			if (b.isEmpty(new Position(3, 7)) && b.isEmpty(new Position(2, 7)) && b.isEmpty(new Position(1, 7))
					&& b.getType(new Position(0, 7)) instanceof BlackRook
					&& !((BlackRook) (b.get(new Position(0, 7)))).hasMoved())
				positions.add(new Position(2, 7));

		}
		this.possibleMoves = positions;

	}

	/**
	 * Sets the king's has moved to true
	 */
	public void moved() {
		hasMoved = true;
	}

	/**
	 * Returns whether the king has moved
	 * 
	 * @return boolean representing the king's move status
	 */
	public boolean hasMoved() {
		return hasMoved;
	}

	@Override
	public boolean isWhite() {
		return false;
	}

	@Override
	public boolean isBlack() {
		return true;
	}

	@Override
	public String toString() {
		return "resources/BlackKing.png";
	}

}
