import React from 'react';
import PropTypes from 'prop-types';
import {
  Button,
  ButtonGroup,
  Modal,
  ModalHeader,
  ModalBody,
  ModalFooter,
} from 'reactstrap';


export class SuggestDialog extends React.Component {
  state = {
    selectedPiece: null,
    selectedWeapon: null,
  };

  render() {
    const { selectedPiece, selectedWeapon } = this.state;
    const { show, pieces, weapons, room, onSuggest, onClose } = this.props;

    return (
      <Modal isOpen={show} toggle={onClose} size="lg">
        <ModalHeader toggle={onClose}>Make A Suggestion...</ModalHeader>
        <ModalBody>
          <h6>I suggest...</h6>
          <ButtonGroup>
            {pieces.map(piece => (
              <Button
                key={piece.uuid}
                className="text-capitalize"
                active={selectedPiece && selectedPiece.uuid === piece.uuid}
                onClick={() => this.setState({ selectedPiece: piece })}
              >
                {piece.name}
              </Button>
            ))}
          </ButtonGroup>
          <h6 className="text-capitalize">In the...{room.name.toLowerCase()}</h6>
          <h6>With the...</h6>
          <ButtonGroup>
            {weapons.map(weapon => (
              <Button
                key={weapon.uuid}
                className="text-capitalize"
                active={selectedWeapon && selectedWeapon.uuid === weapon.uuid}
                onClick={() => this.setState({ selectedWeapon: weapon })}
              >
                {weapon.name}
              </Button>
            ))}
          </ButtonGroup>
        </ModalBody>
        <ModalFooter>
          <Button outline color="primary" onClick={onClose}>Cancel</Button>
          <Button
            color="danger"
            disabled={!selectedPiece || !selectedWeapon}
            onClick={() => onSuggest(selectedPiece, selectedWeapon)}
          >
            Make Suggestion
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

SuggestDialog.propTypes = {
  show: PropTypes.bool.isRequired,
  pieces: PropTypes.arrayOf(PropTypes.shape({
    name: PropTypes.string.isRequired,
    uuid: PropTypes.string.isRequired,
  })).isRequired,
  weapons: PropTypes.arrayOf(PropTypes.shape({
    name: PropTypes.string.isRequired,
    uuid: PropTypes.string.isRequired,
  })).isRequired,
  room: PropTypes.shape({
    name: PropTypes.string.isRequired,
  }).isRequired,
  onSuggest: PropTypes.func.isRequired,
  onClose: PropTypes.func.isRequired,
};

export default SuggestDialog;
