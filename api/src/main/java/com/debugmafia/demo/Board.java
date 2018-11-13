package main.java.com.debugmafia.demo;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Board
{
    private Location[][] grid;
    private Set<Card> deckOCards;
    private Set<Piece> gamePieces;
    private Set<Weapons> gameWeapons;
    private bool hasDeckBeenShuffled;
    private Set<Card> winningCards;

    public Board()
    {

    }

    private void shuffleDeck()
    {
        List<Card> cardList = new ArrayList<>(deckOCards);
        Collections.shuffle(cardList);
        deckOCards = new HashSet<Card>(cardList);
        hasDeckBeenShuffled = true;
    }

    public Set<Card> drawWinningCards()
    {
        Set<Card> cardsToReturn;

        if(winningCards == null)
        {
            cardsToReturn = new Set<Card>();
            if(!hasDeckBeenShuffled)
            {
                shuffleDeck();
            }
            hasDeckBeenShuffled = true;
            
            for(Card c: deckOCards)
            {
                List<Card> filteredList = cardsToReturn.stream().filter( x-> x.getType() == c.getType()).collect(Collections.toList());
                if(filteredList.isEmpty())
                {
                    cardsToReturn.add(c);
                }
            }
            
            winningCards = cardsToReturn;
        }
        else
        {
            cardsToReturn =  winningCards;
        }
        return cardsToReturn;
    }
}