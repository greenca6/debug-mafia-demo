package com.debugmafia.clueless.actions;

import com.debugmafia.clueless.model.Player;
import com.debugmafia.clueless.model.Piece;
import com.debugmafia.clueless.model.BoardLocation;

public class Move {
  private Player player;
  private Piece piece;
  private BoardLocation to;

  public Move() {
  }

  public Player getPlayer() {
    return this.player;
  }

  public Piece getPiece() {
    return this.piece;
  }

  public BoardLocation getTo() {
    return this.to;
  }

  public void setPlayer(Player p) {
    this.player = p;
  }

  public void setPiece(Piece p) {
    this.piece = p;
  }

  public void setTo(BoardLocation to) {
    this.to = to;
  }
}
