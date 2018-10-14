package com.debugmafia.demo;

public class Player {

    private String userName;
    private int playerNum;
    private String character;
    private boolean isInControl;

    public Player() {
    }

    public Player(String userName, int playerNum) {
        this.userName = userName;
        this.playerNum = playerNum;
    }

    public String getContent() {
        return content;
    }

	public String getUsername()
	{
		return this.userName;
	}

	public int getPlayernum()
	{
		return this.playerNum;
	}

	public void setPlayernum(int playerNum)
	{
		this.playerNum = playerNum;
	}

	public String getCharacter()
	{
		return this.character;
	}

	public void setCharacter(String character)
	{
		this.character = character;
	}

	public boolean getIsincontrol()
	{
		return this.isInControl;
	}

	public void setIsincontrol(boolean isInControl)
	{
		this.isInControl = isInControl;
	}

}