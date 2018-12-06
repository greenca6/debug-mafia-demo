import React from 'react';
import PropTypes from 'prop-types';
import { Badge, ListGroup, ListGroupItem } from 'reactstrap';

// shows list of joined players
// props: list of joinedPlayers
export const PlayerList = ({ players }) => {
  return (
    <div className="border rounded p-3">
      <h5 className="text-uppercase">Joined Players</h5>
      {
        players.length > 0 ?
          <ListGroup>
            {players.map(({ uuid, username, piece }) => (
              <ListGroupItem key={uuid}>
                <span className="font-weight-semibold text-muted">{username}</span>
                <Badge className="text-capitalize ml-2">{piece.name}</Badge>
              </ListGroupItem>
            ))}
          </ListGroup> :
          <span className="text-muted font-italic">Waiting for players to join...</span>
      }
    </div>
  );
};

PlayerList.propTypes = {
  players: PropTypes.arrayOf(PropTypes.shape({
    uuid: PropTypes.string.isRequired,
    username: PropTypes.string.isRequired,
    piece: PropTypes.shape({
      name: PropTypes.string.isRequired,
    }).isRequired,
  })).isRequired,
};

export default PlayerList;
