package com.debugmafia.clueless.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;

import com.debugmafia.clueless.actions.*;
import com.debugmafia.clueless.model.Board;
import com.debugmafia.clueless.model.BoardLocation;
import com.debugmafia.clueless.model.Card;
import com.debugmafia.clueless.model.Player;
import com.debugmafia.clueless.state.ActionType;

public class Game {
  private Set<Card> winningCards;
  private Board board;
  private Turn currentPlayersTurn;
  private List<Player> activePlayers;
  private List<Player> players;
  private GameState gameState;
  private String[] pieceNames = { "miss scarlet", "mrs. peacock", "colonel mustard", "mrs. white", "mr. green",
      "professor plum" };
  private Player winner;

  public Game(Set<Player> players) {
    this.board = new Board();
    this.gameState = GameState.IN_PROGRESS;
    this.players = new ArrayList<>(players);

    this.sortAndAssignPlayerList(players);
    this.dealDeck();
    this.setCurrentPlayerTurn(activePlayers.get(0));
  }

  public Board getBoard() {
    return this.board;
  }

  public Turn getCurrentPlayersTurn() {
    return this.currentPlayersTurn;
  }

  public GameState getGameState() {
    return this.gameState;
  }

  public List<Player> getPlayers() {
    return this.players;
  }

  public Player getWinner() {
    return this.winner;
  }

  public List<Player> getActivePlayers() {
    return this.activePlayers;
  }

  public Game makeMove(Move move) {
    // Restrictions: A piece cannot be moved to a location that does not exist, or
    // to a HALLWAY location that already has a piece in it. Also, the player
    // requesting to move must be equal to the current player.
    if (move.getPlayer().equals(this.currentPlayersTurn.getPlayer())) {
      this.board.movePiece(move.getPiece(), move.getTo());
      this.currentPlayersTurn.setState(TurnState.IN_PROGRESS);
      this.currentPlayersTurn.setMove(move);
      this.currentPlayersTurn.removeAvailableAction(ActionType.MOVE);
    }
    return this;
  }

  public Game makeSuggestion(Suggestion suggestion) {
    // Restrictions: The player making the suggestion must also be the one who’s
    // current turn it is. Also, the Location part of the suggestion must equal
    // the location of the current players piece.

    if (suggestion.getPlayer().equals(currentPlayersTurn.getPlayer())
        && suggestion.getRoom().containsPiece(this.currentPlayersTurn.getPlayer().getPiece())) {
      Card suggestedWeapon = this.board.getAssociatedCard(suggestion.getWeapon());
      Card suggestedRoom = this.board.getAssociatedCard(suggestion.getRoom());
      Card suggestedPiece = this.board.getAssociatedCard(suggestion.getPiece());

      this.board.moveWeapon(suggestion.getWeapon(), suggestion.getRoom());
      this.board.movePiece(suggestion.getPiece(), suggestion.getRoom());

      Optional<Player> playerToRebut = this.players.stream().filter(p -> {
        if (p.equals(currentPlayersTurn.getPlayer())) {
          return false;
        }

        if (p.hasCard(suggestedPiece) || p.hasCard(suggestedRoom) || p.hasCard(suggestedWeapon)) {
          return true;
        }

        return false;
      }).findFirst();

      System.out.println(playerToRebut.get());

      if (playerToRebut.isPresent()) {
        Player player = playerToRebut.get();
        Set<Card> rebuttalCardsToChooseFrom = new HashSet<>(
          Arrays.asList(suggestedWeapon, suggestedRoom, suggestedPiece)
        );
        rebuttalCardsToChooseFrom.removeIf(c -> !player.hasCard(c));

        this.currentPlayersTurn.setRequestRebuttalFrom(player);
        this.currentPlayersTurn.setState(TurnState.WAITING_FOR_REBUTTAL);
        this.currentPlayersTurn
            .removeAvailableAction(new HashSet<>(Arrays.asList(ActionType.MOVE, ActionType.SUGGEST)));
        this.currentPlayersTurn.setSuggestion(suggestion);
        this.currentPlayersTurn.setRebuttalCardsToChooseFrom(rebuttalCardsToChooseFrom);
      } else {
        this.currentPlayersTurn.setState(TurnState.WAITING_FOR_END_TURN);
        this.currentPlayersTurn
            .removeAvailableAction(new HashSet<>(Arrays.asList(ActionType.MOVE, ActionType.SUGGEST)));
      }
    }

    return this;
  }

