import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { HashRouter as Router, Switch, Route, Redirect } from 'react-router-dom';
import { Alert, Col, Container, Row, Progress } from 'reactstrap';
import { withStatusService } from './component';
import { StatusService } from './service';
import { GameInProgressPage, GamePage, LobbyPage } from './page';


class App extends Component {
  state = {
    busy: true,
    error: null,
  };
  gameInProgress = true;

  componentDidMount() {
    this.props.statusService.getStatus()
      .then(({ data }) => {
        this.gameInProgress = data.gameInProgress;
        this.setState({ busy: false });
      })
      .catch((error) => {
        console.log(error);
        this.setState({ busy: false, error });
      });
  }

  renderBusy() {
    return (
      <Container>
        <Row className="mt-5">
          <Col>
            <h1>Please wait...</h1>
            <Progress animated value={100} />
          </Col>
        </Row>
      </Container>
    );
  }

  renderError() {
    return (
      <Container>
        <Alert color="danger">Error!</Alert>
      </Container>
    );
  }

  render() {
    const { busy, error } = this.state;

    if (busy) {
      return this.renderBusy();
    }

    if (error) {
      return this.renderError();
    }

    return (
      <Router>
        <Switch>
          <Route path="/" exact render={() => {
            if (this.gameInProgress) {
              return <GameInProgressPage />
            }

            return <LobbyPage />;
          }} />
          <Route path="/game" component={GamePage} />
        </Switch>
      </Router>
    );
  }
}

App.propTypes = {
  statusService: PropTypes.instanceOf(StatusService),
};

export default withStatusService(App);
