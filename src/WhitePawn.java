import java.util.ArrayList;

public class WhitePawn extends Piece {

	/**
	 * Boolean representing whether this pawn has moved or not
	 */
	private boolean hasMoved;
	/**
	 * Boolean representing whether this pawn can passant to the left or not
	 */
	private boolean canLeftPassant;
	/**
	 * Boolean representing whether this pawn can passant to the right or not
	 */
	private boolean canRightPassant;

	/**
	 * Makes a pawn at (x, y)
	 * 
	 * @param x the x location of this pawn
	 * @param y the y location of this pawn
	 */
	WhitePawn(int x, int y) {
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

		this.possibleMoves = positions;
	}

	/**
	 * Makes the pawn a queen if it reaches the other end of the board
	 * <p>
	 * PRECONDITION: The pawn has reached the opposite end of the board
	 * 
	 * @param b the board of the piece
	 */
	public void kingMe(Board b) {
		Position place = this.getPos();
		b.remove(this.getPos());
		b.add(new WhiteQueen(place.getX(), place.getY()), place);
		b.get(place).calculatePossibleMoves(b);
	}

	/**
	 * Sets this pawn's move status to true
	 */
	public void moved() {
		this.hasMoved = true;
	}

	/**
	 * Returns the move status of this pawn
	 * 
	 * @return boolean representing whether or not this pawn has moved
	 */
	public boolean hasMoved() {
		return this.hasMoved;
	}

	/**
	 * Sets this pawns left passant ability
	 * 
	 * @param canPassant boolean representing whether this pawn can passant to the
	 *                   left
	 */
	public void canLeftPassant(boolean canPassant) {
		this.canLeftPassant = canPassant;
	}

	/**
	 * Returns this pawn's ability to passant to the left
	 * 
	 * @return boolean representing whether this pawn can passant to the left
	 */
	public boolean canLeftPassant() {
		return this.canLeftPassant;
	}

	/**
	 * Sets this pawn's right passant ability
	 * 
	 * @param canPassant boolean representing whether this pawn can passant to the
	 *                   right
	 */
	public void canRightPassant(boolean canPassant) {
		this.canRightPassant = canPassant;
	}

	/**
	 * Returns this pawn's ability to passant to the right
	 * 
	 * @return boolean representing whether this pawn can passant to the right
	 */
	public boolean canRightPassant() {
		return this.canRightPassant;
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
		return "resources/WhitePawn.png";
	}
}