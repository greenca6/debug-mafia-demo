package main.java.com.debugmafia.demo;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;

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

    public Set<Card> draw(int numToDraw)
    {
        if(hasDeckBeenShuffled)
        {

        }
    }

    public Set<Location> getOpenAdjacentLocations(Location locationToQuery)
    {
        Set<Location> validLocations = new Set<Location>();

        for(Location l: grid)
        {
            if(locationToQuery.getXcoord() + 1 == l.getXcoord() || 
                locationToQuery.getXcoord() + 1 == l.getYcoord() )
            {
                if(l.getType() == ROOM)
                {
                    validLocations.add(l);
                }
                else if(l.getType() == HALLWAY /* and occupancy is zero */)
                {
                    validLocations.add(l);
                }
            }
            else if(locationToQuery.getSecretPassage() != null && locationToQuery.getSecretPassage() == l)
            {
                validLocations.add(l);
            }
        }

        return validLocations;
    }

    public Board movePiece(Piece p, Location to)
    {

    }

    public Board moveWeapons(Weapon w, Location to)
    {

    }

    public Card getAssociatedCard(Weapon w)
    {
        List<Card> tempList =  deckOCards.stream().filter(x-> x.getType() == WEAPON && x.getName() == w.getWeaponname()).collect(Collections.toList());

        if(tempList.size() != 1)
        {
            //error
        }
        else
        {
            return tempList.get(0);
        }
    }

    public Card getAssociatedCard(Piece p)
    {
        List<Card> tempList =  deckOCards.stream().filter(x-> x.getType() == PIECE && x.getName() == p.getName()).collect(Collections.toList());

        if(tempList.size() != 1)
        {
            //error
        }
        else
        {
            return tempList.get(0);
        }
    }

    public Card getAssociatedCard(Location l)
    {

        if(l.getType() != ROOM)
        {
            //error
        }

        List<Card> tempList =  deckOCards.stream().filter(x-> x.getType() == ROOM && x.getName() == l.getName()).collect(Collections.toList());

        if(tempList.size() != 1)
        {
            //error
        }
        else
        {
            return tempList.get(0);
        }
    }


}