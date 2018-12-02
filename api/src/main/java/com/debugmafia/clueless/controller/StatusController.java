package com.debugmafia.clueless.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import com.debugmafia.clueless.service.GameService;

@RestController
public class StatusController {

  @Autowired
  private GameService gameService;

  @GetMapping(path = "/api/status", produces = "application/json;charset=UTF-8") 
  @ResponseBody
  public Boolean getStatus() throws Exception {
    return gameService.getGameIsInProgress();
  }

}
