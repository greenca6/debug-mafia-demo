import React from 'react';
import { Container, Jumbotron } from 'reactstrap';

export const GameInProgressPage = () => (
  <Container fluid className="pt-3">
    <Jumbotron>
      <h1 className="display-3">Game Currently In Progress</h1>
      <p className="lead">Please come back later to join a game.</p>
    </Jumbotron>
  </Container>
);

export default GameInProgressPage;
