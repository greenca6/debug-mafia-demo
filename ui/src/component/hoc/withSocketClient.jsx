import React from 'react';
import { ServiceFactory } from '../../service';


export const withSocketClient = WrappedComponent => (
  class extends React.Component {
    render() {
      return (
        <WrappedComponent
          {...this.props}
          socketClient={ServiceFactory.createSocketClient()}
        />
      );
    }
  }
);

export default withSocketClient;
