package com.debugmafia.clueless.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Optional;

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
  private List<Player> activePlayers;
  private GameState gameState;
  private String[] pieceNames = {"miss scarlet", "mrs. peacock", "colonel mustard", "mrs. white", "mr. green", "professor plum"};


  public Game(Set<Player> players) {
    this.board = new Board();

    sortAndAssignPlayerList(players);
    dealDeck();
    setCurrentPlayerTurn(activePlayers.get(0));
    gameState = GameState.IN_PROGRESS;
  }

  public Board getBoard() {
    return this.board;
  }

  public Turn getCurrentPlayersTurn() {
    return this.currentPlayersTurn;
  }

  public List<Player> getPlayers() {
    return this.activePlayers;
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

    Optional<Player> playerToRebut = this.activePlayers.stream()
        .filter(p -> p.hasCard(suggestedWeapon) || p.hasCard(suggestedRoom) || p.hasCard(suggestedPiece) && !p.equals(currentPlayersTurn.getPlayer())).findFirst();

    if (playerToRebut.isPresent()) {

        // TODO: set player as player to request rebuttal from inside Turn instance
        currentPlayersTurn.setTurnState(TurnState.WAITING_FOR_REBUTTAL);
        // TODO: Remove SUGGEST and MOVE as available actions for the current players
        // turn
        // PLEASE CHECK: Set the suggestion in the turn object as the suggestion that was just
        // made
        currentPlayersTurn.setSuggestion(s);
      

    } else {
      // TODO: Remove SUGGEST and MOVE as available actions for the current players
      // turn
      currentPlayersTurn.setTurnState(TurnState.IN_PROGRESS);
    }
    return this;
  }

  public Game makeAccusation(Accusation a) {
    // Restrictions: The player making the accusation must be the player whose
    // current turn it is
    Card accusedWeapon = this.board.getAssociatedCard(a.getWeapon());
    Card accusedLocation = this.board.getAssociatedCard(a.getRoom());
    Card accusedPiece = this.board.getAssociatedCard(a.getPiece());

    Set<Card> accusedCards = new HashSet<>(Arrays.asList(accusedWeapon, accusedLocation, accusedPiece));

    if (winningCards.containsAll(accusedCards)) {
      // TODO: Set the winner
      gameState = GameState.COMPLETE;
      // PLEASE CHECK: Set the accusation object within the current players turn to this
      // accusation
      currentPlayersTurn.setAccusation(a);
    } else {
      // TODO: Remove this player from the list of active players

      // PLEASE CHECK: Set the accusation object within the current players turn to this
      // accusation.
      currentPlayersTurn.setAccusation(a);
      // TODO: Set the available actions within the current players turn to END_TURN.
      currentPlayersTurn.setTurnState(TurnState.WAITING_FOR_END_TURN);
    }

    return this;
  }

  public Game makeRebuttal(Rebuttal r) {
    // Restrictions: The player making the rebuttal must be the person who was
    // requested a rebuttal from. The card in the rebuttal must be one of the cards
    // that was made in the original suggestion.
    // PLEASE CHECK: Set the rebuttal object to the current rebuttal on the current players
    // turn
    currentPlayersTurn.setRebuttal(r);
    currentPlayersTurn.setTurnState(TurnState.WAITING_FOR_END_TURN);
    // TODO: Set the available actions to only END_TURN on the current players turn
    return this;
  }

  public Game endTurn(Player p) {
    setCurrentPlayerTurn(getNextActivePlayer());

    BoardLocation location = this.board.getPieceLocation(p.getPiece());
    Set<BoardLocation> openAdjacentLocations = this.board.getOpenAdjacentLocations(location);
    // TODO: Set the available locations property on the current turn object to the
    // result from above
    return this;
  }

  private Player getNextActivePlayer() {
    int currentPlayerNum = activePlayers.indexOf(currentPlayersTurn.getPlayer());

    if (currentPlayerNum < (activePlayers.size() - 1)) {
      return activePlayers.get(currentPlayerNum + 1);
    } else {
      return activePlayers.get(0);
    }

  }

  private void setCurrentPlayerTurn(Player p)
  {
    this.currentPlayersTurn = new Turn(p, new HashSet<>());
  }

  private void sortAndAssignPlayerList(Set<Player> players)
  {
    /*need to sort list such that players go in order of charcter.
    character move order is as follows:
      Miss Scarlet
      Mrs Peacock
      Colonel Mustard
      Mrs. White
      Mr. Green
      Professor Plum
    */
    this.activePlayers = new ArrayList<Player>(players.size());
    int currentPlayerNum = 0;

    for(String character: pieceNames)
    {

      Optional<Player> playerToAdd = players.stream().filter(p ->p.getPiece().getName().equals(character)).findFirst();
      if(playerToAdd.isPresent())
      {
        this.activePlayers.add(currentPlayerNum++, playerToAdd.get());
      }

    }
  }

  private void dealDeck()
  {
    
    this.winningCards = new HashSet<>(this.board.drawWinningCards());
    //21 is the size of the deck. Need to determine how many "left over"
    //cards there are in case there is not an even number of cards to 
    //go around
    int leftOverCards = 21 % activePlayers.size();
    int numToDraw = (int)(21.0/(double)activePlayers.size());

    for(Player p : activePlayers)
    {
      if(leftOverCards != 0)
      {
        p.dealCards(this.board.draw(numToDraw+1));
        leftOverCards--;
      }
      else
      {
        p.dealCards(this.board.draw(numToDraw));
      }
    }
  }

}
