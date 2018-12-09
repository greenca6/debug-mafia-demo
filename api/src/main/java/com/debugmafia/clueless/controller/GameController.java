package com.debugmafia.clueless.controller;

import com.debugmafia.clueless.actions.Accusation;
import com.debugmafia.clueless.actions.Move;
import com.debugmafia.clueless.actions.Rebuttal;
import com.debugmafia.clueless.actions.Suggestion;
import com.debugmafia.clueless.model.Player;
import com.debugmafia.clueless.service.GameService;
import com.debugmafia.clueless.state.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

  @Autowired
  private GameService gameService;

  @MessageMapping("/game/move")
  @SendTo("/game/onMove")
  public Game makeMove(Move move) throws Exception {
    System.out.println("hi");
    return gameService.makeMove(move);
  }

  @MessageMapping("/game/suggestion")
  @SendTo("/game/onSuggestion")
  public Game makeSuggestion(Suggestion suggestion) throws Exception {
    return gameService.makeSuggestion(suggestion);
  }

  @MessageMapping("/game/accusation")
  @SendTo("/game/onAccusation")
  public Game makeAccusation(Accusation accusation) throws Exception {
    return gameService.makeAccusation(accusation);
  }

  @MessageMapping("/game/rebuttal")
  @SendTo("/game/onRebuttal")
  public Game makeAccusation(Rebuttal rebuttal) throws Exception {
    return gameService.makeRebuttal(rebuttal);
  }

  @MessageMapping("/game/endTurn")
  @SendTo("/game/onEndTurn")
  public Game endTurn(Player player) throws Exception {
    return gameService.endTurn(player);
  }

  @MessageMapping("/game/endGame")
  @SendTo("/game/onEndGame")
  public Boolean endGame() throws Exception {
    gameService.endGame();
    return true;
  }

}
