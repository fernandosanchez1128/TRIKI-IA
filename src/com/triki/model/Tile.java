package com.triki.model;

public abstract class Tile implements Cloneable{

	private Piece piece;
	private Coord cord;
	
	public Tile(Piece piece, Coord cord) {
		super();
		this.piece = piece;
		this.cord = cord;
	}
	
	public Tile clone(){
    	Tile tile=null;
        try{
        	tile= (Tile)super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return tile;
    }
	
	
	public Piece getPiece() {
		return piece;
	}


	public Coord getCord() {
		return cord;
	}


	public abstract boolean isEmpty ();
	
	public static class EmptyTile extends Tile {

		public EmptyTile(Coord cord) {
			super(null, cord);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return true;
		}
		
	}
	
	public static class TilePiece  extends Tile{
		
		public TilePiece(Piece piece) {
			super(piece, piece.getCoord());
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	
	
}
