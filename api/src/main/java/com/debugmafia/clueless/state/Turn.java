package com.debugmafia.clueless.state;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.debugmafia.clueless.actions.Accusation;
import com.debugmafia.clueless.actions.Move;
import com.debugmafia.clueless.actions.Rebuttal;
import com.debugmafia.clueless.actions.Suggestion;
import com.debugmafia.clueless.model.BoardLocation;
import com.debugmafia.clueless.model.Player;

public class Turn {
  private Player player;
  private Set<BoardLocation> availableLocations;
  private Player requestRebuttalFrom;
  private Move move;
  private Suggestion suggestion;
  private Accusation accusation;
  private Rebuttal rebuttal;
  private Set<ActionType> availableActions = new HashSet<>(Arrays.asList(
    ActionType.MOVE,
    ActionType.SUGGEST,
    ActionType.ACCUSE,
    ActionType.END_TURN
  ));
  private TurnState state = TurnState.IN_PROGRESS;

  public Turn(Player player, Set<BoardLocation> availableLocations) {
    this.player = player;
    this.availableLocations = availableLocations;
  }

  public Accusation getAccusation() {
    return this.accusation;
  }

  public Set<ActionType> getAvailableActions() {
    return this.availableActions;
  }

  public Set<BoardLocation> getAvailableLocations() {
    return this.availableLocations;
  }

  public Move getMove() {
    return this.move;
  }

  public Player getPlayer() {
    return this.player;
  }

  public Rebuttal getRebuttal() {
    return this.rebuttal;
  }

  public Player getRequestRebuttalFrom() {
    return this.requestRebuttalFrom;
  }

  public Suggestion getSuggestion() {
    return this.suggestion;
  }

  public TurnState getTurnState() {
    return this.state;
  }

  public void removeAvailableAction(ActionType action) {
    this.availableActions.removeIf(a -> a.equals(action));
  }

  public void setAccustion(Accusation accusation) {
    this.accusation = accusation;
  }

  public void setMove(Move move) {
    this.move = move;
  }

  public void setRebuttal(Rebuttal rebuttal) {
    this.rebuttal = rebuttal;
  }

  public void setRequestRebuttalFrom(Player player) {
    this.requestRebuttalFrom = player;
  }

  public void setSuggestion(Suggestion suggestion) {
    this.suggestion = suggestion;
  }

  public void setTurnState(TurnState state) {
    this.state = state;
  }
}
