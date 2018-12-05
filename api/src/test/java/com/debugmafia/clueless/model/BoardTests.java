package com.debugmafia.clueless.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BoardTests {
  private Board board;

  @Before
  public void setup() {
    board = new Board();
  }

  @Test
  public void constructor_shouldCreateAllLocations() {
    // create Board
    // get all locations
    // assert that locations equal an array of locations we have locally here
  }

  @Test
  public void constructor_shouldCreateADeckOfCards() {
    // create Board
    // get deck of cards
    // assert that it contains 6 weapon cards, 6 piece cards, 9 room cards
  }

  @Test
  public void constructor_shouldCreateAllPieces() {
    // create Board
    // get all pieces
    // assert that there are 6 unique pieces
  }

  @Test
  public void constructor_shouldShuffleTheDeckOfCards() {
    // create Board
    // assert that shuffleCards() has been called
  }

  @Test
  public void draw_shouldDrawTheAmountOfCardsGiven() {
    // create Board
    // call draw with positive integer
    // get length of recieved cards, assert it equals amount drawn
  }

  @Test
  public void draw_shouldThrowAnError_whenGivenANegativeInteger() {
    // create Board
    // call draw with negative integer
    // expect failure
  }

  @Test
  public void draw_shouldThrowAnError_whenGivenMoreCardsToDrawThanAvailable() {
    // create Board
    // call draw once, drawing all the cards
    // call draw again, drawing one card
    // expect failure
  }

  @Test
  public void drawWinningCards_shouldDrawThreeCardsOfUniqueTypes() {
    // create Board
    // call drawWinningCards
    // assert that all three cards are unique types
  }

  @Test
  public void getOpenAdjacentLocations_whenGivenHallwayLocation_shouldReturnTwoRooms() {
    // create Board
    // Create the following 3 scenarios:
    //   1. Hallway with 2 empty rooms adjacent
    //   2. Hallway with only 1 empty room adjacent
    //   3. Hallway with 0 empty rooms adjacent
    // Assert that in each case, calling getOPenAdjacentLocations returns the two rooms
  }

  @Test
  public void getOpenAdjacentLocations_whenGivenSideRoom_shouldReturnThreeHallwaysAtMost() {
    // Create Board
    // Using as "side" room (room not on a corner spot), create four scenarios by deliberately moving pieces beforehand:
    //   1. All (3) hallway spaces available
    //   2. All but one (2) hallways spaces available
    //   3. Only one hallway spaces available
    //   4. No hallway spaces available
    // For each of these, assert that when we call getOpenAdjacentLocations, we get the expected result back
  }

  @Test
  public void getOpenAdjacentLocations_whenGivenCornerRoom_shouldReturnOneRoomAndTwoHallwaysAtMost() {
    // Create Board
    // Using a "corner" piece, create three scenarios by deliberately moving pieces beforehand
    //   1. All adjacent hallway spaces available (2)
    //   2. Only one adjacent hallway spaces available
    //   3. No adjacent hallway spaces available
    // For each of these, assert that when we call getOpenAdjacentLocations, we get the expected result back
  }

  @Test
  public void movePiece_shouldMoveThePieceFromTheOldLocationToTheNewOne() {
    // Create Board
    // call movePiece with valid Piece and Location
    // expect that the old location that piece was in no longer has that piece
    // and expect the new location has that piece in it
  }

  @Test
  public void movePiece_whenGivenInvalidPiece_shouldThrowAnError() {
    // Create Board
    // Give a piece that's invalid, expect error
  }

  @Test
  public void movePiece_whenGivenInvalidLocation_shouldThrowAnError() {
    // Create Board
    // Give a location that's invalid, expect error
  }

  @Test
  public void moveWeapon_shouldMoveTheWeaponFromTheOldLocationToTheNewOne() {
    // Create Board
    // call moveWeapon with valid Weapon and Location
    // expect that the old location that weapon was in no longer has that weapon
    // and expect the new location has that weapon in it
  }

  @Test
  public void moveWeapon_whenGivenInvalidWeapon_shouldThrowAnError() {
    // Create Board, call moveWeapon with invalid weapon, should throw error
  }

  @Test
  public void moveWeapon_whenGivenInvalidLocation_shouldThrowAnError() {
    // Create Board, call moveWeapon with invalid location, should throw error
  }

  @Test
  public void getAssociatedCard_whenGivenWeapon_shouldReturnTheWeaponCard() {
    // Create Board
    // call getAssociatedCard, expect to get back the weapon card associated with the weapon
  }

  @Test
  public void getAssociatedCard_whenGivenPiece_shouldReturnThePieceCard() {
    // Create Board
    // call getAssociatedCard, expect to get back the piece card associated with that piece
  }

  @Test
  public void getAssociatedCard_whenGivenLocation_shouldRturnTheCorrectRoomCard() {
    // Create Board
    // call getAssociatedCard, expect to get back the room card associated with that location/room
  }
}
