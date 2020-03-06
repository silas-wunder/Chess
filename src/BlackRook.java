import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class BlackRook extends Piece {

	/**
	 * Boolean representing whether the rook has moved or not, used for castling
	 * validity
	 */
	private boolean hasMoved;

	/**
	 * Creates a black rook at (x, y)
	 * 
	 * @param x the x location of this rook
	 * @param y the y location of this rook
	 */
	BlackRook(int x, int y) {
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
		int max = 1;

		// Positions up
		p.setY(p.getY() + 1);
		while (b.isValid(p) && !b.hasBlack(p)) {
			max++;
			if (b.hasWhite(p))
				break;
			p.setY(p.getY() + 1);
		}
		for (int i = 1; i < max; i++) {
			positions.add(new Position(this.getPos().getX(), this.getPos().getY() + i));
		}

		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;

		// Positions to the right
		p.setX(p.getX() + 1);
		while (b.isValid(p) && !b.hasBlack(p)) {
			max++;
			if (b.hasWhite(p))
				break;
			p.setX(p.getX() + 1);
		}
		for (int i = 1; i < max; i++) {
			positions.add(new Position(this.getPos().getX() + i, this.getPos().getY()));
		}

		max = 1;
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());

		// Positions to the left
		p.setX(p.getX() - 1);
		while (b.isValid(p) && !b.hasBlack(p)) {
			max++;
			if (b.hasWhite(p))
				break;
			p.setX(p.getX() - 1);
			max++;
		}
		for (int i = max - 1; i > 0; i--) {
			positions.add(new Position(this.getPos().getX() - i, this.getPos().getY()));
		}

		max = 1;
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());

		// Positions down
		p.setY(p.getY() - 1);
		while (b.isValid(p) && !b.hasBlack(p)) {
			max++;
			if (b.hasWhite(p))
				break;
			p.setY(p.getY() - 1);
		}
		for (int i = max - 1; i > 0; i--) {
			positions.add(new Position(this.getPos().getX(), this.getPos().getY() - i));
		}

		this.possibleMoves = positions;
	}

	/**
	 * Sets this rook's move status to true
	 */
	public void moved() {
		hasMoved = true;
	}

	/**
	 * Returns this rook's moved status
	 * 
	 * @return boolean representing whether this rook has moved or not
	 */
	public boolean hasMoved() {
		return hasMoved;
	}

	@Override
	public String toString() {
		return "resources/BlackRook.png";
	}

	@Override
	public boolean isWhite() {
		return false;
	}

	@Override
	public boolean isBlack() {
		return true;
	}

}
