import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class WhiteBishop extends Piece {

	public WhiteBishop(int x, int y) {
		super(x, y);
	}

	/**
	 * Returns the current possible moves for this piece
	 * 
	 * @return ArrayList<Position> the ArrayList of all possible positions
	 * @param b the board of the piece
	 */
	public ArrayList<Position> getPossibleMoves(Board b) {
		ArrayList<Position> positions = new ArrayList<Position>();
		Position p = new Position(this.getPos().getX(), this.getPos().getY());
		int max = 1;

		// Positions to the diagonal up - right
		do {
			p.setX(p.getX() + 1);
			p.setY(p.getY() + 1);
			max++;
		} while (b.isEmpty(p) && b.isValid(p) && !b.hasWhite(p));
		for (int i = 0; i < max; i++)
			positions.add(new Position(this.getPos().getX() + i, this.getPos().getY() + i));

		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;

		// Positions to the diagonal down - left
		do {
			p.setX(p.getX() - 1);
			p.setY(p.getY() - 1);
			max++;
		} while (b.isEmpty(p) && b.isValid(p) && !b.hasWhite(p));
		for (int i = max - 1; i >= 0; i--)
			positions.add(new Position(this.getPos().getX() - i, this.getPos().getY() - i));

		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;

		// Positions to the diagonal up - left
		do {
			p.setX(p.getX() + 1);
			p.setY(p.getY() - 1);
			max += 1;
		} while (b.isEmpty(p) && b.isValid(p) && !b.hasWhite(p));
		for (int i = 0; i < max; i++)
			positions.add(new Position(this.getPos().getX() + i, this.getPos().getY() - i));

		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;

		// Positions to the diagonal down - right
		do {
			p.setX(p.getX() - 1);
			p.setY(p.getY() + 1);
			max++;
		} while (b.isEmpty(p) && b.isValid(p) && !b.hasWhite(p));
		for (int i = 0; i < max; i++)
			positions.add(new Position(this.getPos().getX() - i, this.getPos().getY() + i));

		for (int i = 0; i < positions.size() - 1; i++) {
			if (b.isValid(positions.get(i))) {
				if (b.get(positions.get(i)) instanceof BlackKing) {
					((BlackKing) b.get(positions.get(i))).setCheck(true);
				}
			}
		}

		return positions;
	}

	@Override
	public boolean isWhite() {
		return true;
	}

	@Override
	public boolean isBlack() {
		return false;
	}

	public String toString() {
		return "WhiteBishop.png";
	}

}
