import React, { Component } from 'react';
import SockJS from 'sockjs-client';
import * as Stomp from 'stomp-websocket';
import axios from 'axios';
import './App.css';

var stompClient = null;
var serverMessage = null;

class App extends Component {
  state = {
    response: null,
  };

  handleClick = () => {
    axios.get(`${process.env.REACT_APP_API}/api/test`)
      .then(({ data }) => {
        this.setState({ response: data });
      })
      .catch(err => console.log(err));
  };

  handleConnectClick = () => {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        
        document.getElementById("Connect").disabled = true;
        document.getElementById("Disconnect").disabled = false;
        //console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            serverMessage = JSON.parse(greeting.body).content;
        });
    });
  };

  handleDisconnectClick = () => {
    if (stompClient !== null) {
      stompClient.disconnect();
  }
  document.getElementById("Connect").disabled = true;
  document.getElementById("Disconnect").disabled = false;
  console.log("Disconnected");
  };

  handleSendName = () => {
    stompClient.send("/app/hello", {}, JSON.stringify(document.getElementById('Name').value));
  };

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">Hello world!</h1>
        </header>
        <main>
          <button onClick={this.handleClick}>Fetch Me Data!</button>
          The server returned: {JSON.stringify(this.state.response) || ''}

          <button id="Connect" onClick={this.handleConnectClick}>Connect!</button>
          <button id="Disconnect" disabled="diabled" onClick={this.handleDisconnectClick}>Disconnect!</button>
          <input id="Name" type ="text" placeholder = "Brian" />

          <button onClick={this.handleSendName}>Send!</button>
          The server says: {this.serverMessage}

        </main>
      </div>
    );
  }
}


export default App;
