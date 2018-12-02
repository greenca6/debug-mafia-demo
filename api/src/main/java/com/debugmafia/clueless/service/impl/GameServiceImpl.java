package com.debugmafia.clueless.service.impl;

import org.springframework.stereotype.Service;

import com.debugmafia.clueless.service.GameService;
import com.debugmafia.clueless.model.Player;
import com.debugmafia.clueless.state.Lobby;
import com.debugmafia.clueless.state.Game;
import com.debugmafia.clueless.actions.*;

@Service
public class GameServiceImpl implements GameService{

    private Boolean gameIsInProgress = false;
    private Game gameInstance;
    private Lobby lobbyInstance;

    public GameServiceImpl(){
        lobbyInstance = new Lobby();
    }

    public Boolean getGameIsInProgress(){
        return gameIsInProgress;
    }

    public Lobby joinPlayer(Player p){
        return lobbyInstance.addPlayer(p);
    }

    public Game startGame(){
        gameInstance = new Game(lobbyInstance.getConnectedPlayers());
        gameIsInProgress = true;
        return gameInstance;
    }

    public Game makeMove(Move m){
        return gameInstance.makeMove(m);
    }

    public Game makeSuggestion(Suggestion s){
        return gameInstance.makeSuggestion(s);
    }

    public Game makeAccusation(Accusation a){
        return gameInstance.makeAccusation(a);
    }

    public Game makeRebuttal(Rebuttal r){
        return gameInstance.makeRebuttal(r);
    }

    public Game endTurn(Player p){
        return gameInstance.endTurn(p);
    }

    public Lobby getLobby(){
        return lobbyInstance;
    }

    public void endGame()
    {
        gameInstance = null;
        gameIsInProgress = false;
    }

}