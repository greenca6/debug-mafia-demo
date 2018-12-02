import React from 'react';
import { ServiceFactory } from '../../service';


export const withLobbyService = WrappedComponent => (
  class extends React.Component {
    render() {
      return (
        <WrappedComponent
          {...this.props}
          lobbyService={ServiceFactory.createLobbyService()}
        />
      );
    }
  }
);

export default withLobbyService;
