package main.java.com.debugmafia.demo;

import java.util.UUID;

public class Piece
{
    private String name;
    private UUID uuid;

    public Piece(String name)
    {
        this.name = name;
        uuid = UUID.randomUUID();
    }
}