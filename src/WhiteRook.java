import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class WhiteRook extends Piece {

	private boolean hasMoved;

	/**
	 * Constructor setting Position of rook
	 * 
	 * @param x x Coordinate
	 * @param y y Coordinate
	 */
	WhiteRook(int x, int y) {
		super(x, y);
		hasMoved = false;
	}

	/**
	 * Returns the current possible moves for this piece
	 * 
	 * @return ArrayList<Position> the ArrayList of all possible positions
	 * @param b the board of the piece
	 */
	public void calculatePossibleMoves(Board b) {
		ArrayList<Position> positions = new ArrayList<Position>();
		Position p = new Position(this.getPos().getX(), this.getPos().getY());
		int max = 1;

		// Forward Positions
		p.setY(p.getY() + 1);
		while (b.isValid(p) && !b.hasWhite(p)) {
			max++;
			if (b.hasBlack(p))
				break;
			p.setY(p.getY() + 1);
		}
		for (int i = 1; i < max; i++) {
			positions.add(new Position(this.getPos().getX(), this.getPos().getY() + i));
		}

		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;

		// Positions to the Right
		p.setX(p.getX() + 1);
		while (b.isValid(p) && !b.hasWhite(p)) {
			max++;
			if (b.hasBlack(p))
				break;
			p.setX(p.getX() + 1);
		}
		for (int i = 1; i < max; i++) {
			positions.add(new Position(this.getPos().getX() + i, this.getPos().getY()));
		}

		max = 1;
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());

		// Positions to the Left
		p.setX(p.getX() - 1);
		while (b.isValid(p) && !b.hasWhite(p)) {
			max++;
			if (b.hasBlack(p))
				break;
			p.setX(p.getX() - 1);
		}
		for (int i = max - 1; i > 0; i--) {
			positions.add(new Position(this.getPos().getX() - i, this.getPos().getY()));
		}

		max = 1;
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());

		// Positions Behind
		p.setY(p.getY() - 1);
		while (b.isValid(p) && !b.hasWhite(p)) {
			max++;
			if (b.hasBlack(p))
				break;
			p.setY(p.getY() - 1);
		}
		for (int i = max - 1; i > 0; i--) {
			positions.add(new Position(this.getPos().getX(), this.getPos().getY() - i));
		}

		for (int i = 0; i < positions.size() - 1; i++) {
			if (b.isValid(positions.get(i))) {
				if (b.get(positions.get(i)) instanceof BlackKing) {
					((BlackKing) b.get(positions.get(i))).setCheck(true);
				}
			}
		}

		this.possibleMoves = positions;
	}

	public void moved() {
		hasMoved = true;
	}

	public boolean hasMoved() {
		return hasMoved;
	}

	public String toString() {
		return "resources/WhiteRook.png";
	}

	@Override
	public boolean isWhite() {
		return true;
	}

	@Override
	public boolean isBlack() {
		return false;
	}
}
