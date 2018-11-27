package com.debugmafia.demo;

import java.util.UUID;

public class Card {
	
	@SuppressWarnings("unused")
	private String id = UUID.randomUUID().toString();
	private CardType type;
	private String name;

	public Card(CardType type, String name){
		setType(type);
		setName(name);
		this.type = type;
		this.name = name;
	}

	public CardType getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	private void setType(CardType type) {
		this.type = type;
	}
	
	private void setName(String name) {
		this.name = name;
	}

}
