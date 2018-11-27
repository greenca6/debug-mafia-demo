package com.debugmafia.clueless.model;

import java.util.ArrayList;
import java.util.List;

public class BoardLocationFactory {
  public static List<BoardLocation> createGrid() {
    List<BoardLocation> grid = new ArrayList<>();

    // Nine rooms
    BoardLocation study = new BoardLocation(BoardLocationType.ROOM, "study");
    BoardLocation hall = new BoardLocation(BoardLocationType.ROOM, "hall");
    BoardLocation lounge = new BoardLocation(BoardLocationType.ROOM, "lounge");
    BoardLocation library = new BoardLocation(BoardLocationType.ROOM, "library");
    BoardLocation billiardRoom = new BoardLocation(BoardLocationType.ROOM, "billiard room");
    BoardLocation diningRoom = new BoardLocation(BoardLocationType.ROOM, "dining room");
    BoardLocation conservatory = new BoardLocation(BoardLocationType.ROOM, "conservatory");
    BoardLocation ballRoom = new BoardLocation(BoardLocationType.ROOM, "ball room");
    BoardLocation kitchen = new BoardLocation(BoardLocationType.ROOM, "kitchen");

    // Secret passages
    study.setSecretPassage(kitchen);
    kitchen.setSecretPassage(study);
    lounge.setSecretPassage(conservatory);
    conservatory.setSecretPassage(lounge);

    // Ten hallways
    BoardLocation studyToHall = new BoardLocation(BoardLocationType.HALLWAY, "study to hall");
    BoardLocation hallToLounge = new BoardLocation(BoardLocationType.HALLWAY, "hall to lounge");
    BoardLocation studyToLibrary = new BoardLocation(BoardLocationType.HALLWAY, "study to library");
    BoardLocation hallToBilliardRoom = new BoardLocation(BoardLocationType.HALLWAY, "hall to billiard room");
    BoardLocation loungeToDiningRoom = new BoardLocation(BoardLocationType.HALLWAY, "lounge to dining room");
    BoardLocation libraryToBilliardRoom = new BoardLocation(BoardLocationType.HALLWAY, "library to billiard room");
    BoardLocation billiardRoomToDiningRoom = new BoardLocation(BoardLocationType.HALLWAY, "billiard room to dining room");
    BoardLocation libraryToConservatory = new BoardLocation(BoardLocationType.HALLWAY, "library to conservatory");
    BoardLocation billiardRoomToBallRoom = new BoardLocation(BoardLocationType.HALLWAY, "billiard room to ball room");
    BoardLocation diningRoomToKitchen = new BoardLocation(BoardLocationType.HALLWAY, "dining room to kitchen");
    BoardLocation conservatoryToBallRoom = new BoardLocation(BoardLocationType.HALLWAY, "conservatory to ball room");
    BoardLocation ballRoomToKitchen = new BoardLocation(BoardLocationType.HALLWAY, "ball room to kitchen");

    /* Connect Rooms and Hallways */
    // Study
    study.addAdjacentLocation(studyToHall);
    study.addAdjacentLocation(studyToLibrary);
    studyToHall.addAdjacentLocation(study);
    studyToLibrary.addAdjacentLocation(study);

    // Hall
    hall.addAdjacentLocation(studyToHall);
    hall.addAdjacentLocation(hallToLounge);
    hall.addAdjacentLocation(hallToBilliardRoom);
    studyToHall.addAdjacentLocation(hall);
    hallToLounge.addAdjacentLocation(hall);
    hallToBilliardRoom.addAdjacentLocation(hall);

    // Lounge
    lounge.addAdjacentLocation(hallToLounge);
    lounge.addAdjacentLocation(loungeToDiningRoom);
    hallToLounge.addAdjacentLocation(lounge);
    loungeToDiningRoom.addAdjacentLocation(lounge);

    // Library
    library.addAdjacentLocation(studyToLibrary);
    library.addAdjacentLocation(libraryToBilliardRoom);
    library.addAdjacentLocation(libraryToConservatory);
    studyToLibrary.addAdjacentLocation(library);
    libraryToBilliardRoom.addAdjacentLocation(library);
    libraryToConservatory.addAdjacentLocation(library);

    // Billiard Room
    billiardRoom.addAdjacentLocation(hallToBilliardRoom);
    billiardRoom.addAdjacentLocation(libraryToBilliardRoom);
    billiardRoom.addAdjacentLocation(billiardRoomToBallRoom);
    billiardRoom.addAdjacentLocation(billiardRoomToDiningRoom);
    hallToBilliardRoom.addAdjacentLocation(billiardRoom);
    libraryToBilliardRoom.addAdjacentLocation(billiardRoom);
    billiardRoomToBallRoom.addAdjacentLocation(billiardRoom);
    billiardRoomToDiningRoom.addAdjacentLocation(billiardRoom);

    // Dining Room
    diningRoom.addAdjacentLocation(loungeToDiningRoom);
    diningRoom.addAdjacentLocation(billiardRoomToDiningRoom);
    diningRoom.addAdjacentLocation(diningRoomToKitchen);
    loungeToDiningRoom.addAdjacentLocation(diningRoom);
    billiardRoomToDiningRoom.addAdjacentLocation(diningRoom);
    diningRoomToKitchen.addAdjacentLocation(diningRoom);

    // Conservatory
    conservatory.addAdjacentLocation(libraryToConservatory);
    conservatory.addAdjacentLocation(conservatoryToBallRoom);
    libraryToConservatory.addAdjacentLocation(conservatory);
    conservatoryToBallRoom.addAdjacentLocation(conservatory);

    // Ball Room
    ballRoom.addAdjacentLocation(billiardRoomToBallRoom);
    ballRoom.addAdjacentLocation(conservatoryToBallRoom);
    ballRoom.addAdjacentLocation(ballRoomToKitchen);
    billiardRoomToBallRoom.addAdjacentLocation(ballRoom);
    conservatoryToBallRoom.addAdjacentLocation(ballRoom);
    ballRoomToKitchen.addAdjacentLocation(ballRoom);

    // Kitchen
    kitchen.addAdjacentLocation(diningRoomToKitchen);
    kitchen.addAdjacentLocation(ballRoomToKitchen);
    diningRoomToKitchen.addAdjacentLocation(kitchen);
    ballRoomToKitchen.addAdjacentLocation(kitchen);

    // Add rooms to the grid
    grid.add(study);
    grid.add(hall);
    grid.add(lounge);
    grid.add(library);
    grid.add(billiardRoom);
    grid.add(diningRoom);
    grid.add(conservatory);
    grid.add(ballRoom);
    grid.add(kitchen);
    // Add hallways to the grid
    grid.add(studyToHall);
    grid.add(hallToLounge);
    grid.add(studyToLibrary);
    grid.add(hallToBilliardRoom);
    grid.add(loungeToDiningRoom);
    grid.add(libraryToBilliardRoom);
    grid.add(billiardRoomToDiningRoom);
    grid.add(libraryToConservatory);
    grid.add(billiardRoomToBallRoom);
    grid.add(diningRoomToKitchen);
    grid.add(conservatoryToBallRoom);
    grid.add(ballRoomToKitchen);

    return grid;
  }
}
