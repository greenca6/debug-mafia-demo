import React from 'react';
import { Jumbotron } from 'reactstrap';

export const GameInProgressPage = () => (
  <div className="container">
    <Jumbotron>
      <h1 className="display-3">Game Currently In Progress</h1>
      <p className="lead">Please come back later to join a game.</p>
    </Jumbotron>
  </div>
);

export default GameInProgressPage;
