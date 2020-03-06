import java.util.ArrayList;

public class WhiteKing extends Piece {

	/**
	 * Boolean representing whether or not this king has moved, used for castling
	 * validity
	 */
	private boolean hasMoved;

	/**
	 * Boolean representing whether or not this king is in check
	 */
	private boolean inCheck;

	public WhiteKing(int x, int y) {
		super(x, y);
		hasMoved = false;
		inCheck = false;
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

		if (b.isValid(new Position(p.getX() + 1, p.getY())) && !b.hasWhite(new Position(p.getX() + 1, p.getY())))
			positions.add(new Position(p.getX() + 1, p.getY()));

		// Checks the position to the left

		if (b.isValid(new Position(p.getX() - 1, p.getY())) && !b.hasWhite(new Position(p.getX() - 1, p.getY())))
			positions.add(new Position(p.getX() - 1, p.getY()));

		// Checks the position up

		if (b.isValid(new Position(p.getX(), p.getY() + 1)) && !b.hasWhite(new Position(p.getX(), p.getY() + 1)))
			positions.add(new Position(p.getX(), p.getY() + 1));

		// Checks the position down

		if (b.isValid(new Position(p.getX(), p.getY() - 1)) && !b.hasWhite(new Position(p.getX(), p.getY() - 1)))
			positions.add(new Position(p.getX(), p.getY() - 1));

		// Checks the position to the diagonal up - right

		if (b.isValid(new Position(p.getX() + 1, p.getY() + 1))
				&& !b.hasWhite(new Position(p.getX() + 1, p.getY() + 1)))
			positions.add(new Position(p.getX() + 1, p.getY() + 1));

		// Checks the position to the diagonal down - right

		if (b.isValid(new Position(p.getX() + 1, p.getY() - 1))
				&& !b.hasWhite(new Position(p.getX() + 1, p.getY() - 1)))
			positions.add(new Position(p.getX() + 1, p.getY() - 1));

		// Checks the position to the diagonal up - left

		if (b.isValid(new Position(p.getX() - 1, p.getY() + 1))
				&& !b.hasWhite(new Position(p.getX() - 1, p.getY() + 1)))
			positions.add(new Position(p.getX() - 1, p.getY() + 1));

		// Checks the position to the diagonal down - left

		if (b.isValid(new Position(p.getX() - 1, p.getY() - 1))
				&& !b.hasWhite(new Position(p.getX() - 1, p.getY() - 1)))
			positions.add(new Position(p.getX() - 1, p.getY() - 1));

		// Checks conditions for the castle move
		if (!hasMoved && !inCheck && b.whiteTurn()) {
			// Checks castle conditions to the right
			if (b.isEmpty(new Position(5, 0)) && b.isEmpty(new Position(6, 0))
					&& (b.getType(new Position(7, 0)) instanceof WhiteRook
							&& !((WhiteRook) (b.get(new Position(7, 0)))).hasMoved())) {
				boolean canCastleRight = true;
				for (Position pos : b.blackLocations()) {
					b.get(pos).calculatePossibleMoves(b);
					for (Position mov : b.get(pos).getPossibleMoves(b))
						if (mov.equals(new Position(5, 0)))
							canCastleRight = false;
				}
				if (canCastleRight)
					positions.add(new Position(6, 0));
			}

			// Checks castle conditions to the left
			if (b.isEmpty(new Position(3, 0)) && b.isEmpty(new Position(2, 0)) && b.isEmpty(new Position(1, 0))
					&& (b.getType(new Position(0, 0)) instanceof WhiteRook
							&& !((WhiteRook) (b.get(new Position(0, 0)))).hasMoved())) {
				boolean canCastleLeft = true;
				for (Position pos : b.blackLocations()) {
					b.get(pos).calculatePossibleMoves(b);
					for (Position mov : b.get(pos).getPossibleMoves(b))
						if (mov.equals(new Position(3, 0)))
							canCastleLeft = false;
				}
				if (canCastleLeft)
					positions.add(new Position(2, 0));
			}

		}

		this.possibleMoves = positions;
	}

	/**
	 * Returns this king's move status
	 * 
	 * @return boolean representing whether this king has moved or not
	 */
	public boolean hasMoved() {
		return this.hasMoved;
	}

	/**
	 * Sets this king's move status to true
	 */
	public void moved() {
		hasMoved = true;
	}

	/**
	 * Returns whether this king is in check or not
	 * 
	 * @return boolean representing whether the king is in check
	 */
	public boolean isInCheck() {
		return this.inCheck;
	}

	/**
	 * Sets the king's inCheck value
	 * 
	 * @param inCheck boolean representing whether the king is in check or not
	 */
	public void setCheck(boolean inCheck) {
		this.inCheck = inCheck;
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
		return "resources/WhiteKing.png";
	}

}
