package com.debugmafia.clueless.actions;

import com.debugmafia.clueless.model.Player;
import com.debugmafia.clueless.model.Piece;
import com.debugmafia.clueless.model.Weapon;
import com.debugmafia.clueless.model.BoardLocation;

public class Accusation {
    private Player player;
    private Piece piece;
    private BoardLocation room;
    private Weapon weapon;
  
    public Accusation() {
    }
  
    public Player getPlayer() {
      return this.player;
    }
  
    public Piece getPiece() {
      return this.piece;
    }
  
    public BoardLocation getRoom() {
      return this.room;
    }

    public Weapon getWeapon(){
        return this.weapon;
    }
  
    public void setPlayer(Player p) {
      this.player = p;
    }
  
    public void setPiece(Piece p) {
      this.piece = p;
    }
  
    public void setRoom(BoardLocation room) {
      this.room = room;
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }
  }