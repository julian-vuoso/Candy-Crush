package game.backend.element;

import game.backend.move.Direction;

public abstract class Element {
	
	public abstract boolean isMovable();
	
	public abstract String getKey();
	
	public String getFullKey() {
		return getKey();
	}

	public boolean isSolid() {
		return true;
	}
	
	public Direction[] explode() {
		return null;
	}
	
	public long getScore() {
		return 0;
	}

	/*
	* Se agrega el metodo concreto isHole(), que devuelve false.
	* Este metodo sera sobreescrito por Gap para indicar que es
	* un agujero
	* */
	public boolean isHole() { return false; }
}
