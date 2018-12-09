import React from 'react';
import PropTypes from 'prop-types';
import {
  Button,
  CardDeck,
  Card,
  CardTitle,
  CardSubtitle,
  Modal,
  ModalHeader,
  ModalBody,
  ModalFooter,
} from 'reactstrap';

export class RebuttalDialog extends React.Component {
  state = {
    selectedCard: null,
  };

  render() {
    const { selectedCard } = this.state;
    const { show, cards, onRebuttal } = this.props;

    return (
      <Modal isOpen={show} size="lg">
        <ModalHeader>Make Your Rebuttal...</ModalHeader>
        <ModalBody>
          <p className="lead">
            You have a card that was a part of the Suggestion! Select a card to use to disprove the players suggestion!
          </p>
          <CardDeck>
            {cards.map(card => {
              const isSelected = selectedCard && selectedCard.uuid === card.uuid;
              const props = isSelected ? { outline: true, color: 'primary' } : { };

              return (
                <Card {...props}>
                  <CardTitle>{card.name}</CardTitle>
                  <CardSubtitle className="text-capitalize">{card.type}</CardSubtitle>
                  <Button
                    disabled={isSelected}
                    color="primary"
                    outlined
                    onClick={() => this.setState({ selectedCard: card })}
                  >
                    Select
                  </Button>
                </Card>
              )})}
          </CardDeck>
        </ModalBody>
        <ModalFooter>
          <Button
            color="primary"
            disabled={!selectedCard}
            onClick={() => onRebuttal(selectedCard)}
          >
            Make Rebuttal
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

RebuttalDialog.propTypes = {
  show: PropTypes.bool.isRequired,
  cards: PropTypes.arrayOf(PropTypes.shape({
    type: PropTypes.oneOf(['ROOM', 'PIECE', 'WEAPON']).isRequired,
    name: PropTypes.string.isRequired,
    uuid: PropTypes.string.isRequired,
  })).isRequired,
  onRebuttal: PropTypes.func.isRequired,
};

export default RebuttalDialog;
