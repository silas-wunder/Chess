import java.util.ArrayList;
public class WhiteRook extends Piece {	

	private boolean hasMoved;

	/**
	 * Constructor setting Position of rook
	 * @param x x Coordinate
	 * @param y y Coordinate
	 */
	WhiteRook(int x, int y){
		super(x, y);
		hasMoved = false;
	}
	
	/**
	 * Returns the current possible moves for this piece
	 * @return ArrayList<Position> the ArrayList of all possible positions
	 * @param b the board of the piece
	 */
	public ArrayList<Position> getPossibleMoves(Board b){
		ArrayList<Position> positions = new ArrayList<Position>();
		Position p = new Position(this.getPos().getX(), this.getPos().getY());
		int max = 1;
		
		//Forward Positions
		do{
			p.setY(p.getY() + 1);
			max++;
		}
		while(b.isEmpty(p) && b.isValid(p) && !b.hasWhite(p));
		for(int i = 0; i < max; i++){
			positions.add(new Position(this.getPos().getX(), this.getPos().getY() + i));
		}
		
		//Positions to the Right
		
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;
		
		do{
			p.setX(p.getX() + 1);
			max++;
		}
		while(b.isEmpty(p) && b.isValid(p) && !b.hasWhite(p));
		for(int i = 0; i < max; i++){
			positions.add(new Position(this.getPos().getX() + i, this.getPos().getY()));
		}
		
		//Positions to the Left
		
		max = 1;
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		
		do{
			p.setX(p.getX() - 1);
			max++;
		}while(b.isEmpty(p) && b.isValid(p) && !b.hasWhite(p));
		for(int i = max; i >= 0; i--){
			positions.add(new Position(this.getPos().getX() - i, this.getPos().getY()));
		}
		
		//Positions Behind
		
		max = 1;
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		
		do{
			p.setY(p.getY() - 1);
			max++;
		}
		while(b.isEmpty(p) && b.isValid(p) && !b.hasWhite(p));
		for(int i = max; i >= 0; i--){
			positions.add(new Position(this.getPos().getX(), this.getPos().getY() - i));
		}
		return positions;
	}

	public void moved(){
		hasMoved = true;
	}

	public boolean hasMoved(){
		return hasMoved;
	}
	
	public String toString(){
		return "WhiteRook.png";
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
