package com.debugmafia.clueless.model;

import java.util.UUID;

public class Piece {
  private String name;
  private UUID uuid;

  public Piece(String name) {
    this.name = name;
    uuid = UUID.randomUUID();
  }

  public String getName() {
    return this.name;
  }

  public UUID getUuid() {
    return this.uuid;
  }
}
