package com.triki.game;

import com.triki.gui.Table;
import com.triki.model.Board;

public class Game {

	public static void main (String [] args) {
		
	
	Player playerOne = new Player(PlayerType.ONE);
	Player playerTwo = new Player(PlayerType.TWO);
	Board board = new Board(playerOne, playerTwo);
	Table table = new Table (board);
	}
}
