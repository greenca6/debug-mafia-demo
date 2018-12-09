import React from 'react';
import PropTypes from 'prop-types';
import { Container } from 'reactstrap';
import { GameContainer } from '../container';

export const GamePage = ({ location }) => {
  const { game, player } = location.state;

  return (
    <Container fluid className="pb-3">
      <GameContainer game={game} player={player} />
    </Container>
  );
}

GamePage.propTypes = {
  location: PropTypes.shape({
    state: PropTypes.shape({
      player: PropTypes.shape({
        uuid: PropTypes.string.isRequired,
      }).isRequired,
      game: PropTypes.object.isRequired,
    }).isRequired,
  }).isRequired,
};

export default GamePage;
