package com.debugmafia.clueless.model;

import java.util.Set;

public class BoardLocation {
  private int xCoord;
  private int yCoord;
  private BoardLocationType type;
  private String name;
  private BoardLocation secretPassage;
  private Set<Weapon> weapons;
  private Set<Piece> pieces;

  public BoardLocation(int x, int y, BoardLocationType type, String name) {
    this.xCoord = x;
    this.yCoord = y;
    this.type = type;
    this.name = name;
  }

  public BoardLocation(int x, int y, BoardLocationType type, String name, BoardLocation secretPassage) {
    this.xCoord = x;
    this.yCoord = y;
    this.type = type;
    this.name = name;
    this.secretPassage = secretPassage;
  }

  public int getXcoord() {
    return this.xCoord;
  }

  public int getYcoord() {
    return this.yCoord;
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

  public Set<Weapon> getWeapons()
  {
    return this.weapons;
  }

  public void setWeapons(Set<Weapon> w)
  {
    this.weapons = w;
  }

  public Set<Piece> getPieces()
  {
    return this.pieces;
  }

  public void setPieces(Set<Piece> p)
  {
    this.pieces = p;
  }

}
