package com.debugmafia.clueless.model;

import java.util.UUID;

public class Weapon {
  private String weaponName;
  private UUID uuid;

  public Weapon(String name) {
    this.weaponName = name;
    uuid = UUID.randomUUID();
  }

  public String getWeaponname() {
    return this.weaponName;
  }

  public UUID getUuid() {
    return this.uuid;
  }
}
