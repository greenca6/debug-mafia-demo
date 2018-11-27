package com.debugmafia.demo;

public class Location {

	private int x;
	private int y;
	private LocationType type;
	private String name;
	private Piece piece; // This wasn't in our spec. but I think we need it :)
	private Weapon weapon;

	// Not sure we need the X,Y coordinates represented here and on the game
	// board...One or the other should suffice
	public Location(Integer x, Integer y, LocationType type, String name) {
		setX(x);
		setY(y);
		setLocationType(type);
		setName(name);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public LocationType getLocationType() {
		return type;
	}

	public String getName() {
		return name;
	}

	private void setX(int x) {
		this.x = x;
	}

	private void setY(int y) {
		this.y = y;
	}

	private void setLocationType(LocationType type) {
		this.type = type;
	}

	private void setName(String name) {
		this.name = name;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	// Not in our spec, but convenient to have
	public boolean isEmpty() {
		return ((getPiece() == null) && (getName() == null) && (getWeapon() == null));
	}
}
