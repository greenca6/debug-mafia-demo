import React from 'react';
import { withRouter } from 'react-router';
import PropTypes from 'prop-types';
import { Button, Col, Row } from 'reactstrap';
import { PlayerList, JoinPlayerForm, withLobbyService, withSocketClient } from '../component';
import { LobbyService, SocketClient } from '../service';


export class LobbyContainer extends React.Component {
  // TODO: Experiment with different values here for local dev
  state = {
    lobby: {
      canStartGame: false,
      connectedPlayers: [],
      availablePieces: [],
    },
    player: {
      piece: null,
      username: '',
    },
  };

  componentDidMount() {
    const { lobbyService, socketClient, history } = this.props;

    // Make the initial api request, loading the lobby as it currently is
    lobbyService.getLobby()
      .then(({ data }) => {
        this.setState({ lobby: data });
      })
      .catch((error) => console.error(error));

    // Connect to lobby socket endpoint
    socketClient.connect(() => {
      console.log('Connected to ClueLess Server');
      // Listen for player joins that the server tells us about
      lobbyService.onPlayerJoin((lobby) => {
        const { player } = this.state;
        const pieces = lobby.connectedPlayers.map(p => p.piece.uuid);

        if (player && player.piece && pieces.includes(player.piece.uuid)) {
          this.setState({ lobby, player: null })
        } else {
          this.setState({ lobby });
        }
      });

      // Listen for the game to start that the server tells us about, route to the main game page
      // when that happens, passing the newly created game object along with it
      lobbyService.onStartGame((game) => {
        history.push({ pathname: '/game', state: { game } });
      });
    });
  }

  handleJoin = () => {
    this.props.lobbyService.joinLobby(this.state.player);
  };

  handleSelectPiece = (piece) => {
    this.setState({ player: { ...this.state.player, piece } });
  };

  handleStartGame = () => {
    this.props.lobbyService.startGame();
  };

  handleUpdateUsername = (username) => {
    this.setState({ player: { ...this.state.player, username: username.trim() } });
  };

  render() {
    const { player, lobby } = this.state;

    return (
      <React.Fragment>
        <Row>
          <Col md="4">
            <PlayerList players={lobby.connectedPlayers} />
          </Col>
          <Col md="8">
            {
              player ?
                <JoinPlayerForm
                  player={player}
                  selectedPiece={player.piece}
                  availablePieces={lobby.availablePieces}
                  onSelectPiece={this.handleSelectPiece}
                  onJoinClick={this.handleJoin}
                  onUpdateUsername={this.handleUpdateUsername}
                  username={player.username}
                /> :
                <h2>You&apos;ve joined the game.</h2>
            }
          </Col>
        </Row>
        <Row className="justify-content-end border-top pt-3 mt-3">
          <Col md="2">
            <Button
              block
              color="primary"
              disabled={!lobby.canStartGame}
              onClick={this.handleStartGame}
            >
              Start Game
            </Button>
          </Col>
        </Row>
      </React.Fragment>
    );
  }
};

LobbyContainer.propTypes = {
  lobbyService: PropTypes.instanceOf(LobbyService).isRequired,
  socketClient: PropTypes.instanceOf(SocketClient).isRequired,
  history: PropTypes.shape({
    push: PropTypes.func.isRequired,
  }).isRequired,
};

export default withLobbyService(withSocketClient(withRouter(LobbyContainer)));
