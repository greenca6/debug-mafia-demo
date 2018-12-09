import React from 'react';
import PropTypes from 'prop-types';
import {
  Button,
  CardDeck,
  Card,
  CardTitle,
  CardSubtitle,
  Col,
  Row,
} from 'reactstrap';

import './_actionPanel.css';

// allows user to make actions, and shows their cards
// props: cards, availableMoves, onSuggestClick, onMoveClick, onAccuseClick, isCurrentTurn

export const ActionPanel = ({
  availableActions,
  cards,
  canMove,
  canSuggest,
  isCurrentTurn,
  onAccuseClick,
  onEndTurnClick,
  onMoveClick,
  onSuggestClick,
}) => {
  return (
    <Row>
      <Col xs="6">
        <h3>Your Hand</h3>
        <Col>
          <CardDeck className="card-slider">
            {cards.map((card) => (
              <Card body key={card.uuid} className="text-capitalize">
                <CardTitle>{card.name}</CardTitle>
                <CardSubtitle className="text-muted">{card.type.toLowerCase()}</CardSubtitle>
              </Card>
            ))}
          </CardDeck>
        </Col>
      </Col>
      <Col xs="6">
        <h3>Actions</h3>
        {
          isCurrentTurn ?
            <React.Fragment>
              <h5>I want to...</h5>
              <Button
                color="primary"
                disabled={!canMove}
                onClick={onMoveClick}
              >
                Move
              </Button>{' '}
              <Button
                color="primary"
                disabled={!availableActions.includes('SUGGEST') || !canSuggest}
                onClick={onSuggestClick}
              >
                Make a Suggestion
              </Button>{' '}
              <Button
                color="primary"
                disabled={!availableActions.includes('ACCUSE')}
                onClick={onAccuseClick}
              >
                Make an Accusation
              </Button>{' '}
              <Button outline color="primary" onClick={onEndTurnClick}>End Turn</Button>
            </React.Fragment> :
            <h5 className="font-italic text-muted">Waiting for your turn...</h5>
        }
      </Col>
    </Row>
  );
};

ActionPanel.propTypes = {
  availableActions: PropTypes.arrayOf(PropTypes.string).isRequired,
  canMove: PropTypes.bool.isRequired,
  cards: PropTypes.arrayOf(PropTypes.shape({
    uuid: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    type: PropTypes.string.isRequired,
  })).isRequired,
  isCurrentTurn: PropTypes.bool.isRequired,
  onAccuseClick: PropTypes.func.isRequired,
  onMoveClick: PropTypes.func.isRequired,
  onSuggestClick: PropTypes.func.isRequired,
};

export default ActionPanel;
