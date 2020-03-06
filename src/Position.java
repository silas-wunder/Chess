public class Position {
	/**
	 * x coordinate of Position
	 */
	private int x;

	/**
	 * y Coordinate of Position
	 */
	private int y;

	/**
	 * Constructor that sets x and y coordinates of a Piece
	 * 
	 * @param x x Coordinate of Position
	 * @param y y Coordinate of Position
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Sets x coordinate of Position
	 * 
	 * @param x new x Coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets y coordinate of Position
	 * 
	 * @param y new y Coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets x coordinate of Position
	 * 
	 * @return x coordinate
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Gets y coordinate of Position
	 * 
	 * @return y coordinate
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Checks if this position's x and y coordinates are equal to Position given in
	 * parameter
	 * 
	 * @param p Position to check
	 * @return if Position's x and y coordinates are equal
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Position))
			return false;
		Position p = (Position) o;
		return (this.getX() == p.getX() && this.getY() == p.getY());
	}

	/**
	 * Prints xCoor and yCoor of Position
	 */
	@Override
	public String toString() {
		return String.format("(%d, %d)", this.x, this.y);
	}
}
