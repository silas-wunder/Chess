import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class WhiteBishop extends Piece {

	/**
	 * Creates a white bishop at (x, y)
	 * 
	 * @param x the x location of this bishop
	 * @param y the y location of this bishop
	 */
	public WhiteBishop(int x, int y) {
		super(x, y);
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

		// Positions to the diagonal up - right
		p.setX(p.getX() + 1);
		p.setY(p.getY() + 1);
		while (b.isValid(p) && !b.hasWhite(p)) {
			max ++;
			if(b.hasBlack(p))
				break;
			p.setX(p.getX() + 1);
			p.setY(p.getY() + 1);
		}
		for (int i = 1; i < max; i++)
			positions.add(new Position(this.getPos().getX() + i, this.getPos().getY() + i));

		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;

		// Positions to the diagonal down - left
		p.setX(p.getX() - 1);
		p.setY(p.getY() - 1);
		while (b.isValid(p) && !b.hasWhite(p)) {
			max ++;
			if(b.hasBlack(p))
				break;
			p.setX(p.getX() - 1);
			p.setY(p.getY() - 1);
		}
		for (int i = max - 1; i > 0; i--)
			positions.add(new Position(this.getPos().getX() - i, this.getPos().getY() - i));

		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;

		// Positions to the diagonal up - left
		p.setX(p.getX() + 1);
		p.setY(p.getY() - 1);
		while (b.isValid(p) && !b.hasWhite(p)) {
			max ++;
			if(b.hasBlack(p))
				break;
			p.setX(p.getX() + 1);
			p.setY(p.getY() - 1);
		}
		for (int i = 1; i < max; i++)
			positions.add(new Position(this.getPos().getX() + i, this.getPos().getY() - i));

		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;

		// Positions to the diagonal down - right
		p.setX(p.getX() - 1);
		p.setY(p.getY() + 1);
		while (b.isValid(p) && !b.hasWhite(p)) {
			max ++;
			if(b.hasBlack(p))
				break;
			p.setX(p.getX() - 1);
			p.setY(p.getY() + 1);
		}
		for (int i = 1; i < max; i++)
			positions.add(new Position(this.getPos().getX() - i, this.getPos().getY() + i));

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
		return "resources/WhiteBishop.png";
	}

}
