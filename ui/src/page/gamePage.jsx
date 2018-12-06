import React from 'react';
import { Container } from 'reactstrap';

export const GamePage = ({ location }) => {
  console.log(location);
  return (
    <Container fluid className="pt-3">
      <h1>Game page yo</h1>
    </Container>
  );
}

export default GamePage;
