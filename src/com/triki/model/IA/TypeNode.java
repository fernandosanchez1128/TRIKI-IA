package com.triki.model.IA;

public enum TypeNode {

	MIN {
		@Override
		public TypeNode oposite() {
			// TODO Auto-generated method stub
			return MAX;
		}
	},
	MAX {
		@Override
		public TypeNode oposite() {
			// TODO Auto-generated method stub
			return MIN;
		}
	};
	
	public abstract TypeNode oposite();
}
