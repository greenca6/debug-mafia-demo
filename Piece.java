package com.debugmafia.demo;

import java.util.UUID;

public class Piece {

	@SuppressWarnings("unused")
	private String id = UUID.randomUUID().toString();
	private String name;

	public Piece(String name) {
		setName(name);
	}
	
	private void setName(String name){
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
