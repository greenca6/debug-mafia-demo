package com.debugmafia.clueless.state;

import java.util.HashSet;
import java.util.Set;

import com.debugmafia.clueless.model.Piece;
import com.debugmafia.clueless.model.Player;

public class Lobby {
  private boolean canStartGame;
  private Set<Player> connectedPlayers = new HashSet<>();
  private Set<Piece> availablePieces;
}
