package com.debugmafia.clueless.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BoardLocation {
  private BoardLocationType type;
  private String name;
  private BoardLocation secretPassage;
  private Set<BoardLocation> adjacentTo = new HashSet<>();
  private Set<Weapon> weapons = new HashSet<>();
  private Set<Piece> pieces = new HashSet<>();

  public BoardLocation(BoardLocationType type, String name) {
    this.type = type;
    this.name = name;
  }

  public void addAdjacentLocation(BoardLocation location) {
    this.adjacentTo.add(location);
  }

  public Set<BoardLocation> getAdjacentTo() {
    return this.adjacentTo;
  }

  public BoardLocationType getType() {
    return this.type;
  }

  public String getName() {
    return this.name;
  }

  public BoardLocation getSecretPassage() {
    return this.secretPassage;
  }

  public Set<Weapon> getWeapons() {
    return this.weapons;
  }

  public void addWeapon(Weapon w) {
    this.weapons.add(w);
  }

  public void removeWeapon(Weapon w) {
    for (Iterator<Weapon> iter = this.weapons.iterator(); iter.hasNext();) {
      Weapon next = iter.next();

      if (next.equals(w)) {
        iter.remove();
      }
    }
  }

  public Set<Piece> getPieces() {
    return this.pieces;
  }

  public void addPeice(Piece p) {
    // TODO: fail here if location type is hallway and we already have a piece?
    this.pieces.add(p);
  }

  public void removePiece(Piece p) {
    for (Iterator<Piece> iter = this.pieces.iterator(); iter.hasNext();) {
      Piece next = iter.next();

      if (next.equals(p)) {
        iter.remove();
      }
    }
  }
}
