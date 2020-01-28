package main.java;
import java.util.ArrayList;

public abstract class Piece{
	/**
	 * Holds x and y position of piece
	 */
	private Position pos;
	
	/**
	 * Holds current board in use
	 */
	Piece(int x, int y){
		this.pos = new Position(x, y);
	}
	
	/**
	 * @return Position of piece
	 */
	public Position getPos(){
		return this.pos;
	}
	
	/**
	 * Defines how each piece can move
	 * @param b Board containing piece
	 * @return ArrayList of all possible Positions
	 */
	public abstract ArrayList<Position> getPossibleMoves(Board b);
	
	/**
	 * Implemented in all sub-classes
	 * @return If piece is White
	 */
	public abstract boolean isWhite();
	
	/**
	 * Implemented in all sub-classes
	 * @return If piece is Black
	 */
	public abstract boolean isBlack();
	
	/**
	 * Implemented in sub-classes
	 */
	public String toString(){
		return "";
	}
}