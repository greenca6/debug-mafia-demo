package com.debugmafia.clueless.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class Board {
  private List<BoardLocation> grid;
  private Set<Card> cards;
  private Set<Card> deck;
  private Set<Piece> pieces;
  private Set<Weapon> weapons;

  public Board() {
    this.grid = BoardLocationFactory.createGrid();
    this.cards = CardFactory.createDeck();
    this.deck = CardFactory.createDeck();
    this.pieces = PieceFactory.createPieces();
    this.weapons = WeaponFactory.createWeapons();
    this.shuffleDeck();
  }

  private void shuffleDeck() {
    List<Card> cardList = new ArrayList<>(this.deck);
    Collections.shuffle(cardList);
    this.deck = new HashSet<>(cardList);
  }

  private BoardLocation getLocationByUuid(UUID uuid) {
    Optional<BoardLocation> location = this.grid.stream().filter(l -> l.getUuid().equals(uuid)).findFirst();

    if (location.isPresent()) {
      return location.get();
    }

    return null;
  }

  public Set<Card> drawWinningCards() {
    Set<Card> cardsToReturn = new HashSet<>();
    Iterator<Card> iter = this.deck.iterator();

    while (iter.hasNext() && cardsToReturn.size() < 3) {
      Card next = iter.next();

      // Pick this card if we haven't picked one of this type, remove the card from the deck
      if (cardsToReturn.stream().noneMatch(c -> c.getType().equals(next.getType()))) {
        cardsToReturn.add(next);
        iter.remove();
      }
    }

    return cardsToReturn;
  }

  public Set<Card> draw(int numToDraw) {
    // TODO: pretty up this error, add better error handling
    if (numToDraw > this.deck.size()) {
      throw new Error("Tried to draw more cards than available!");
    }

    Iterator<Card> iter = this.deck.iterator();
    Set<Card> setToReturn = new HashSet<>();

    while (setToReturn.size() < numToDraw) {
      setToReturn.add(iter.next());
      iter.remove();
    }

    return setToReturn;
  }

  public Set<BoardLocation> getOpenAdjacentLocations(BoardLocation location) {
    Set<BoardLocation> openAdjacentLocations = new HashSet<BoardLocation>();

    // Always add the secret passage if the location has one
    if (location.getSecretPassage() != null) {
      BoardLocation secretPassage = this.getLocationByUuid(location.getSecretPassage());
      openAdjacentLocations.add(secretPassage);
    }

    // Add if the location is a room, or an empty hallway
    for (UUID uuid: location.getAdjacentTo()) {
      BoardLocation l = this.getLocationByUuid(uuid);

      if (l.getType().equals(BoardLocationType.ROOM)) {
        openAdjacentLocations.add(l);
      } else if (l.getType().equals(BoardLocationType.HALLWAY) && l.getPieces().size() == 0) {
        openAdjacentLocations.add(l);
      }
    }

    return openAdjacentLocations;
  }

  public Board movePiece(Piece piece, BoardLocation to) {
    BoardLocation targetLocation = this.grid.stream().filter(l -> l.equals(to)).findFirst().get();
    Piece targetPiece = this.pieces.stream().filter(p -> p.equals(piece)).findFirst().get();
    Optional<BoardLocation> oldLocation = this.grid.stream().filter(l -> l.containsPiece(targetPiece)).findFirst();

    if (oldLocation.isPresent()) {
      oldLocation.get().removePiece(targetPiece);
    }

    targetLocation.addPiece(targetPiece);

    return this;
  }

  public BoardLocation getPieceLocation(Piece p){
    return this.grid.stream().filter(l ->l.containsPiece(p)).findFirst().get();
  }

  public Board moveWeapon(Weapon weapon, BoardLocation to) {
    BoardLocation targetLocation = this.grid.stream().filter(l -> l.equals(to)).findFirst().get();
    Weapon targetWeapon = this.weapons.stream().filter(w -> w.equals(weapon)).findFirst().get();
    Optional<BoardLocation> oldLocation = this.grid.stream().filter(l -> l.containsWeapon(targetWeapon)).findFirst();

    if (oldLocation.isPresent()) {
      oldLocation.get().removeWeapon(targetWeapon);
    }

    targetLocation.addWeapon(targetWeapon);

    return this;
  }

  public Card getAssociatedCard(Weapon weapon) {
    Optional<Card> card = this.cards.stream().filter(c -> c.getType().equals(CardType.WEAPON) && c.getName().equals(weapon.getName())).findFirst();

    return card.isPresent() ? card.get() : null;
  }

  public Card getAssociatedCard(Piece piece) {
    Optional<Card> card = this.cards.stream().filter(c -> c.getType().equals(CardType.PIECE) && c.getName().equals(piece.getName())).findFirst();

    return card.isPresent() ? card.get() : null;
  }

  public Card getAssociatedCard(BoardLocation location) {
    Optional<Card> card = this.cards.stream().filter(c -> c.getType().equals(CardType.ROOM) && c.getName().equals(location.getName())).findFirst();

    return card.isPresent() ? card.get() : null;
  }

  public List<BoardLocation> getGrid() {
    return this.grid;
  }

  public Set<Card> getCards() {
    return this.cards;
  }

  public Set<Card> getDeck() {
    return this.deck;
  }

  public Set<Piece> getPieces() {
    return this.pieces;
  }

  public Set<Weapon> getWeapons() {
    return this.weapons;
  }
}
