import React, { Component } from 'react';
import axios from 'axios';
import './App.css';

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

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">Hello world!</h1>
        </header>
        <main>
          <button onClick={this.handleClick}>Fetch Me Data!</button>
          The server returned: {JSON.stringify(this.state.response) || ''}
        </main>
      </div>
    );
  }
}

export default App;
