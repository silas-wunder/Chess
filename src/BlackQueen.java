import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class BlackQueen extends Piece {

	public BlackQueen(int x, int y) {
		super(x, y);
	}

	/**
	 * Returns the current possible moves for this piece
	 * 
	 * TODO: This is adding invalid moves to the move set
	 * 
	 * @return ArrayList<Position> the ArrayList of all possible positions
	 * @param b the board of the piece
	 */
	public void calculatePossibleMoves(Board b) {
		ArrayList<Position> positions = new ArrayList<Position>();
		Position p = new Position(this.getPos().getX(), this.getPos().getY());
		int max = 1;

		// Positions to the diagonal up - right
		p.setX(p.getX() + 1);
		p.setY(p.getY() + 1);
		while (b.isValid(p) && !b.hasBlack(p)) {
			max++;
			if (b.hasWhite(p))
				break;
			p.setX(p.getX() + 1);
			p.setY(p.getY() + 1);
		}
		for (int i = 1; i < max; i++)
			positions.add(new Position(this.getPos().getX() + i, this.getPos().getY() + i));

		// Reset p and max
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;

		// Positions to the diagonal down - left
		p.setX(p.getX() - 1);
		p.setY(p.getY() - 1);
		while (b.isValid(p) && !b.hasBlack(p)) {
			max++;
			if (b.hasWhite(p))
				break;
			p.setX(p.getX() - 1);
			p.setY(p.getY() - 1);
		}
		for (int i = max - 1; i > 0; i--)
			positions.add(new Position(this.getPos().getX() - i, this.getPos().getY() - i));

		// Reset p and max
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;

		// Positions to the diagonal up - left
		p.setX(p.getX() + 1);
		p.setY(p.getY() - 1);
		while (b.isValid(p) && !b.hasBlack(p)) {
			max++;
			if (b.hasWhite(p))
				break;
			p.setX(p.getX() + 1);
			p.setY(p.getY() - 1);
		}
		for (int i = 1; i < max; i++)
			positions.add(new Position(this.getPos().getX() + i, this.getPos().getY() - i));

		// Reset p and max
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;

		// Positions to the diagonal down - right
		p.setX(p.getX() - 1);
		p.setY(p.getY() + 1);
		while (b.isValid(p) && !b.hasBlack(p)) {
			max++;
			if (b.hasWhite(p))
				break;
			p.setX(p.getX() - 1);
			p.setY(p.getY() + 1);
		}
		for (int i = 1; i < max; i++)
			positions.add(new Position(this.getPos().getX() - i, this.getPos().getY() + i));

		// Reset p and max
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;

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

		// Reset p and max
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

		// Reset p and max
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;

		// Positions to the left
		p.setX(p.getX() - 1);
		while (b.isValid(p) && !b.hasBlack(p)) {
			max++;
			if (b.hasWhite(p))
				break;
			p.setX(p.getX() - 1);
		}
		for (int i = max - 1; i > 0; i--) {
			positions.add(new Position(this.getPos().getX() - i, this.getPos().getY()));
		}

		// Reset p and max
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;

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
		return "resources/BlackQueen.png";
	}

}
