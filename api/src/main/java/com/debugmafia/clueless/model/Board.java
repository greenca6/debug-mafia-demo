package com.debugmafia.clueless.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Board {
  private BoardLocation[][] grid;
  private Set<Card> cards;
  private Set<Card> deck;
  private Set<Piece> gamePieces;
  private Set<Weapon> gameWeapons;
  private Set<Card> winningCards;

  public Board() {

    //TODO: initialize grid
    //TODO: initialize deck of cards
    //TODO: initialize pieces
    //TODO: initialize weapons
    shuffleDeck();
  }

  private void shuffleDeck() {
    List<Card> cardList = new ArrayList<>(deck);
    Collections.shuffle(cardList);
    deck = new HashSet<Card>(cardList);
  }

  public Set<Card> drawWinningCards() {
    Set<Card> cardsToReturn;

    if (winningCards == null) {
      cardsToReturn = new HashSet<Card>();
      for (Card c : deck) {
        List<Card> filteredList = cardsToReturn.stream().filter(x -> x.getType() == c.getType())
            .collect(Collectors.toList());
        if (filteredList.isEmpty()) {
          cardsToReturn.add(c);
          deck.remove(c);
        }
      }
      winningCards = cardsToReturn;
    } else {
      cardsToReturn = winningCards;
    }
    return cardsToReturn;
  }

  public Set<Card> draw(int numToDraw) {

    Set<Card> setToReturn = new HashSet<>();

    if (deck.size() >= numToDraw) {

      Iterator<Card> iterator = deck.iterator();

      while(numToDraw > 0 && iterator.hasNext())
      {
        setToReturn.add(iterator.next());
        iterator.remove();
        numToDraw--;
      }
    }
    return setToReturn;
  }

  public Set<BoardLocation> getOpenAdjacentLocations(BoardLocation locationToQuery) {
    Set<BoardLocation> validLocations = new HashSet<BoardLocation>();

    for (BoardLocation[] row : grid) {
      for(BoardLocation column: row)
      {
      if (locationToQuery.getXcoord() + 1 == column.getXcoord() || locationToQuery.getYcoord() + 1 == column.getYcoord()) {
        if (column.getType() == BoardLocationType.ROOM) {
          validLocations.add(column);
        } else if (column.getType() == BoardLocationType.HALLWAY && locationToQuery.getPieces().isEmpty()) {
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

    BoardLocation localLocation = null;
    Piece localPiece = null;

    for(BoardLocation[] row : grid )
    {
      for(BoardLocation column : row)
      {
        if(column == to)
        {
          localLocation = column;
          break;
        }
      }
    }

    for(Piece pIter : gamePieces)
    {
      if(pIter == p)
      {
        localPiece = pIter;
        break;
      }
    }

    if(localLocation == null || localPiece == null)
    {
      return null;
    }

    HashSet<Piece> localPieces = new HashSet<Piece>(localLocation.getPieces());

    localPieces.add(localPiece);
    localLocation.setPieces(localPieces);

    return this;
  }

  public Board moveWeapon(Weapon w, BoardLocation to) {


    BoardLocation localLocation = null;
    Weapon localWeapon = null;

    for(BoardLocation[] row : grid )
    {
      for(BoardLocation column : row)
      {
        if(column == to)
        {
          localLocation = column;
          break;
        }
      }
    }

    for(Weapon wIter : gameWeapons)
    {
      if(wIter == w)
      {
        localWeapon = wIter;
        break;
      }
    }

    if(localLocation == null || localWeapon == null)
    {
      return null;
    }

    HashSet<Weapon> localWeapons = new HashSet<Weapon>(localLocation.getWeapons());

    localWeapons.add(localWeapon);
    localLocation.setWeapons(localWeapons);

    return this;

  }

  public Card getAssociatedCard(Weapon w) {
    List<Card> tempList = deck.stream().filter(x -> x.getType() == CardType.WEAPON && x.getName() == w.getWeaponname())
        .collect(Collectors.toList());

    if (tempList.size() != 1) {
      return null;
    } else {
      return tempList.get(0);
    }
  }

  public Card getAssociatedCard(Piece p) {
    List<Card> tempList = deck.stream().filter(x -> x.getType() == CardType.PIECE && x.getName() == p.getName())
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

    List<Card> tempList = deck.stream().filter(x -> x.getType() == CardType.ROOM && x.getName() == l.getName())
        .collect(Collectors.toList());

    if (tempList.size() != 1) {
      return null;
    } else {
      return tempList.get(0);
    }
  }

}
