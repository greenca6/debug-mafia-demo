import React, { Component } from 'react';
import SockJS from 'sockjs-client';
import * as Stomp from 'stomp-websocket';
import axios from 'axios';
import './App.css';

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
    const socket = new SockJS(`${process.env.REACT_APP_API}/gs-guide-websocket`);
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
    stompClient.send('/app/hello', {}, JSON.stringify({ name: this.state.name }));
  };

  render() {
    const { apiResponse, connected, name, serverMessage } = this.state;

    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">Hello world!</h1>
        </header>
        <main>
          <button onClick={this.handleClick}>Fetch Me Data!</button>
          The server returned: {JSON.stringify(apiResponse) || ''}

          <button disabled={connected} onClick={this.handleConnectClick}>Connect!</button>
          <button disabled={!connected} onClick={this.handleDisconnectClick}>Disconnect!</button>
          <input value={name} type="text" placeholder="Brian" onChange={({ target }) => this.setState({ name: target.value })} />

          <button onClick={this.handleSendName}>Send!</button>
          The server says: {serverMessage}
        </main>
      </div>
    );
  }
}


export default App;
