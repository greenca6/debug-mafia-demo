package com.debugmafia.clueless.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

  @RequestMapping(value = "/status", method= RequestMethod.GET)
  public HTTPResponse getStatus() throws Exception {
    return new HTTPResponse();
  }

}
