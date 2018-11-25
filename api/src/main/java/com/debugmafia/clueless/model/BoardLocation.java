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

  public void addPeice(Piece piece) {
    // TODO: fail here if location type is hallway and we already have a piece?
    this.pieces.add(piece);
  }

  public void addWeapon(Weapon weapon) {
    this.weapons.add(weapon);
  }

  public boolean containsPiece(Piece piece) {
    return this.pieces.size() > 0 && this.pieces.stream().anyMatch(p -> p.equals(piece));
  }

  public boolean containsWeapon(Weapon weapon) {
    return this.weapons.size() > 1 && this.weapons.stream().anyMatch(w -> w.equals(weapon));
  }

  public Set<BoardLocation> getAdjacentTo() {
    return this.adjacentTo;
  }

  public String getName() {
    return this.name;
  }

  public Set<Piece> getPieces() {
    return this.pieces;
  }

  public BoardLocation getSecretPassage() {
    return this.secretPassage;
  }

  public BoardLocationType getType() {
    return this.type;
  }

  public Set<Weapon> getWeapons() {
    return this.weapons;
  }

  public void removePiece(Piece piece) {
    this.pieces.removeIf(p -> p.equals(piece));
  }

  public void removeWeapon(Weapon weapon) {
    this.weapons.removeIf(w -> w.equals(weapon));
  }
}
