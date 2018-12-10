package com.debugmafia.clueless.model;

import java.util.HashSet;
import java.util.Set;

public class WeaponFactory {
  public static Set<Weapon> createWeapons() {
    Set<Weapon> weapons = new HashSet<>();

    weapons.add(new Weapon("rope"));
    weapons.add(new Weapon("lead pipe"));
    weapons.add(new Weapon("knife"));
    weapons.add(new Weapon("wrench"));
    weapons.add(new Weapon("candlestick"));
    weapons.add(new Weapon("pistol"));

    return weapons;
  }
}
