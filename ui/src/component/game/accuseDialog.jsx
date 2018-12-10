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


export class AccuseDialog extends React.Component {
  state = {
    selectedPiece: null,
    selectedRoom: null,
    selectedWeapon: null,
  };

  render() {
    const { selectedPiece, selectedRoom, selectedWeapon } = this.state;
    const { show, pieces, weapons, rooms, onAccuse, onClose } = this.props;

    return (
      <Modal isOpen={show} toggle={onClose} size="lg">
        <ModalHeader toggle={onClose}>Make An Accusation...</ModalHeader>
        <ModalBody>
          <p className="lead">
            Be aware: if you make an <strong>incorrect</strong> accusation, you will no longer be able to move or make suggestions!
          </p>
          <h6>I accuse...</h6>
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
          <h6>In the...</h6>
          <ButtonGroup>
            {rooms.map(room => (
              <Button
                key={room.uuid}
                className="text-capitalize"
                active={selectedRoom && selectedRoom.uuid === room.uuid}
                onClick={() => this.setState({ selectedRoom: room })}
              >
                {room.name}
              </Button>
            ))}
          </ButtonGroup>
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
            disabled={!selectedPiece || !selectedRoom || !selectedWeapon}
            onClick={() => onAccuse(selectedPiece, selectedRoom, selectedWeapon)}
          >
            Make Accusation
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

AccuseDialog.propTypes = {
  show: PropTypes.bool.isRequired,
  pieces: PropTypes.arrayOf(PropTypes.shape({
    name: PropTypes.string.isRequired,
    uuid: PropTypes.string.isRequired,
  })).isRequired,
  weapons: PropTypes.arrayOf(PropTypes.shape({
    name: PropTypes.string.isRequired,
    uuid: PropTypes.string.isRequired,
  })).isRequired,
  rooms: PropTypes.arrayOf(PropTypes.shape({
    name: PropTypes.string.isRequired,
    uuid: PropTypes.string.isRequired,
  })).isRequired,
  onAccuse: PropTypes.func.isRequired,
  onClose: PropTypes.func.isRequired,
};

export default AccuseDialog;
