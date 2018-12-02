package com.debugmafia.clueless.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.debugmafia.clueless.model.Player;
import com.debugmafia.clueless.state.Lobby;
import com.debugmafia.clueless.state.Game;
import com.debugmafia.clueless.service.GameService;

@Controller
public class LobbyController {

  @Autowired
  private GameService gameService;

  @GetMapping(path = "/api/lobby", produces = "application/json;charset=UTF-8") 
  @ResponseBody
  public Lobby getLobby() throws Exception {
    return gameService.getLobby();
  }

  @MessageMapping("/lobby/join")
  @SendTo("/lobby/join")
  public Lobby joinPlayer(Player p) throws Exception {
    return gameService.joinPlayer(p);
  }

  @MessageMapping("/lobby/start")
  @SendTo("/lobby/start")
  public Game startGame() throws Exception {
    return gameService.startGame();
  }

}
