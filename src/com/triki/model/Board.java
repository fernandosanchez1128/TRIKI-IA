package com.triki.model;

import javax.swing.JButton;

import com.triki.game.Constants;
import com.triki.game.Player;
import com.triki.model.IA.Node;
import com.triki.model.IA.TypeNode;

public class Board implements Cloneable{
	private Tile [][] tiles = new Tile [Constants.ROWS][Constants.COLUMNS];
	private Player playerTurn = null;
	private Player playerOne = null;
	private Player playerTwo = null;

	public Board (Player playerOne, Player playerTwo) {
		this.initTiles ();
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.playerTurn = playerOne;
	}
	

    public Tile[][] getTiles() {
		return tiles;
	}


	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}


	public Board clone(){
    	Board board=null;
        try{
            board= (Board)super.clone();
            //board.playerOne = this.playerOne;
            //board.playerTwo = this.playerTwo;
            board.setTiles( this.makeCopyTiles());
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return board;
    }
	
	public Tile [][] makeCopyTiles (){
		Tile [] [] new_tiles = new Tile [3][3]; 
		for (int i=0; i<Constants.ROWS;i++) {
			for (int j=0; j<Constants.COLUMNS;j++) {
				new_tiles [i] [j] = this.tiles[i][j].clone();
			}
		} 
		return new_tiles;
		
	}
	public void initTiles() {
		// TODO Auto-generated method stub
		for (int i=0; i<Constants.ROWS;i++) {
			for (int j=0; j<Constants.COLUMNS;j++) {
				tiles [i][j] = new Tile.EmptyTile(new Coord(i, j));
			}
		}
	}
	
	public boolean makeMove (Coord coord) {
		int x = coord.getX();
		int y = coord.getY();
		Tile tile = this.tiles[x][y];
		if (tile  instanceof Tile.EmptyTile) {
			this.tiles[x][y]= new Tile.TilePiece(playerTurn.createPiece(coord));
			boolean winner = Board.validateWinner(this);
			this.changetTurn();
			return winner;
		}
		return false;
		
	}

	public static boolean validateWinner(Board board ) {
		boolean hori = validateHorizontal (board);
		boolean vert = validateVertical (board);
		boolean diag = validateDiagonal (board);
		// TODO Auto-generated method stub
		return hori || vert || diag;
		
	}

	
	public void modify (){
		this.tiles[0][0] = new Tile.TilePiece(this.playerOne.createPiece(new Coord(0,0)));
	}
	

	public static boolean validateHorizontal(Board board) {
		// TODO Auto-generated method stub
		for (int x=0; x<Constants.ROWS;x++) {
			if (validateEqual (board.tiles[x][0], board.tiles[x][1], board.tiles[x][2])) {
				return true;
			};
		}
		return false;
	}

	private static boolean validateVertical(Board board) {
		// TODO Auto-generated method stub
		for (int y=0; y<Constants.COLUMNS;y++) {
			if (validateEqual (board.tiles[0][y], board.tiles[1][y], board.tiles[2][y])) {
				return true;
			};
		}
		return false;
	}

	private static boolean validateDiagonal(Board board) {
		// TODO Auto-generated method stub
		boolean d1 = Board.validateEqual(board.tiles[0][0], board.tiles[1][1], board.tiles[2][2]);
		boolean d2 = Board.validateEqual(board.tiles[0][2], board.tiles[1][1], board.tiles[2][0]);
		return d1 || d2;
	}
	
	private static boolean validateEqual(Tile tile, Tile tile2, Tile tile3) {
		// TODO Auto-generated method stub
		if ((!tile.isEmpty() && !tile2.isEmpty() && !tile3.isEmpty())){
			return (tile.getPiece().v() == tile2.getPiece().v()) &&
				   (tile.getPiece().v() == tile3.getPiece().v());
		}
		return false;
	}
	private void changetTurn() {
		// TODO Auto-generated method stub
		this.playerTurn = this.playerTurn.equals(playerOne)? playerTwo : playerOne;
	}
	
	
	public Tile GetTile (int i, int j){
		return this.tiles[i][j];
	}


	public boolean playIA() {
		// TODO Auto-generated method stub
		System.out.println("IA");
		Node root = new Node (TypeNode.MAX,this,null);
		Node.CreateTree(root, 6);
		Node.calculateScores(root);
		//root.print(1);
		//System.out.println(root.getCoordMove());
		//root.print(1);
		
		return this.makeMove(root.getCoordMove());
	}
	
	
	public static int calculaScore (Board board) {
		if (Board.validateWinner(board)) {
			return 10;
		};
		return 0;
	}
	
	public String print (String tab) {
		String  val = ""; 
		for (int i=0; i<Constants.ROWS;i++) {
			for (int j=0; j<Constants.COLUMNS;j++) {
				Tile tile = this.tiles[i][j];
				if (tile instanceof Tile.EmptyTile) {
					val = val + " - ";
				}else {
					if (tile.getPiece() instanceof Piece.Circle) {
						val = val + " O ";
						
					}else {
						val = val + " X ";
					}
				}
			}
			val = val + "\n" + tab;
		}
		return val;
	}


	public boolean isComplete() {
		// TODO Auto-generated method stub
		for (int i=0; i<Constants.ROWS;i++) {
			for (int j=0; j<Constants.COLUMNS;j++) {
				if (this.tiles[i][j] instanceof Tile.EmptyTile) {
					return false;
				}
			}
		}
		return true;
	}
	
}
