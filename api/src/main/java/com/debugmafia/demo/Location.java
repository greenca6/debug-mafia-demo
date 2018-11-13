package main.java.com.debugmafia.demo;

public class Location
{
    private int xCoord;
    private int yCoord;
    private LocationType type;
    private String name;

    public Location(int x, int y, LocationType type, String name)
    {
        this.xCoord = x;
        this.yCoord = y;
        this.type = type;
        this.name = name;
    }
}