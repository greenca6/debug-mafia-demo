import React from 'react';
import { Badge, Row, Col } from 'reactstrap';

import './_board.css';

const Location = ({
  location,
  availableLocations,
  canMove,
  onSelect,
  isCurrentTurn,
  selectedLocation,
}) => {
  const { type, uuid, name, weapons, pieces } = location;
  const isAvailableLocation = availableLocations.map(l => l.uuid).includes(uuid);
  const highlighted = isCurrentTurn && canMove && isAvailableLocation;
  const selected = selectedLocation && selectedLocation.uuid === uuid;
  const onClick = highlighted ? () => onSelect(location) : null;
  let className = `location location--${type.toLowerCase()} p-1`;

  if (highlighted) {
    className = `${className} location--highlighted`;
  }

  if (selected) {
    className = `${className} location--selected`;
  }

  return (
    <div className={className} onClick={onClick}>
      <h6>{type === 'ROOM' ? name : ''}</h6>
      <Row>
        <Col>
          {pieces.map(piece => (
            <Badge key={piece.uuid} color="primary">{piece.name}</Badge>
          ))}
        </Col>
      </Row>
      <Row>
        <Col>
          {weapons.map(weapon => (
            <Badge key={weapon.uuid} color="info">{weapon.name}</Badge>
          ))}
        </Col>
      </Row>
    </div>
  );
};

export const Board = ({
  canMove,
  locations,
  availableLocations,
  selectedLocation,
  onSelectLocation,
  isCurrentTurn,
}) => {
  const props = { canMove, availableLocations, onSelect: onSelectLocation, selectedLocation, isCurrentTurn };

  return (
    <div className="text-capitalize">
      <table className="m-auto shadow">
        <tbody>
          <tr>
            <td><Location location={locations[0]} {...props} /></td>
            <td><Location location={locations[1]} {...props} /></td>
            <td><Location location={locations[2]} {...props} /></td>
            <td><Location location={locations[3]} {...props} /></td>
            <td><Location location={locations[4]} {...props} /></td>
          </tr>
          <tr>
            <td><Location location={locations[5]} {...props} /></td>
            <td><div className="location location--gap" /></td>
            <td><Location location={locations[6]} {...props} /></td>
            <td><div className="location location--gap" /></td>
            <td><Location location={locations[7]} {...props} /></td>
          </tr>
          <tr>
            <td><Location location={locations[8]} {...props} /></td>
            <td><Location location={locations[9]} {...props} /></td>
            <td><Location location={locations[10]} {...props} /></td>
            <td><Location location={locations[11]} {...props} /></td>
            <td><Location location={locations[12]} {...props} /></td>
          </tr>
          <tr>
            <td><Location location={locations[13]} {...props} /></td>
            <td><div className="location location--gap" /></td>
            <td><Location location={locations[14]} {...props} /></td>
            <td><div className="location location--gap" /></td>
            <td><Location location={locations[15]} {...props} /></td>
          </tr>
          <tr>
            <td><Location location={locations[16]} {...props} /></td>
            <td><Location location={locations[17]} {...props} /></td>
            <td><Location location={locations[18]} {...props} /></td>
            <td><Location location={locations[19]} {...props} /></td>
            <td><Location location={locations[20]} {...props} /></td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default Board;
