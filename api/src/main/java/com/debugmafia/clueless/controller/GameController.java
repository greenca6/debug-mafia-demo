package com.debugmafia.clueless.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.debugmafia.clueless.model.Player;
import com.debugmafia.clueless.state.Game;
import com.debugmafia.clueless.service.GameService;
import com.debugmafia.clueless.actions.*;

@Controller
public class GameController {

  @Autowired
  private GameService gameService;

  @MessageMapping("/game/move")
  @SendTo("/game/move")
  public Game makeMove(Move m) throws Exception {
    return gameService.makeMove(m);
  }

  @MessageMapping("/game/suggestion")
  @SendTo("/game/suggestion")
  public Game makeSuggestion(Suggestion s) throws Exception {
    return gameService.makeSuggestion(s);
  }

  @MessageMapping("/game/accusation")
  @SendTo("/game/accusation")
  public Game makeAccusation(Accusation a) throws Exception {
    return gameService.makeAccusation(a);
  }

  @MessageMapping("/game/rebuttal")
  @SendTo("/game/rebuttal")
  public Game makeAccusation(Rebuttal r) throws Exception {
    return gameService.makeRebuttal(r);
  }

  @MessageMapping("/game/endTurn")
  @SendTo("/game/endTurn")
  public Game endTurn(Player p) throws Exception {
    return gameService.endTurn(p);
  }

  @MessageMapping("/game/endGame")
  @SendTo("/game/endGame")
  public Boolean endGame() throws Exception {
    gameService.endGame();
    return true;
  }


}
