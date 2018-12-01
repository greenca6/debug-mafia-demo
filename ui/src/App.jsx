import React, { Component } from 'react';
import SockJS from 'sockjs-client';
import * as Stomp from 'stomp-websocket';
import axios from 'axios';

let stompClient = null;

class App extends Component {
  state = {
    apiResponse: null,
    serverMessage: null,
    connected: false,
    name: '',
  };

  handleClick = () => {
    axios.get(`${process.env.REACT_APP_API}/api/test`)
      .then(({ data }) => {
        this.setState({ apiResponse: data });
      })
      .catch(err => console.log(err));
  };

  handleConnectClick = () => {
    const socket = new SockJS(`${process.env.REACT_APP_API}/clueless`);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
      this.setState({ connected: true });
      stompClient.subscribe('/topic/greetings', (greeting) => {
        console.log(greeting);
        this.setState({ serverMessage: JSON.parse(greeting.body).content });
      });
    });
  };

  handleDisconnectClick = () => {
    if (stompClient !== null) {
      stompClient.disconnect();
      this.setState({ connected: false });
    }
  };

  handleSendName = () => {
    stompClient.send('/socket/hello', {}, JSON.stringify({ name: this.state.name }));
  };

  render() {
    const { apiResponse, connected, name, serverMessage } = this.state;

    return (
      <div className="container">
        <main className="text-center">
          <h1>Hello world!</h1>
          <div className="row mb-3">
            <div className="col">
              <button
                type="button"
                className="btn btn-primary mr-2"
                onClick={this.handleClick}
              >
                Fetch Me Data!
              </button>
              The server returned: {JSON.stringify(apiResponse) || ''}
            </div>
          </div>
          <div className="row">
            <div className="col">
              <button
                disabled={connected}
                className="btn btn-primary mr-2"
                onClick={this.handleConnectClick}
              >
                Connect!
              </button>
              <button
                disabled={!connected}
                className="btn btn-outline-primary mr-2"
                onClick={this.handleDisconnectClick}
              >
                Disconnect!
              </button>
            </div>
            <div className={`col${!connected ? ' d-none' : ''}`}>
              <div className="input-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Message"
                  aria-label="Message To Send"
                  aria-describedby="button-addon2"
                  onChange={({ target }) => this.setState({ name: target.value })}
                />
                <div className="input-group-append">
                  <button
                    className="btn btn-outline-primary"
                    type="button"
                    id="button-addon2"
                    onClick={this.handleSendName}
                  >
                    Send!
                  </button>
                </div>
              </div>
              The server says: {serverMessage}
            </div>
          </div>
        </main>
      </div>
    );
  }
}


export default App;
