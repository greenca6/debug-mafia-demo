package com.debugmafia.clueless.model;

import java.util.Collections;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.tools.DocumentationTool.Location;

public class Board {
  private BoardLocation[][] grid;
  private Set<Card> deckOCards;
  private Set<Piece> gamePieces;
  private Set<Weapon> gameWeapons;
  private Boolean hasDeckBeenShuffled;
  private Set<Card> winningCards;

  public Board() {

  }

  private void shuffleDeck() {
    List<Card> cardList = new ArrayList<>(deckOCards);
    Collections.shuffle(cardList);
    deckOCards = new HashSet<Card>(cardList);
    hasDeckBeenShuffled = true;
  }

  public Set<Card> drawWinningCards() {
    Set<Card> cardsToReturn;

    if (winningCards == null) {
      cardsToReturn = new HashSet<Card>();
      if (!hasDeckBeenShuffled) {
        shuffleDeck();
      }
      hasDeckBeenShuffled = true;

      for (Card c : deckOCards) {
        List<Card> filteredList = cardsToReturn.stream().filter(x -> x.getType() == c.getType())
            .collect(Collectors.toList());
        if (filteredList.isEmpty()) {
          cardsToReturn.add(c);
        }
      }

      winningCards = cardsToReturn;
    } else {
      cardsToReturn = winningCards;
    }
    return cardsToReturn;
  }

  public Set<Card> draw(int numToDraw) {
    if (hasDeckBeenShuffled) {

    }
    return null;
  }

  public Set<BoardLocation> getOpenAdjacentLocations(BoardLocation locationToQuery) {
    Set<BoardLocation> validLocations = new HashSet<BoardLocation>();

    for (BoardLocation[] row : grid) {
      for(BoardLocation column: row)
      {
      if (locationToQuery.getXcoord() + 1 == column.getXcoord() || locationToQuery.getXcoord() + 1 == column.getYcoord()) {
        if (column.getType() == BoardLocationType.ROOM) {
          validLocations.add(column);
        } else if (column.getType() == BoardLocationType.HALLWAY /* and occupancy is zero */) {
          validLocations.add(column);
        }
      } else if (locationToQuery.getSecretPassage() != null && locationToQuery.getSecretPassage() == column) {
        validLocations.add(column);
      }
    }
    }

    return validLocations;
  }

  public Board movePiece(Piece p, BoardLocation to) {
    return this;
  }

  public Board moveWeapons(Weapon w, BoardLocation to) {
    return this;
  }

  public Card getAssociatedCard(Weapon w) {
    List<Card> tempList = deckOCards.stream().filter(x -> x.getType() == CardType.WEAPON && x.getName() == w.getWeaponname())
        .collect(Collectors.toList());

    if (tempList.size() != 1) {
      return null;
    } else {
      return tempList.get(0);
    }
  }

  public Card getAssociatedCard(Piece p) {
    List<Card> tempList = deckOCards.stream().filter(x -> x.getType() == CardType.PIECE && x.getName() == p.getName())
        .collect(Collectors.toList());

    if (tempList.size() != 1) {
      return null;
    } else {
      return tempList.get(0);
    }
  }

  public Card getAssociatedCard(BoardLocation l) {

    if (l.getType() != BoardLocationType.ROOM) {
      return null;
    }

    List<Card> tempList = deckOCards.stream().filter(x -> x.getType() == CardType.ROOM && x.getName() == l.getName())
        .collect(Collectors.toList());

    if (tempList.size() != 1) {
      return null;
    } else {
      return tempList.get(0);
    }
  }

}
