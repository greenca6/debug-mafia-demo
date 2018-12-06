package com.debugmafia.clueless.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BoardLocation {
  private BoardLocationType type;
  private UUID uuid = UUID.randomUUID();
  private String name;
  private UUID secretPassage;
  private Set<UUID> adjacentTo = new HashSet<>();
  private Set<Weapon> weapons = new HashSet<>();
  private Set<Piece> pieces = new HashSet<>();

  public BoardLocation(BoardLocationType type, String name) {
    this.type = type;
    this.name = name;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public void addAdjacentLocation(BoardLocation location) {
    this.adjacentTo.add(location.getUuid());
  }

  public void addPiece(Piece piece) {
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

  public Set<UUID> getAdjacentTo() {
    return this.adjacentTo;
  }

  public String getName() {
    return this.name;
  }

  public Set<Piece> getPieces() {
    return this.pieces;
  }

  public UUID getSecretPassage() {
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

  public void setSecretPassage(BoardLocation location) {
    // TODO: fail if location type isn't room?
    this.secretPassage = location.getUuid();
  }
}
