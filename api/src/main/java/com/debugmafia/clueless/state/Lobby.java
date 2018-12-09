package com.debugmafia.clueless.state;

import java.util.HashSet;
import java.util.Set;

import com.debugmafia.clueless.model.Piece;
import com.debugmafia.clueless.model.PieceFactory;
import com.debugmafia.clueless.model.Player;

public class Lobby {
  private boolean canStartGame = false;
  private Set<Player> connectedPlayers = new HashSet<>();
  private Set<Piece> availablePieces = new HashSet<>(PieceFactory.createPieces());

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

  @Override
  public String toString() {
    String s = "Lobby Instance: \n";

    s += "  canStartGame: " + this.canStartGame + "\n";
    s += "  connectedPlayers: \n";

    for (Player p: this.connectedPlayers) {
      s += "    " + p.toString() + "\n";
    }

    s += "  availablePieces: \n";

    for (Piece p: this.availablePieces) {
      s += "    " +  p.toString() + "\n";
    }

    return s;
  }
}
