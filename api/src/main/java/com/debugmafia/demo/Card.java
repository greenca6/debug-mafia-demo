package main.java.com.debugmafia.demo;

import java.util.UUID;

public class Card
{
    private CardType type;
    private String name;
    private UUID uuid;

    public Card(CardType type, String name)
    {
        this.type = type;
        this.name = name;
        uuid = UUID.randomUUID();
    }

    public CardType getType()
	{
		return this.type;
	}

	public String getName()
	{
		return this.name;
	}

}