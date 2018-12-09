import React from 'react';
import { Container, Row, Col } from 'reactstrap';
import { LobbyContainer } from '../container';

export const LobbyPage = () => (
  <Container fluid className="pt-3">
    <Row className="mb-3">
      <Col>
        <h1 className="text-center font-weight-normal">Game Lobby</h1>
      </Col>
    </Row>
    <LobbyContainer />
  </Container>
);

export default LobbyPage;
