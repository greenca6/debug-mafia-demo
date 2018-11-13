package main.java.com.debugmafia.demo;

import java.util.UUID;

public class Weapon
{
    private String weaponName;
    public UUID uuid;

    public Weapon(string name)
    {
        this.weaponName = name;
        uuid = UUID.randomUUID();        
    }
}