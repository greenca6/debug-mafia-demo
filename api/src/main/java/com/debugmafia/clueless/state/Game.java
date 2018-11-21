package com.debugmafia.clueless.state;

import com.debugmafia.clueless.actions.*;
import com.debugmafia.clueless.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.tools.DocumentationTool.Location;

public class Game {
    private Set<Card> winningCards;
    private Board boardInstance;
    private Turn turnInstance;

    public Game(Set<Player> players) {
        //TODO: initialize board
        List<Player> playerList = new ArrayList<>(players);
        //TODO: Conversion above needs to have Mrs. Scarlett first
        //TODO: Set the initialPlayers private List to the players
        //TODO: Set the game state to GameState.IN_PROGRESS

        //How to access shuffle deck from board when it's private to perform 
        //5. Shuffle the deck in the Board?
        boardInstance.shuffleDeck();
        winningCards = boardInstance.drawWinningCards();
        //TODO: Determine how many cards go to each player (the number will always be a whole number),
        //then call draw() for each player, giving them the cards that were drawn
        
        //TODO: Set the current players turn to the player who will go first, as well as other relevant turn
        //information
    }


    public Game makeMove(Move m) {
        //Restrictions: A piece cannot be moved to a location that does not exist, or to a HALLWAY location that
        //already has a piece in it. Also, the player requesting to move must be equal to the current player.
        boardInstance.movePiece(m.getPiece(), m.getTo());
        //TODO: Update the turn state for the current player
            // a. Add the move they just made
            // b. Remove MOVE as an available action for their turn (meaning they cannot make
            // another move)
        return this;
    }

    public Game makeSuggestion(Suggestion s) {
        //Restrictions: The player making the suggestion must also be the one who’s current turn it is. Also, the
        //Location part of the suggestion must equal the location of the current players piece.
        Card suggestedWeapon = boardInstance.getAssociatedCard(s.getWeapon());
        Card suggestedLocation = boardInstance.getAssociatedCard(s.getRoom();
        Card suggestedPlayer = boardInstance.getAssociatedCard(s.getPiece();
        //Can we make these into a Set for a suggestionSet?
        
        boardInstance.moveWeapons(suggestedWeapon, suggestedLocation);
        boardInstance.movePiece(suggestedPlayer, suggestedLocation);
        
        //TODO: Iterate over all players, and find the first one that has a card that was a part of the suggestion
        if(playerIterator.hasCard(suggestedWeapon)) || playerIterator.hasCard(suggestedLocation || playerIterator.hasCard(suggestedPlayer))){
            //TODO: set player as player to request rebuttal from inside Turn instance
            turnInstance.setRebuttalRequest(playerIterator);
            //TODO: Set the turn state to WAITING_FOR_REBUTTAL
            //TODO: Remove SUGGEST and MOVE as available actions for the current players turn
            //TODO: Set the suggestion in the turn object as the suggestion that was just made
            return this;
        }
        else {
            //TODO: Remove SUGGEST and MOVE as available actions for the current players turn
            //TODO: Set the turn state to IN_PROGRESS
            turnInstance.setSuggestion(suggestionSet);
            return this;
        }

    }

}