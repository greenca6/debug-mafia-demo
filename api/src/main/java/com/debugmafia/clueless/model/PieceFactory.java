package com.debugmafia.clueless.model;

import java.util.HashSet;
import java.util.Set;

/**
 * PieceFactory creates the set of pieces to be used within the game. It will cache a set of
 * pieces, so that only one set is ever created. This is needed because the same instance of the
 * pieces need to be passed around to the various callers (Lobby and Board).
 *
 * In the future, this (and the other Factory classes) could and probably should be moved to a
 * data storage layer. This way the components of the game that are "global" (pieces, cards,
 * weapons, etc) are persisted and can be accessed by any caller in the same way.
 *
 */
public class PieceFactory {
  static Set<Piece> pieces = new HashSet<>();

  public static Set<Piece> createPieces() {
    if (pieces.size() > 0) {
      return pieces;
    }

    pieces.add(new Piece("miss scarlet"));
    pieces.add(new Piece("mrs. peacock"));
    pieces.add(new Piece("colonel mustard"));
    pieces.add(new Piece("mrs. white"));
    pieces.add(new Piece("mr. green"));
    pieces.add(new Piece("professor plum"));

    return pieces;
  }
}
