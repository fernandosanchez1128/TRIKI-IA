package com.triki.model;

public abstract class Piece {
	private Coord coord;
	
	

	public Piece(Coord coord) {
		super();
		this.coord = coord;
	}

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}
	
	public abstract int v();
	
	
	public static class Circle extends Piece {

		public Circle(Coord coord) {
			super(coord);
			// TODO Auto-generated constructor stub
		}

		@Override
		public int v() {
			// TODO Auto-generated method stub
			return 2;
		}
		
	}
	public static class Cross extends Piece{

		public Cross(Coord coord) {
			super(coord);
			// TODO Auto-generated constructor stub
		}

		@Override
		public int v() {
			// TODO Auto-generated method stub
			return 4;
		}
		
	}
	
	
}
