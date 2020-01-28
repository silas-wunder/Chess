import java.util.ArrayList;

public class WhiteKing extends Piece{

	private boolean inCheck;
	private boolean hasMoved;

	public WhiteKing(int x, int y) {
		super(x, y);
		inCheck = false;
	}

	/**
	 * Returns the current possible moves for this piece
	 * @return ArrayList<Position> the ArrayList of all possible positions
	 * @param b the board of the piece
	 */
	public ArrayList<Position> getPossibleMoves(Board b) {
		ArrayList<Position> positions = new ArrayList<Position>();
		Position p = new Position(this.getPos().getX(), this.getPos().getY());
		
		//Checks the position to the right
		
		if(b.isValid(new Position(p.getX() + 1, p.getY())))
			if(!b.hasWhite(new Position(p.getX() + 1, p.getY())))
				positions.add(new Position(p.getX() + 1, p.getY()));
		
		//Checks the position to the left
		
		if(b.isValid(new Position(p.getX() - 1 , p.getY())))
			if(!b.hasWhite(new Position(p.getX() - 1, p.getY())))
				positions.add(new Position(p.getX() - 1, p.getY()));
		
		//Checks the position above
		
		if(b.isValid(new Position(p.getX(), p.getY() + 1)))
			if(!b.hasWhite(new Position(p.getX(), p.getY() + 1)))
				positions.add(new Position(p.getX(), p.getY() + 1));
		
		//Checks the position below
		
		if(b.isValid(new Position(p.getX(), p.getY() - 1)))
			if(!b.hasWhite(new Position(p.getX(), p.getY() - 1)))
				positions.add(new Position(p.getX(), p.getY() - 1));
		
		//Checks the position to the diagonal up - right
		
		if(b.isValid(new Position(p.getX() + 1, p .getY() + 1)))
			if(!b.hasWhite(new Position(p.getX() + 1, p.getY() + 1)))
				positions.add(new Position(p.getX() + 1, p .getY() + 1));
		
		//Checks the position to the diagonal down - right
		
		if(b.isValid(new Position(p.getX() + 1, p .getY() - 1)))
			if(!b.hasWhite(new Position(p.getX() + 1, p.getY() - 1)))
				positions.add(new Position(p.getX() + 1, p .getY() - 1));
		
		//Checks the position to the diagonal up - left
		
		if(b.isValid(new Position(p.getX() - 1, p .getY() + 1)))
			if(!b.hasWhite(new Position(p.getX() - 1, p.getY() + 1)))
				positions.add(new Position(p.getX() - 1, p .getY() + 1));
		
		//Checks the position to the diagonal down - left
		
		if(b.isValid(new Position(p.getX() - 1, p .getY() - 1)))
			if(!b.hasWhite(new Position(p.getX() - 1, p.getY() - 1)))
				positions.add(new Position(p.getX() - 1, p .getY() - 1));
		
		//Checks conditions for the castle move
		if(!hasMoved){
			//Checks if the spaces to the right are empty
			if(b.isEmpty(new Position(5, 0)) && b.isEmpty(new Position(6, 0))){
				if(b.getType(new Position(0, 0)) instanceof WhiteRook){
					if(((WhiteRook)(b.get(new Position(0, 0)))).hasMoved()){

					}
				}
			}
			//Checks if the spaces to the left are empty
			if(b.isEmpty(new Position(3, 0)) && b.isEmpty(new Position(2, 0)) && b.isEmpty(new Position(1, 0))){

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

	public void moved(){ 
		hasMoved = true; 
	}

	public String toString(){ return "WhiteKing.png"; }
	
}
