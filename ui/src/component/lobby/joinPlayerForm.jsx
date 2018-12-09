import React from 'react';
import PropTypes from 'prop-types';
import { Button, Row, Col, FormGroup, Label, Input } from 'reactstrap';
import { AvailablePieces } from './availablePieces';

// includes the available cards
// props: available cards, 1 selected card, onSubmit, username
export const JoinPlayerForm = ({
  availablePieces,
  onJoinClick,
  onSelectPiece,
  onUpdateUsername,
  selectedPiece,
  username,
}) => {
  return (
    <React.Fragment>
      <h2 className="font-weight-normal">Join Game</h2>
      <h3 className="text-muted font-weight-light">Select Piece</h3>
      <div className="p-3 mb-3 border rounded">
        <AvailablePieces
          pieces={availablePieces}
          selectedPiece={selectedPiece}
          onSelectPiece={onSelectPiece}
        />
      </div>
      <h3 className="text-muted font-weight-light">Player Info</h3>
      <Row>
        <Col md="3">
          <FormGroup>
            <Label for="username">Username</Label>
            <Input
              name="username"
              id="username"
              placeholder="Enter your username..."
              onChange={({ target }) => onUpdateUsername(target.value)}
              value={username}
            />
          </FormGroup>
        </Col>
      </Row>
      <Button
        outline
        color="primary"
        onClick={onJoinClick}
        disabled={!username || !selectedPiece}
      >
        Join Game
      </Button>
    </React.Fragment>
  );
};

JoinPlayerForm.propTypes = {
  availablePieces: PropTypes.arrayOf(PropTypes.shape({
    name: PropTypes.string.isRequired,
    uuid: PropTypes.string.isRequired,
  })).isRequired,
  onJoinClick: PropTypes.func.isRequired,
  onSelectPiece: PropTypes.func.isRequired,
  onUpdateUsername: PropTypes.func.isRequired,
  selectedPiece: PropTypes.shape({
    name: PropTypes.string.isRequired,
    uuid: PropTypes.string.isRequired,
  }),
  username: PropTypes.string,
};

export default JoinPlayerForm;
