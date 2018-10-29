package com.triki.game;

import com.triki.model.Board;
import com.triki.model.Coord;
import com.triki.model.Piece;

public class Player implements Cloneable{
	private boolean alive = true;
	private PlayerType type;
	
	public Player(PlayerType type) {
		super();
		this.type = type;
	}
	public Player clone(){
    	Player player=null;
        try{
            player= (Player)super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return player;
    }
	public Piece createPiece (Coord coord) {
		if (type.equals(PlayerType.ONE)) {
			return new Piece.Cross(coord);
		}else {
			return new Piece.Circle(coord);
		}
	}
	
}
