import React from 'react';
import PropTypes from 'prop-types';

export const CharacterPortrait = ({
  piece,
  username,
}) => (
  <div>
    {username} - {piece.name}
  </div>
);

CharacterData.propTypes = {
  piece: PropTypes.shape({ name: PropTypes.string.isRequired }).isRequired,
  username: PropTypes.string.isRequired,
};

export default CharacterPortrait;
