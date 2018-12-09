import React from 'react';
import PropTypes from 'prop-types';
import { Badge, Card, CardTitle, Button, Row, Col } from 'reactstrap';

// list of available cards
// props: array of cards, 1 selected card, onSelectCard
export const AvailablePieces = ({ onSelectPiece, pieces, selectedPiece }) => {
  if (pieces.length === 0) {
    return <span className="font-italic text-muted">No Pieces Available...</span>;
  }

  return (
    <Row>
      {pieces.map((piece) => {
        const isSelected = selectedPiece && selectedPiece.uuid === piece.uuid;
        const props = isSelected ? { outline: true, color: 'primary' } : { };

        return (
          <Col md="4" key={piece.uuid}>
            <Card body {...props}>
              <CardTitle className="text-capitalize">
                {piece.name}
                {isSelected ? <Badge className="ml-2" color="primary" pill>Selected</Badge> : null}
              </CardTitle>
              <Button
                disabled={isSelected}
                color="primary"
                outline
                onClick={() => onSelectPiece(piece)}
              >
                Select
              </Button>
            </Card>
          </Col>
        );
      })}
    </Row>
  );
};

AvailablePieces.propTypes = {
  onSelectPiece: PropTypes.func.isRequired,
  pieces: PropTypes.arrayOf(PropTypes.shape({
    name: PropTypes.string.isRequired,
    uuid: PropTypes.string.isRequired,
  })).isRequired,
  selectedPiece: PropTypes.shape({
    name: PropTypes.string.isRequired,
    uuid: PropTypes.string.isRequired,
  }),
};

export default AvailablePieces;
