const GAME_SOCKET_ENDPOINT = '/game';

/**
 * The GameService is the primary component that handles the server<->client communication.
 *
 * It allows for:
 *    1. Callers to subscribe to user actions (sent from the server after an action has been
 *       committed).
 *    2. Callers to send actions to the server for processing and updating the Game (there is no
 *       response from this message, only a send).
 */
export class GameService {
  constructor(socketClient) {
    this.socketClient = socketClient;
  }

  endGame() {
    this.socketClient.send(`${GAME_SOCKET_ENDPOINT}/endGame`, {});
  }

  endTurn(player) {
    this.socketClient.send(`${GAME_SOCKET_ENDPOINT}/endTurn`, JSON.stringify(player));
  }

  onAccusation(callback) {
    this.socketClient.subscribe(`${GAME_SOCKET_ENDPOINT}/onAccusation`, ({ body }) => {
      const updatedGameInstance = JSON.parse(body);
      callback(updatedGameInstance);
    });
  }

  onEndGame(callback) {
    this.socketClient.subscribe(`${GAME_SOCKET_ENDPOINT}/onEndGame`, ({ body }) => {
      callback();
    });
  }

  onEndTurn(callback) {
    this.socketClient.subscribe(`${GAME_SOCKET_ENDPOINT}/onEndTurn`, ({ body }) => {
      const updatedGameInstance = JSON.parse(body);
      callback(updatedGameInstance);
    });
  }

  onMove(callback) {
    this.socketClient.subscribe(`${GAME_SOCKET_ENDPOINT}/onMove`, ({ body }) => {
      const updatedGameInstance = JSON.parse(body);
      callback(updatedGameInstance);
    });
  }

  onRebuttal(callback) {
    this.socketClient.subscribe(`${GAME_SOCKET_ENDPOINT}/onRebuttal`, ({ body }) => {
      const updatedGameInstance = JSON.parse(body);
      callback(updatedGameInstance);
    });
  }

  onSuggestion(callback) {
    this.socketClient.subscribe(`${GAME_SOCKET_ENDPOINT}/onSuggestion`, ({ body }) => {
      const updatedGameInstance = JSON.parse(body);
      callback(updatedGameInstance);
    });
  }

  /**
   * Sends the accusation instance to the server for it to process and update the Game.
   *
   * @param {Accusation} accusation
   */
  makeAccusation(accusation) {
    this.socketClient.send(`${GAME_SOCKET_ENDPOINT}/accusation`, JSON.stringify(accusation));
  }

  /**
   * Sends the move instance to the server for it to process and update the Game.
   *
   * @param {Move} move
   */
  makeMove(move) {
    this.socketClient.send(`${GAME_SOCKET_ENDPOINT}/move`, JSON.stringify(move));
  }

  /**
   * Sends the rebuttal instance to the server for it to process and update the Game.
   *
   * @param {Rebuttal} rebuttal
   */
  makeRebuttal(rebuttal) {
    this.socketClient.send(`${GAME_SOCKET_ENDPOINT}/rebuttal`, JSON.stringify(rebuttal));
  }

  /**
   * Sends the suggestion instance to the server for it to process and update the Game.
   *
   * @param {Suggestion} suggestion
   */
  makeSuggestion(suggestion) {
    this.socketClient.send(`${GAME_SOCKET_ENDPOINT}/suggestion`, JSON.stringify(suggestion));
  }
}

export default GameService;
