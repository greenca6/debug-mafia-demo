package com.debugmafia.clueless.model;

import java.util.UUID;

public class Weapon {
  private String name;
  private UUID uuid = UUID.randomUUID();

  public Weapon(String name, UUID uuid) {
    this.name = name;
    this.uuid = uuid;
  }

  public Weapon(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    final Weapon w = (Weapon) obj;

    if (!w.getName().equals(this.name) || !w.getUuid().equals(this.uuid)) {
      return false;
    }

    return true;
  }
}
