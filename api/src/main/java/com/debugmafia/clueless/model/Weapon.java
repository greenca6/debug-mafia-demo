package com.debugmafia.clueless.model;

import java.util.UUID;

public class Weapon {
  private String weaponName;
  private UUID uuid = UUID.randomUUID();

  public Weapon(String name) {
    this.weaponName = name;
  }

  public String getWeaponname() {
    return this.weaponName;
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

    if (!w.getWeaponname().equals(this.weaponName) || !w.getUuid().equals(this.uuid)) {
      return false;
    }

    return true;
  }
}
