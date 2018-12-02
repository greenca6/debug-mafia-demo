const GAME_SOCKET_ENDPIONT = '/game/actions';

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

  /**
   * Subscribes to any incoming user actions that have been committed. This includes moves,
   * accusations, suggestions, and rebuttals.
   *
   * @param {Function} callback
   */
  onPlayerAction(callback) {
    this.socketClient.subscribe(GAME_SOCKET_ENDPIONT, ({ body }) => {
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
    this.socketClient.send(`${GAME_SOCKET_ENDPIONT}/accusation`, {}, JSON.stringify(accusation));
  }

  /**
   * Sends the move instance to the server for it to process and update the Game.
   *
   * @param {Move} move
   */
  makeMove(move) {
    this.socketClient.send(`${GAME_SOCKET_ENDPIONT}/move`, {}, JSON.stringify(move));
  }

  /**
   * Sends the rebuttal instance to the server for it to process and update the Game.
   *
   * @param {Rebuttal} rebuttal
   */
  makeRebuttal(rebuttal) {
    this.socketClient.send(`${GAME_SOCKET_ENDPIONT}/rebuttal`, {}, JSON.stringify(rebuttal));
  }

  /**
   * Sends the suggestion instance to the server for it to process and update the Game.
   *
   * @param {Suggestion} suggestion
   */
  makeSuggestion(suggestion) {
    this.socketClient.send(`${GAME_SOCKET_ENDPIONT}/suggestion`, {}, JSON.stringify(suggestion));
  }
}

export default GameService;
