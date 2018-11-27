package com.debugmafia.clueless.model;

import java.util.HashSet;
import java.util.Set;

public class PieceFactory {
  public static Set<Piece> createPieces() {
    Set<Piece> pieces = new HashSet<>();

    pieces.add(new Piece("miss scarlet"));
    pieces.add(new Piece("mrs. peacock"));
    pieces.add(new Piece("colonel mustard"));
    pieces.add(new Piece("mrs. white"));
    pieces.add(new Piece("mr. green"));
    pieces.add(new Piece("professor plum"));

    return pieces;
  }
}
