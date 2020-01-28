package main.java;
import java.util.ArrayList;

public class BlackBishop extends Piece{

	public BlackBishop(int x, int y) {
		super(x, y);
	}

	/**
	 * Returns the current possible moves for this piece
	 * @return ArrayList<Position> the ArrayList of all possible positions
	 * @param b the board of the piece
	 */
	public ArrayList<Position> getPossibleMoves(Board b) {
		ArrayList<Position> positions = new ArrayList<Position>();
		Position p = new Position(this.getPos().getX() + 1, this.getPos().getY() + 1);
		int max = 1;
		
		//Positions to the diagonal up - right
		do{
			p.setX(p.getX() + 1);
			p.setY(p.getY() + 1);
			max ++;
		}while(b.isEmpty(p) && b.isValid(p) && !b.hasBlack(p));
		for(int i = 0; i <= max; i ++)
			positions.add(new Position(this.getPos().getX() + i, this.getPos().getY() + i));
		
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;
		
		//Positions to the diagonal down - left
		do{
			p.setX(p.getX() - 1);
			p.setY(p.getY() - 1);
			max ++;
		}while(b.isEmpty(p) && b.isValid(p) && !b.hasBlack(p));
		for(int i = max; i >= 0; i --)
			positions.add(new Position(this.getPos().getX() - i, this.getPos().getY() - i));
		
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;
		
		//Positions to the diagonal up - left
		do{
			p.setX(p.getX() + 1);
			p.setY(p.getY() - 1);
			max += 1;
		}while(b.isEmpty(p) && b.isValid(p) && !b.hasBlack(p));
		for(int i = 0; i < max; i ++)
			positions.add(new Position(this.getPos().getX() + i, this.getPos().getY() - i));
		
		p.setY(this.getPos().getY());
		p.setX(this.getPos().getX());
		max = 1;
		
		//Positions to the diagonal down - right
		do{
			p.setX(p.getX() - 1);
			p.setY(p.getY() + 1);
			max ++;
		}while(b.isEmpty(p) && b.isValid(p) && !b.hasBlack(p));
		for(int i = 0; i < max; i ++)
			positions.add(new Position(this.getPos().getX() - i, this.getPos().getY() + i));
		
		return positions;
	}

	@Override
	public boolean isWhite() {
		return false;
	}

	@Override
	public boolean isBlack() {
		return true;
	}

	public String toString(){ return "BlackBishop.png"; }
	
}
