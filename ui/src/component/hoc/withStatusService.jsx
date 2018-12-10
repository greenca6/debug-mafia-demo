import React from 'react';
import { ServiceFactory } from '../../service';


export const withStatusService = WrappedComponent => (
  class extends React.Component {
    render() {
      return (
        <WrappedComponent
          {...this.props}
          statusService={ServiceFactory.createStatusService()}
        />
      );
    }
  }
);

export default withStatusService;
