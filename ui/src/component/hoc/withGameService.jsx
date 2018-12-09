import React from 'react';
import { ServiceFactory } from '../../service';


export const withGameService = WrappedComponent => (
  class extends React.Component {
    render() {
      return (
        <WrappedComponent
          {...this.props}
          gameService={ServiceFactory.createGameService()}
        />
      );
    }
  }
);

export default withGameService;
