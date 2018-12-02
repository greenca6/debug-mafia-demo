import GameService from './gameService';
import LobbyService from './lobbyService';
import SocketClient from './socketClient';
import StatusService from './statusService';

let gameService = null;
let lobbyService = null;
let socketClient = null;
let statusService = null;

export class ServiceFactory {
  static createGameService() {
    if (!gameService) {
      gameService = new GameService(ServiceFactory.createSocketClient());
    }

    return gameService;
  }

  static createLobbyService() {
    if (!lobbyService) {
      lobbyService = new LobbyService(ServiceFactory.createSocketClient());
    }

    return lobbyService;
  }

  static createSocketClient() {
    if (!socketClient) {
      socketClient = new SocketClient();
    }

    return socketClient;
  }

  static createStatusService() {
    if (!statusService) {
      statusService = new StatusService();
    }

    return statusService;
  }
}

export default ServiceFactory;
