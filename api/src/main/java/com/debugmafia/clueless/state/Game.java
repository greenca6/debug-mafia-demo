package com.debugmafia.clueless.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.debugmafia.clueless.actions.Accusation;
import com.debugmafia.clueless.actions.Move;
import com.debugmafia.clueless.actions.Rebuttal;
import com.debugmafia.clueless.actions.Suggestion;
import com.debugmafia.clueless.model.Board;
import com.debugmafia.clueless.model.BoardLocation;
import com.debugmafia.clueless.model.Card;
import com.debugmafia.clueless.model.Player;

public class Game {
  private Set<Card> winningCards;
  private Board board;
  private Turn currentPlayersTurn;
  private List<Player> players;

  public Game(Set<Player> players) {
    this.board = new Board();
    this.players = new ArrayList<>(players);
    // TODO: Conversion above needs to have Mrs. Scarlett first
    // TODO: Set the initialPlayers private List to the players
    // TODO: Set the game state to GameState.IN_PROGRESS

    this.winningCards = new HashSet<>(this.board.drawWinningCards());
    // TODO: Determine how many cards go to each player (the number will always be a
    // whole number),
    // then call draw() for each player, giving them the cards that were drawn

    // TODO: Set the current players turn to the player who will go first, as well
    // as other relevant turn
    // information
  }

  public Board getBoard() {
    return this.board;
  }

  public Turn getCurrentPlayersTurn() {
    return this.currentPlayersTurn;
  }

  public List<Player> getPlayers() {
    return this.players;
  }

  public Game makeMove(Move m) {
    // Restrictions: A piece cannot be moved to a location that does not exist, or
    // to a HALLWAY location that
    // already has a piece in it. Also, the player requesting to move must be equal
    // to the current player.
    this.board.movePiece(m.getPiece(), m.getTo());
    // TODO: Update the turn state for the current player
    // a. Add the move they just made
    // b. Remove MOVE as an available action for their turn (meaning they cannot
    // make
    // another move)
    return this;
  }

  public Game makeSuggestion(Suggestion s) {
    // Restrictions: The player making the suggestion must also be the one who’s
    // current turn it is. Also, the
    // Location part of the suggestion must equal the location of the current
    // players piece.
    Card suggestedWeapon = this.board.getAssociatedCard(s.getWeapon());
    Card suggestedRoom = this.board.getAssociatedCard(s.getRoom());
    Card suggestedPiece = this.board.getAssociatedCard(s.getPiece());
    // Can we make these into a Set for a suggestionSet?

    this.board.moveWeapon(s.getWeapon(), s.getRoom());
    this.board.movePiece(s.getPiece(), s.getRoom());

    List<Player> validPlayers = this.players.stream()
        .filter(p -> p.hasCard(suggestedWeapon) || p.hasCard(suggestedRoom) || p.hasCard(suggestedPiece))
        .collect(Collectors.toList());

    if (!validPlayers.isEmpty()) {

      for (Player p : validPlayers) {
        // TODO: set player as player to request rebuttal from inside Turn instance
        // TODO: Set the turn state to WAITING_FOR_REBUTTAL
        // TODO: Remove SUGGEST and MOVE as available actions for the current players
        // turn
        // TODO: Set the suggestion in the turn object as the suggestion that was just
        // made
      }

    } else {
      // TODO: Remove SUGGEST and MOVE as available actions for the current players
      // turn
      // TODO: Set the turn state to IN_PROGRESS
    }
    return this;
  }

  public Game makeAccusation(Accusation a) {
    // Restrictions: The player making the accusation must be the player whose
    // current turn it is
    Card accusedWeapon = this.board.getAssociatedCard(a.getWeapon());
    Card accusedLocation = this.board.getAssociatedCard(a.getRoom());
    Card accusedPiece = this.board.getAssociatedCard(a.getPiece());

    Card arr[] = { accusedWeapon, accusedLocation, accusedPiece };

    Set<Card> accusedCards = new HashSet<>(Arrays.asList(arr));

    if (winningCards.containsAll(accusedCards)) {
      // TODO: Set the winner
      // TODO: Set the game state to GameState.COMPLETE
      // TODO: Set the accusation object within the current players turn to this
      // accusation.
    } else {
      // TODO: Remove this player from the list of active players
      // TODO: Set the accusation object within the current players turn to this
      // accusation.
      // TODO: Set the available actions within the current players turn to END_TURN.
      // TODO: Set the turn state within the current players turn to
      // WAITING_FOR_END_TURN
    }

    return this;
  }

  public Game makeRebuttal(Rebuttal r) {
    // Restrictions: The player making the rebuttal must be the person who was
    // requested a rebuttal from. The card in the rebuttal must be one of the cards
    // that was made in the original suggestion.
    // TODO: Set the rebuttal object to the current rebuttal on the current players
    // turn
    // TODO: Set the turn state to WAITING_FOR_END_TURN on the current players turn
    // TODO: Set the available actions to only END_TURN on the current players turn
    return this;
  }

  public Game endTurn(Player p) {
    // TODO: Find the next active player to take their turn (next in the array)
    Player nextActivePlayer = this.getNextActivePlayer(p);
    // What is the constructor for Turn object?
    Turn newTurnInstance = new Turn(nextActivePlayer, new HashSet<>());
    // TODO: Set that player as the player in the current turn instance

    BoardLocation location = this.board.getPieceLocation(p.getPiece());
    Set<BoardLocation> openAdjacentLocations = this.board.getOpenAdjacentLocations(location);
    // TODO: Set the available locations property on the current turn object to the
    // result from above
    // TODO: Set this new turn instance to the current players turn
    return this;
  }

  private Player getNextActivePlayer(Player currentPlayer) {
    int currentPlayerNum = players.indexOf(currentPlayer);

    if (currentPlayerNum < (players.size() - 1)) {
      return players.get(currentPlayerNum + 1);
    } else {
      return players.get(0);
    }

  }
}
