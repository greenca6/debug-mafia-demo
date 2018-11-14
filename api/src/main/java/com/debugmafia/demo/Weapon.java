package main.java.com.debugmafia.demo;

import java.util.UUID;

public class Weapon
{
    private String weaponName;
    private UUID uuid;

    public Weapon(string name)
    {
        this.weaponName = name;
        uuid = UUID.randomUUID();        
    }

    public String getWeaponname()
	{
		return this.weaponName;
	}
	public UUID getUuid()
	{
		return this.uuid;
	}
}