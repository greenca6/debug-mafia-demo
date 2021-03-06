package com.debugmafia.clueless.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import com.debugmafia.clueless.service.GameService;

@Controller
public class StatusController {

  @Autowired
  private GameService gameService;

  @GetMapping(path = "/api/status", produces = "application/json;charset=UTF-8")
  @ResponseBody
  public Map<String, Boolean> getStatus() throws Exception {
    Map<String, Boolean> status = new HashMap<>();

    status.put("gameIsInProgress", gameService.getGameIsInProgress());

    return status;
  }

}
