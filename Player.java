package com.debugmafia.demo;

import java.util.Set;

public class Player {
	
	private String name;
	private Piece piece;
	private Set<Card> cards;
	
	public Player(String name, Piece piece){
		setName(name);
		setPiece(piece);
		
	}
	
	public boolean hasCard(Card card){
		return cards.contains(card);
	}
	
	public void dealCards(Set<Card> cards){
		this.cards=cards;
		
	}

	public String getName() {
		return name;
	}

	public Piece getPiece() {
		return piece;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setPiece(Piece p) {
		this.piece = p;
	}
}
