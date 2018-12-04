package com.debugmafia.clueless.service;

import com.debugmafia.clueless.model.Player;
import com.debugmafia.clueless.state.Lobby;
import com.debugmafia.clueless.state.Game;
import com.debugmafia.clueless.actions.*;

public interface GameService{
    Boolean getGameIsInProgress();
    Lobby joinPlayer(Player p);
    Game startGame();
    Game makeMove(Move m);
    Game makeSuggestion(Suggestion s);
    Game makeAccusation(Accusation a);
    Game makeRebuttal(Rebuttal r);
    Game endTurn(Player p);
    void endGame();
    Lobby getLobby();
}