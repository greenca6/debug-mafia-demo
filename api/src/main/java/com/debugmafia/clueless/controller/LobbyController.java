package com.debugmafia.clueless.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.debugmafia.clueless.model.Player;
import com.debugmafia.clueless.state.Lobby;
import com.debugmafia.clueless.state.Game;

@Controller
@MessageMapping("socket")
public class LobbyController {

  @RequestMapping(value = "/lobby", method = RequestMethod.GET)
  public HTTPResponseMessage<Lobby> getLobby() throws Exception {
    return new HTTPResponse();
  }

  @MessageMapping("playerJoin")
  public Lobby joinPlayer(Player p) throws Exception {

  }

  @MessageMapping("gameStart")
  public Game startGame() throws Exception {

  }

}
