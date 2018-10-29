package com.triki.gui;

import com.triki.model.Board;
import com.triki.model.Coord;
import com.triki.model.Piece;
import com.triki.model.Tile;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.triki.game.Constants;

public class Table implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Board board;
	private JButton [] [] buttons = new JButton[Constants.ROWS][Constants.COLUMNS];
	private final JFrame gameFrame = new JFrame("TRIKI");
	JPanel mainframe = new JPanel();
	public Table(Board board) {
		super();
		this.board = board;
		this.initTable();
		this.gameFrame.setVisible(true);
		this.gameFrame.setVisible(true);
		this.gameFrame.setSize(500,500);
	}

	private void initTable() {
		// TODO Auto-generated method stub
		 this.mainframe.setLayout(new GridLayout(Constants.ROWS, Constants.COLUMNS));
		 
		 for (int i=0; i<Constants.ROWS;i++) {
				for (int j=0; j<Constants.COLUMNS;j++) {
					JButton btn = new JButton();
					btn.setBackground(Color.WHITE);
					btn.addActionListener(this);
					this.buttons[i][j] = btn;
					this.mainframe.add(btn);
				}
			}
		 this.gameFrame.add(this.mainframe);
	        
	        //Set up components preferred size
	        
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Coord c = this.getCoordButton(((JButton) e.getSource()));
		if (this.board.GetTile(c.getX(), c.getY()) instanceof Tile.TilePiece) {
			JOptionPane.showMessageDialog(null, "Cuadro en uso" , "ERROR", JOptionPane.INFORMATION_MESSAGE);
		}
		boolean winner = this.board.makeMove(this.getCoordButton(((JButton) e.getSource())));
		if (winner) {
			JOptionPane.showMessageDialog(null,"HAS GANADO" , "GANASTE", JOptionPane.INFORMATION_MESSAGE);
			this.board.initTiles();
			this.repaintBoard();
			return;
		}
		
		this.repaintBoard ();
		if (this.board.isComplete()) {
			JOptionPane.showMessageDialog(null, "HAN QUEDADO EN EMPATE", "EMPATE",JOptionPane.INFORMATION_MESSAGE);
			this.board.initTiles();
			this.repaintBoard();
			return;
		};
		boolean winnerIA = this.board.playIA();
		if (winnerIA) {
			JOptionPane.showMessageDialog(null, "PERDISTE", "HA GANADO LA MAQUINA", JOptionPane.INFORMATION_MESSAGE);
			this.board.initTiles();
			this.repaintBoard();
			return;
		}
		this.repaintBoard();
		
	}
	
	

	private Coord getCoordButton (JButton button){
		for (int i=0; i<Constants.ROWS;i++) {
			for (int j=0; j<Constants.COLUMNS;j++) {
				if (button == this.buttons[i][j]) {
					return new Coord(i, j);
				}
				
			}
		}
		return null;
	}
	
	private void repaintBoard() {
		// TODO Auto-generated method stub
		for (int i=0; i<Constants.ROWS;i++) {
			for (int j=0; j<Constants.COLUMNS;j++) {
				JButton btn = this.buttons[i][j];
				Tile tile = this.board.GetTile(i, j);
				if (tile instanceof Tile.EmptyTile) {
					btn.setText("");
				}else {
					if (tile.getPiece() instanceof Piece.Circle) {
						btn.setText("O");
					}else {
						btn.setText("X");
					}
				}
			}
		}
	}
	
	
}
