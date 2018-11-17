package com.debugmafia.clueless.model;

public class BoardLocation {
  private int xCoord;
  private int yCoord;
  private BoardLocationType type;
  private String name;
  private BoardLocation secretPassage;

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

}
