package com.triki.model.IA;

import java.util.ArrayList;
import java.util.List;

import com.triki.game.Player;
import com.triki.game.PlayerType;
import com.triki.model.Board;
import com.triki.model.Coord;
import com.triki.model.Tile;

public class Node {
	private TypeNode type= TypeNode.MAX;
	private int score = 0;
	List <Node> children = new ArrayList<>() ;
	private Board board;
	private Coord coord;
	
	public Node(TypeNode type, Board board,Coord coord) {
		super();
		this.type = type;
		this.board = board;
		this.coord = coord;
	}
	public boolean hasChildren () {
		return this.children == null || this.children.size()>0;
	}
	
	List <Node> getChildren () {
		return this.children;
	}
	

	public void addSon (Node node){
		this.children.add(node);
		
	}
	public static void CreateTree (Node node, int deep) {
		if (deep == 0) {
			 
		}else {
			if (!Board.validateWinner(node.board)) {
		
				for (int i=0 ; i<3;i++) {
					for (int j = 0 ; j<3;j++) {
						Tile tile = node.board.GetTile(i, j);
						if (tile instanceof Tile.EmptyTile) {
							Board boardClone = node.board.clone();						
							Coord mymov = new Coord(i, j);
							boardClone.makeMove(mymov);
							Node ch1 = new Node (node.getType().oposite(),boardClone,mymov);
							int newDeep = deep -1;
							node.addSon(ch1);
							Node.CreateTree(ch1, newDeep);
							
						}
					}
				}
			}
			
		}
	}
	
	public static void calculateScores (Node node) {
		if (!node.hasChildren()) {
			int score =  Board.calculaScore(node.board);
			node.score = score;
			if (node.getType().equals (TypeNode.MAX)){
				node.score = score * -1;
			}
		}else {
			for (Node nodeC : node.getChildren()) {
				Node.calculateScores(nodeC);
			}
			if (node.getType().equals(TypeNode.MAX)){
				int  val = -1000;
				for (Node nodeSon : node.getChildren()) {
					if (nodeSon.score > val) {
						val = nodeSon.score;
					}
				}
				node.score = val;
				
			}else {
				int val = 1000;
				for (Node nodeSon : node.getChildren()) {
					if (nodeSon.score < val) {
						val = nodeSon.score;
					}
				}
				node.score = val;
			}
		}
	}
	public void print (int deep) {
		for (Node node : this.children) {
			String cad = "";
			
			for (int i=0; i<deep; i++) {
				cad = cad + "\t";
			}
//			Coord c= node.coord;
//			if (c!= null) {
//				cad = cad + "(" + c.getX() +"," + c.getY() +") : " + node.score;
//			}else {
//				cad = cad + "T : "+ node.score;
//			}
			
			System.out.println(cad + node.board.print(cad));
			node.print(deep+1);
		}
	}
	
	
	public TypeNode getType() {
		return type;
	}

	public void setType(TypeNode type) {
		this.type = type;
	}
	
	public Coord getCoordMove (){
		for (Node nodeSon : this.children) {
			System.out.println(nodeSon.score);
			if (nodeSon.score == this.score) {
				return nodeSon.coord;
			}
		}
		return null;
	}

//	public static void main (String [] args) {
//		Player playerOne = new Player(PlayerType.ONE);
//		Player playerTwo = new Player(PlayerType.TWO);
//		Board board = new Board(playerOne, playerTwo);
//		Node root = new Node (TypeNode.MAX,board,null);
//		Node.CreateTree(root, 4);
//		Node.calculateScores(root);
//		root.print(1);
//	}
}
