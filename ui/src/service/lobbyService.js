import { get } from 'axios';

const LOBBY_API_ENDPIONT = `${process.env.REACT_APP_SERVER}/api/lobby`;
const LOBBY_SOCKET_ENDPOINT = '/lobby';

export class LobbyService {
  constructor(socketClient) {
    this.socketClient = socketClient;
  }

  getLobby() {
    return get(LOBBY_API_ENDPIONT);
  }

  onPlayerJoin(callback) {
    this.socketClient.subscribe(`${LOBBY_SOCKET_ENDPOINT}/onJoin`, ({ body }) => {
      const updatedLobbyInstance = JSON.parse(body);
      callback(updatedLobbyInstance);
    });
  }

  onStartGame(callback) {
    this.socketClient.subscribe(`${LOBBY_SOCKET_ENDPOINT}/onStart`, ({ body }) => {
      const newGame = JSON.parse(body);
      callback(newGame);
    });
  }

  joinLobby(player) {
    this.socketClient.send(`${LOBBY_SOCKET_ENDPOINT}/join`, JSON.stringify(player));
  }

  startGame() {
    this.socketClient.send(`${LOBBY_SOCKET_ENDPOINT}/start`, {});
  }
}

export default LobbyService;
