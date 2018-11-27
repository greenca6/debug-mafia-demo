package com.debugmafia.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Board {

	private int gameBoardRows = 5;
	private int gameBoardCols = 5;

	private Location[][] grid;

	private Set<Piece> pieces;
	private Set<Weapon> weapons;
	private Set<Card> deck;

	public Board() {
		// Right now all of these are hard-coded. Once this push is committed I
		// will work on parsing these values from an input file.
		initializeGrid();
		initializePieces();
		initializeWeapons();
		initializeDeck();
		shuffleDeck();

	}

	private void initializeGrid() {
		this.grid = new Location[gameBoardRows][gameBoardCols];
		// I realize that I could have created objects for each of these and
		// reused the Hallway and Space objects to save space in memory. i.e.:
		// not doing that because we won't have that option when we are reading
		// the map from user-input files.

		this.grid[0][0] = new Location(0, 0, LocationType.ROOM, "Study");
		this.grid[0][1] = new Location(0, 1, LocationType.HALLWAY, "Hallway");
		this.grid[0][2] = new Location(0, 2, LocationType.ROOM, "Hall");
		this.grid[0][3] = new Location(0, 3, LocationType.HALLWAY, "Hallway");
		this.grid[0][4] = new Location(0, 4, LocationType.ROOM, "Lounge");

		this.grid[1][0] = new Location(1, 0, LocationType.ROOM, "Hallway");
		this.grid[1][1] = new Location(1, 1, LocationType.SPACE, "Space");
		this.grid[1][2] = new Location(1, 2, LocationType.ROOM, "Hallway");
		this.grid[1][3] = new Location(1, 3, LocationType.SPACE, "Space");
		this.grid[1][4] = new Location(1, 4, LocationType.ROOM, "Hallway");

		this.grid[2][0] = new Location(2, 0, LocationType.ROOM, "Library");
		this.grid[2][1] = new Location(2, 1, LocationType.HALLWAY, "Hallway");
		this.grid[2][2] = new Location(2, 2, LocationType.ROOM, "Billiard Room");
		this.grid[2][3] = new Location(2, 3, LocationType.HALLWAY, "Hallway");
		this.grid[2][4] = new Location(2, 4, LocationType.ROOM, "Dining Room");

		this.grid[3][0] = new Location(3, 0, LocationType.ROOM, "Hallway");
		this.grid[3][1] = new Location(3, 1, LocationType.SPACE, "Space");
		this.grid[3][2] = new Location(3, 2, LocationType.ROOM, "Hallway");
		this.grid[3][3] = new Location(3, 3, LocationType.SPACE, "Space");
		this.grid[3][4] = new Location(3, 4, LocationType.ROOM, "Hallway");

		this.grid[4][0] = new Location(4, 0, LocationType.ROOM, "Conservatory");
		this.grid[4][1] = new Location(4, 1, LocationType.HALLWAY, "Hallway");
		this.grid[4][2] = new Location(4, 2, LocationType.ROOM, "Ballroom");
		this.grid[4][3] = new Location(4, 3, LocationType.HALLWAY, "Hallway");
		this.grid[4][4] = new Location(4, 4, LocationType.ROOM, "Kitchen");
	}

	private void initializePieces() {
		this.pieces = new HashSet<Piece>();
		pieces.add(new Piece("Inspector Coloniel Mustard"));
		pieces.add(new Piece("Mr. Green"));
		pieces.add(new Piece("Professor Plum"));
		pieces.add(new Piece("Ole Brian Powell"));
		pieces.add(new Piece("Mrs. Peacock"));
		pieces.add(new Piece("Miss Scarlett"));

	}

	private void initializeWeapons() {
		this.weapons = new HashSet<Weapon>();
		weapons.add(new Weapon("Candlestick"));
		weapons.add(new Weapon("Lead Pipe"));
		weapons.add(new Weapon("Revolver"));
		weapons.add(new Weapon("Rope"));
		weapons.add(new Weapon("Wrench"));
		weapons.add(new Weapon("Knife"));

	}

	private void initializeDeck() {
		this.deck = new HashSet<Card>();
		this.deck.addAll(createPlayerCards());
		this.deck.addAll(createWeaponCards());
		this.deck.addAll(createRoomCards());

	}

	private Set<Card> createRoomCards() {
		HashSet<Card> roomCards = new HashSet<Card>();

		roomCards.add(new Card(CardType.ROOM, "Study"));
		roomCards.add(new Card(CardType.ROOM, "Hall"));
		roomCards.add(new Card(CardType.ROOM, "Lounge"));
		roomCards.add(new Card(CardType.ROOM, "Library"));
		roomCards.add(new Card(CardType.ROOM, "Billiard Room"));
		roomCards.add(new Card(CardType.ROOM, "Dining Room"));
		roomCards.add(new Card(CardType.ROOM, "Conservatory"));
		roomCards.add(new Card(CardType.ROOM, "Ballroom"));
		roomCards.add(new Card(CardType.ROOM, "Kitchen"));

		return roomCards;
	}

	private Set<Card> createWeaponCards() {
		HashSet<Card> weaponCards = new HashSet<Card>();

		weaponCards.add(new Card(CardType.WEAPON, "Candlestick"));
		weaponCards.add(new Card(CardType.WEAPON, "Lead Pipe"));
		weaponCards.add(new Card(CardType.WEAPON, "Revolver"));
		weaponCards.add(new Card(CardType.WEAPON, "Rope"));
		weaponCards.add(new Card(CardType.WEAPON, "Wrench"));
		weaponCards.add(new Card(CardType.WEAPON, "Knife"));

		return weaponCards;
	}

	private Set<Card> createPlayerCards() {
		HashSet<Card> playerCards = new HashSet<Card>();

		playerCards.add(new Card(CardType.PIECE, "Inspector Coloniel Mustard"));
		playerCards.add(new Card(CardType.PIECE, "Mr. Green"));
		playerCards.add(new Card(CardType.PIECE, "Professor Plum"));
		playerCards.add(new Card(CardType.PIECE, "Ole Brian Powell"));
		playerCards.add(new Card(CardType.PIECE, "Mrs. Peacock"));
		playerCards.add(new Card(CardType.PIECE, "Miss Scarlett"));

		return playerCards;
	}

	private Location[][] getGrid() {
		return this.grid;
	}

	private void shuffleDeck() {
		List<Card> newDeck = new ArrayList<Card>(getDeck());
		Collections.shuffle(newDeck);
		setDeck(new HashSet<Card>(newDeck));

	}

	public Set<Card> drawWinningCards() {
		HashSet<Card> winningCards = new HashSet<Card>();
		Iterator<Card> deck = getDeck().iterator();
		Card nextCard;

		while (winningCards.size() < 3 && deck.hasNext()) {
			nextCard = deck.next();
			if (!winningDeckHasType(winningCards, nextCard)) {
				winningCards.add(nextCard);
			}
		}

		return winningCards;
	}

	private boolean winningDeckHasType(Set<Card> currentDeck, Card newCard) {
		for (Card card : currentDeck) {
			if (card.getType().equals(newCard.getType()))
				return true;
		}

		return false;
	}

	public Set<Card> draw(Integer n) {
		HashSet<Card> drawnCards = null;

		if (getDeck().size() >= n) {
			Iterator<Card> currentDeck = getDeck().iterator();
			drawnCards = new HashSet<Card>();

			for (int ctr = 0; ctr < n; ctr++) {
				drawnCards.add(currentDeck.next());
				currentDeck.remove();
			}
		} else {
			System.err.println("Not enough cards left, cowboy!");
		}

		return drawnCards;
	}

	public Set<Location> getOpenAdjacentLocations(Location l) {
		HashSet<Location> openAdjacentLocations = new HashSet<Location>();

		if (l.getX() - 1 > 0)
			if (l.getY() - 1 > 0)
				if (l.getX() + 1 > 0)
					if (l.getY() + 1 > 0)

						for (int row = l.getX() - 1; row < l.getX() + 1; row++) {
							for (int col = l.getY() - 1; col < l.getY() + 1; col++) {

								if ((row >= 0 && row <= gameBoardRows) && (col >= 0 && col <= gameBoardCols)
										&& (row != l.getX() || col != l.getY())) {
									if (getGrid()[row][col].getLocationType() == LocationType.ROOM
											|| (getGrid()[row][col].getLocationType() == LocationType.HALLWAY
													&& getGrid()[row][col].isEmpty()))
										openAdjacentLocations.add(getGrid()[row][col]);
								}
							}
						}

		if ((l.getX() == 0 && l.getY() == 0) || (l.getX() == 0 && l.getY() == 2) || (l.getX() == 2 && l.getY() == 0)
				|| (l.getX() == 2 && l.getY() == 2)) {
			openAdjacentLocations.add(getGrid()[1][1]);
		}

		return openAdjacentLocations;
	}

	public Board movePiece(Piece p, Location to) {
		for (int x = 0; x < gameBoardRows; x++) {
			for (int y = 0; y < gameBoardCols; y++) {
				if (getGrid()[x][y].getPiece() == p) {
					getGrid()[x][y].setPiece(null);
					getGrid()[to.getX()][to.getY()].setPiece(p);
				}
			}
		}
		return this;
	}

	public Board moveWeapon(Weapon w, Location to) {
		for (int x = 0; x < gameBoardRows; x++) {
			for (int y = 0; y < gameBoardCols; y++) {
				if (getGrid()[x][y].getWeapon() == w) {
					getGrid()[x][y].setWeapon(null);
					getGrid()[to.getX()][to.getY()].setWeapon(w);
				}
			}
		}
		return this;
	}

	/*
	 * Assumes that no weapon, piece, or location can share a name
	 */
	public Card getAssociatedCard(Object obj) {
		Iterator<Card> deck = getDeck().iterator();
		Card nextCard = null;

		while (deck.hasNext()) {
			nextCard = deck.next();
			// We should have used an interface here among Weapons, Pieces and
			// locations since they all have a getName() Method. For right now
			// I'm
			// just going to cast to Location but this choice was arbitrary
			if (nextCard.getName().equals(((Location) obj).getName())) {
				return nextCard;
			}
		}

		return nextCard; // This should never happen since all cards are
							// guaranteed to be in the deck!
	}

	public Set<Card> getDeck() {
		return this.deck;
	}

	private void setDeck(Set<Card> deck) {
		this.deck = deck;
	}

}
