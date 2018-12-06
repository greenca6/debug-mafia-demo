import SockJS from 'sockjs-client';
import * as Stomp from 'stomp-websocket';

const SOCKET_ENDPOINT = `${process.env.REACT_APP_SERVER}/clueless`;

/**
 * Connects to the server's socket for live client<->server communication.
 *
 * Uses the Stomp lib's core methods for connecting, subscribing to responses, sending messages,
 * and disconnecting.
 *
 */
export class SocketClient {
  constructor() {
    const socket = new SockJS(SOCKET_ENDPOINT);
    const client = Stomp.over(socket);

    // Pass through methods
    this.connect = (cb) => client.connect({}, () => cb());
    this.subscribe = (location, cb) => client.subscribe(location, cb);
    this.send = (location, body) => client.send(location, {}, body);
    this.disconnect = client.disconnect;
  }
}

export default SocketClient;
