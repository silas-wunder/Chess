import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class WhitePawn extends Piece {

	private boolean hasMoved;
	private boolean canLeftPassant;
	private boolean canRightPassant;

	/**
	 * Makes a Pawn piece
	 * 
	 * @param x the x position of the pawn
	 * @param y the y position of the pawn
	 */
	WhitePawn(int x, int y) {
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
		// Checks the left diagonal position to see if there is a piece there
		if (b.isValid(new Position(p.getX() - 1, p.getY() + 1)) && b.hasBlack(new Position(p.getX() - 1, p.getY() + 1))
				|| this.canLeftPassant)
			positions.add(new Position(p.getX() - 1, p.getY() + 1));
		// Checks the right diagonal position to see if there is a piece there
		if (b.isValid(new Position(p.getX() + 1, p.getY() + 1)) && b.hasBlack(new Position(p.getX() + 1, p.getY() + 1))
				|| this.canRightPassant)
			positions.add(new Position(p.getX() + 1, p.getY() + 1));
		// Checks to see if the pawn has moved yet
		if (!hasMoved) {
			// If the pawn hasn't moved, it has the option to move 1 forward or 2 forward
			if (b.isEmpty(new Position(p.getX(), p.getY() + 1)))
				if (b.isValid(new Position(p.getX(), p.getY() + 1)))
					positions.add(new Position(p.getX(), p.getY() + 1));

			if (b.isEmpty(new Position(p.getX(), p.getY() + 2)) && b.isEmpty(new Position(p.getX(), p.getY() + 1)))
				if (b.isValid(new Position(p.getX(), p.getY() + 2)))
					positions.add(new Position(p.getX(), p.getY() + 2));
		} else {
			// Only adds the space in front of the pawn if it has moved
			if (b.isEmpty(new Position(p.getX(), p.getY() + 1)))
				if (b.isValid(new Position(p.getX(), p.getY() + 1)))
					positions.add(new Position(p.getX(), p.getY() + 1));
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

	/**
	 * PRECONDITION: The pawn has reached the opposite end of the board Makes the
	 * pawn a queen if it reaches the other end of the board
	 * 
	 * @param b the board of the piece
	 */
	public void kingMe(Board b) {
		Position place = this.getPos();
		b.remove(this.getPos());
		b.add(new WhiteQueen(place.getX(), place.getY()), place);
	}

	public boolean isWhite() {
		return true;
	}

	public boolean isBlack() {
		return false;
	}

	public void moved() {
		this.hasMoved = true;
	}

	public boolean hasMoved() {
		return this.hasMoved;
	}

	public void canLeftPassant(boolean canPassant) {
		this.canLeftPassant = canPassant;
	}

	public boolean canLeftPassant() {
		return this.canLeftPassant;
	}

	public void canRightPassant(boolean canPassant) {
		this.canRightPassant = canPassant;
	}

	public boolean canRightPassant() {
		return this.canRightPassant;
	}

	public String toString() {
		return "resources/WhitePawn.png";
	}
}