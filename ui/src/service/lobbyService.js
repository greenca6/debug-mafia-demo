import { get } from 'axios';

const LOBBY_API_ENDPIONT = '/api/lobby';
const LOBBY_SOCKET_ENDPOINT = '/lobby/join';

export class LobbyService {
  constructor(socketClient) {
    this.socketClient = socketClient;
  }

  getLobby() {
    // Mocking this request for now until the API is ready
    // return get(LOBBY_API_ENDPIONT);
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve({
          data: {
            canStartGame: false,
            connectedPlayers: [],
            availablePieces: [],
          },
        });
      }, 1000);
    });
  }

  onPlayerJoin(callback) {
    this.socketClient.subscrib(LOBBY_SOCKET_ENDPOINT, ({ body }) => {
      const updatedLobbyInstance = JSON.parse(body);
      callback(updatedLobbyInstance);
    });
  }

  joinLobby(player) {
    this.socketClient.send(LOBBY_SOCKET_ENDPOINT, {}, JSON.stringify(player));
  }
}

export default LobbyService;
