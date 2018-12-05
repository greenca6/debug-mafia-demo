package com.debugmafia.clueless.state;

import java.util.HashSet;
import java.util.Set;

import com.debugmafia.clueless.model.Piece;
import com.debugmafia.clueless.model.PieceFactory;
import com.debugmafia.clueless.model.Player;

public class Lobby {
  private boolean canStartGame = false;
  private Set<Player> connectedPlayers = new HashSet<>();
  private Set<Piece> availablePieces = PieceFactory.createPieces();

  private final int MINIMUM_PLAYERS = 3;
  private final int MAXIMUM_PLAYERS = 6;

  public boolean getCanStartGame() {
    return this.canStartGame;
  }

  public Set<Player> getConnectedPlayers() {
    return this.connectedPlayers;
  }

  public Set<Piece> getAvailablePieces() {
    return this.availablePieces;
  }

  public Lobby addPlayer(Player player) {
    // TODO: validation/error checking.
    // 1. Can't add player if they hold a piece that is not in available pieces array
    // 2. Can't add player if we are already at MAXIMUM_PLAYERS

    this.connectedPlayers.add(player);
    this.availablePieces.removeIf(p -> p.equals(player.getPiece()));

    if (this.connectedPlayers.size() >= MINIMUM_PLAYERS) {
      this.canStartGame = true;
    }

    return this;
  }
}
