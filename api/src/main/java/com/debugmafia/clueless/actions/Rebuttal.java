package com.debugmafia.clueless.actions;

import com.debugmafia.clueless.model.Player;
import com.debugmafia.clueless.model.Card;

public class Rebuttal {
    private Player player;
    private Card card;

    public Rebuttal(Player player, Card card) {
      this.player = player;
      this.card = card;
    }

    public Player getPlayer() {
      return this.player;
    }

    public Card getCard() {
      return this.card;
    }

    public void setPlayer(Player p) {
      this.player = p;
    }

    public void setCard(Card c) {
      this.card = c;
    }
  }