  public Game makeAccusation(Accusation accusation) {
    // Restrictions: The player making the accusation must be the player whose
    // current turn it is.
    if (accusation.getPlayer().equals(this.currentPlayersTurn.getPlayer())) {
      Card accusedWeapon = this.board.getAssociatedCard(accusation.getWeapon());
      Card accusedLocation = this.board.getAssociatedCard(accusation.getRoom());
      Card accusedPiece = this.board.getAssociatedCard(accusation.getPiece());

      Set<Card> accusedCards = new HashSet<>(Arrays.asList(accusedWeapon, accusedLocation, accusedPiece));

      if (winningCards.containsAll(accusedCards)) {
        this.winner = accusation.getPlayer();
        this.gameState = GameState.COMPLETE;
        this.currentPlayersTurn.setAccusation(accusation);
      } else {
        this.activePlayers.remove(accusation.getPlayer());
        this.currentPlayersTurn.setAccusation(accusation);
        this.currentPlayersTurn.setState(TurnState.WAITING_FOR_END_TURN);
        this.currentPlayersTurn.removeAvailableAction(
            new HashSet<>(Arrays.asList(ActionType.ACCUSE, ActionType.MOVE, ActionType.SUGGEST)));
      }
    }
    return this;
  }

  public Game makeRebuttal(Rebuttal rebuttal) {
    // Restrictions: The player making the rebuttal must be the person who was
    // requested a rebuttal from. The card in the rebuttal must be one of the cards
    // that was made in the original suggestion.
    if (rebuttal.getPlayer().equals(this.currentPlayersTurn.getRequestRebuttalFrom())) {
      this.currentPlayersTurn.setRebuttal(rebuttal);
      this.currentPlayersTurn.setState(TurnState.WAITING_FOR_END_TURN);
      this.currentPlayersTurn
          .removeAvailableAction(new HashSet<>(Arrays.asList(ActionType.ACCUSE, ActionType.MOVE, ActionType.SUGGEST)));
    }
    return this;
  }

  public Game endTurn(Player player) {
    setCurrentPlayerTurn(getNextActivePlayer());
    return this;
  }

  private Player getNextActivePlayer() {
    int currentPlayerNum = this.activePlayers.indexOf(this.currentPlayersTurn.getPlayer());

    if (currentPlayerNum < (this.activePlayers.size() - 1)) {
      return this.activePlayers.get(currentPlayerNum + 1);
    } else {
      return this.activePlayers.get(0);
    }

  }

  private void setCurrentPlayerTurn(Player player) {
    Set<BoardLocation> availableLocations = new HashSet<>();
    BoardLocation location = this.board.getPieceLocation(player.getPiece());

    // First moves by any player will have null locations - each piece has a special
    // starting position that only allows for 1 move into a hallway
    if (location == null) {
      availableLocations.add(this.board.getStartingMoveLocation(player.getPiece()));
    } else {
      availableLocations.addAll(this.board.getOpenAdjacentLocations(location));
    }

    this.currentPlayersTurn = new Turn(player, availableLocations);

  }

  private void sortAndAssignPlayerList(Set<Player> players) {
    /*
     * need to sort list such that players go in order of character. character move
     * order is as follows: Miss Scarlet Mrs Peacock Colonel Mustard Mrs. White Mr.
     * Green Professor Plum
     */
    this.activePlayers = new ArrayList<Player>(players.size());
    int currentPlayerNum = 0;

    for (String character : pieceNames) {
      Optional<Player> playerToAdd = players.stream().filter(p -> p.getPiece().getName().equals(character)).findFirst();
      if (playerToAdd.isPresent()) {
        this.activePlayers.add(currentPlayerNum++, playerToAdd.get());
      }
    }
  }

  private void dealDeck() {
    this.winningCards = new HashSet<>(this.board.drawWinningCards());
    // 18 is the size of the deck after drawing the winning cards.
    // Need to determine how many "left over" cards there are in case there is not
    // an even number of cards to go around
    int leftOverCards = 18 % activePlayers.size();
    int numToDraw = (int) (18.0 / (double) players.size());

    for (Player p : activePlayers) {
      if (leftOverCards != 0) {
        p.dealCards(this.board.draw(numToDraw + 1));
        leftOverCards--;
      } else {
        p.dealCards(this.board.draw(numToDraw));
      }
    }
  }
}
