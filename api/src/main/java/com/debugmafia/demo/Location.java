package main.java.com.debugmafia.demo;

public class Location
{
    private int xCoord;
    private int yCoord;
    private LocationType type;
    private String name;
    private Location secretPassage;

    public Location(int x, int y, LocationType type, String name)
    {
        this.xCoord = x;
        this.yCoord = y;
        this.type = type;
        this.name = name;
    }

    public Location(int x, int y, LocationType type, String name, Location secretPassage)
    {
        this.xCoord = x;
        this.yCoord = y;
        this.type = type;
        this.name = name;
        this.secretPassage = secretPassage;
    }
    
	public int getXcoord()
	{
		return this.xCoord;
	}

	public int getYcoord()
	{
		return this.yCoord;
	}

    public LocationType getType()
	{
		return this.type;
	}

	public String getName()
	{
		return this.name;
	}

	public Location getSecretPassage()
	{
		return this.secretPassage;
	}

}