import React from 'react';
import PropTypes from 'prop-types';
import { Button, Row, Col, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import {
  AccuseDialog,
  ActionPanel,
  Board,
  CharacterPortrait,
  RebuttalDialog,
  SuggestDialog,
  withGameService
} from '../component';
import { GameService } from '../service';


export class GameContainer extends React.Component {
  state = {
    game: this.props.game,
    selectedLocation: null,
    showSuggestionDialog: false,
    showAccusationDialog: false,
    showRebuttalConfirmationDialog: false,
  };

  _tick(game, showRebuttalConfirmationDialog = false) {
    this.setState({
      game,
      selectedLocation: null,
      showSuggestionDialog: false,
      showAccusationDialog: false,
      showRebuttalConfirmationDialog,
    });
  }

  componentDidMount() {
    // TODO: add service hooks for move, accusation, suggestion, rebuttal, endGame
    const { gameService } = this.props;

    gameService.onAccusation((game) => this._tick(game));
    gameService.onEndGame(() => window.location = '/');
    gameService.onEndTurn((game) => this._tick(game));
    gameService.onMove((game) => this._tick(game));
    gameService.onRebuttal((game) => this._tick(game, true));
    gameService.onSuggestion((game) => this._tick(game));
  }

  handleAccusationClick = () => {
    this.setState({ showAccusationDialog: !this.state.showAccusationDialog });
  };

  handleAccuse = (piece, room, weapon) => {
    this.props.gameService.makeAccusation({ player: this.props.player, piece, room, weapon });
  };

  handleBackToLobbyClick = () => {
    this.props.gameService.endGame();
  };

  handleEndTurn = () => {
    this.props.gameService.endTurn(this.props.player);
  };

  handleMove = () => {
    const { player } = this.props;
    const { selectedLocation } = this.state;

    this.props.gameService.makeMove({ player, piece: player.piece, to: selectedLocation });
  };

  handleRebuttal = (card) => {
    this.props.gameService.makeRebuttal({ player: this.props.player, card });
  };

  handleSelectLocation = (selectedLocation) => {
    this.setState({ selectedLocation });
  };

  handleSuggest = (piece, weapon) => {
    const { board, currentPlayersTurn } = this.state.game;
    const { player } = this.props;
    const isCurrentTurn = player.uuid === currentPlayersTurn.player.uuid;
    const playerLocation = isCurrentTurn && board.grid.find(l => l.pieces.find(p => player.piece.uuid === p.uuid));

    this.props.gameService.makeSuggestion({ player, piece, room: playerLocation, weapon });
  };

  handleSuggestionClick = () => {
    this.setState({ showSuggestionDialog: !this.state.showSuggestionDialog });
  };

  renderDialogs() {
    const { game, showAccusationDialog, showSuggestionDialog, showRebuttalConfirmationDialog } = this.state;
    const { board, currentPlayersTurn, gameState, winner } = game;
    const { player } = this.props;
    const isCurrentTurn = player.uuid === currentPlayersTurn.player.uuid;
    const hasWinner = gameState === 'COMPLETE' && winner;
    const rebuttalRequested = currentPlayersTurn.state === 'WAITING_FOR_REBUTTAL' && currentPlayersTurn.requestRebuttalFrom.uuid === player.uuid;
    const playerLocation = isCurrentTurn && game.board.grid.find(l => l.pieces.find(p => player.piece.uuid === p.uuid));
    let canSuggest = false;

    console.log('rebuttal requested: ' + rebuttalRequested);
    console.log(currentPlayersTurn);

    if (playerLocation && playerLocation.type === 'ROOM') {
      canSuggest = true;
    }

    // Immediately show the winning dialog if there was a winner
    if (hasWinner) {
      const playerIsWinner = winner.uuid === player.uuid;
      const { piece, room, weapon } = currentPlayersTurn.accusation;

      return (
        <Modal isOpen className="text-capitalize">
          <ModalHeader>
            {
              playerIsWinner ?
                <span className="text-success">You win!</span> :
                `${winner.username} has won!`
            }
          </ModalHeader>
          <ModalBody>
            {
              playerIsWinner ?
                <React.Fragment>
                  <strong className="text-success">Congrats!</strong>&nbsp;
                  You won with the following accusation:
                </React.Fragment> :
                `${winner.username} won with the following accusation:`
            }
            <Row>
              <Col>
                <strong>{piece.name}</strong> did it, in the <strong>{room.name}</strong>, with the <strong>{weapon.name}!</strong>
              </Col>
            </Row>
          </ModalBody>
          <ModalFooter>
            <Button color="primary" onClick={this.handleBackToLobbyClick}>Back to Lobby</Button>
          </ModalFooter>
        </Modal>
      );
    }

    // Immediately show the rebuttal dialog if a rebuttal is requested of our player
    if (rebuttalRequested) {
      console.log('rebuttal requested');
      return (
        <RebuttalDialog
          show
          cards={currentPlayersTurn.rebuttalCardsToChooseFrom}
          onRebuttal={this.handleRebuttal}
        />
      );
    }

    // Show the rebuttal confirmation dialog when another player rebutts
    if (isCurrentTurn && showRebuttalConfirmationDialog) {
      const { card, player } = currentPlayersTurn.rebuttal;

      return (
        <Modal isOpen className="text-capitalize">
          <ModalHeader>Rebuttal from {player.username}</ModalHeader>
          <ModalBody>
            {player.username} has shown you their <strong>{card.type}</strong> card: {card.name}
          </ModalBody>
          <ModalFooter>
            <Button color="primary" onClick={() => this.setState({ showRebuttalConfirmationDialog: false })}>Okay</Button>
          </ModalFooter>
        </Modal>
      );
    }

    // Render accusation and suggest dialogs
    return (
      <React.Fragment>
        <AccuseDialog
          show={showAccusationDialog}
          pieces={board.pieces}
          weapons={board.weapons}
          rooms={board.grid.filter(l => l.type === 'ROOM')}
          cards={board.cards}
          onAccuse={this.handleAccuse}
          onClose={() => this.setState({ showAccusationDialog: false })}
        />
        {
          canSuggest ?
            <SuggestDialog
              show={showSuggestionDialog}
              pieces={board.pieces}
              weapons={board.weapons}
              room={playerLocation}
              onSuggest={this.handleSuggest}
              onClose={() => this.setState({ showSuggestionDialog: false })}
            /> :
            null
        }
      </React.Fragment>
    );
  }

  render() {
    const { game, selectedLocation } = this.state;
    const { currentPlayersTurn, players, board } = game;
    const playerId = this.props.player.uuid;
    const player = players.find(p => p.uuid === playerId);
    const isCurrentTurn = playerId === currentPlayersTurn.player.uuid;
    const canMove = isCurrentTurn && currentPlayersTurn.availableActions.includes('MOVE');
    const playerLocation = isCurrentTurn && game.board.grid.find(l => l.pieces.find(p => player.piece.uuid === p.uuid));
    let canSuggest = false;

    if (playerLocation && playerLocation.type === 'ROOM') {
      canSuggest = true;
    }

    return (
      <React.Fragment>
        <Row>
          <Col className="text-center">
            <h2 className={`text-capitalize ${isCurrentTurn ? 'text-info' : 'text-muted'}`}>
              {
                isCurrentTurn ?
                  `It's your turn!` :
                  `${currentPlayersTurn.player.username}'s turn (${currentPlayersTurn.player.piece.name})`
              }
            </h2>
          </Col>
        </Row>
        <Row noGutters>
          <Col xs="2" className="border bg-white p-2">
            Action History
          </Col>
          <Col xs="10" className="border bg-light p-3">
            <Board
              canMove={canMove}
              locations={board.grid}
              availableLocations={currentPlayersTurn.availableLocations}
              selectedLocation={selectedLocation}
              onSelectLocation={this.handleSelectLocation}
              isCurrentTurn={isCurrentTurn}
            />
          </Col>
        </Row>
        <Row noGutters>
          <Col xs="2" className="border p-2">
            <CharacterPortrait username={player.username} piece={player.piece} />
          </Col>
          <Col xs="10" className="border p-2">
            <ActionPanel
              availableActions={currentPlayersTurn.availableActions}
              canMove={canMove && !!selectedLocation}
              canSuggest={canSuggest}
              cards={player.cards}
              isCurrentTurn={isCurrentTurn}
              onAccuseClick={this.handleAccusationClick}
              onEndTurnClick={this.handleEndTurn}
              onMoveClick={this.handleMove}
              onSuggestClick={this.handleSuggestionClick}
            />
          </Col>
        </Row>
        {this.renderDialogs()}
        <Row>
          <Col>
            <Button outline color="danger" onClick={this.handleBackToLobbyClick} className="mt-3">
              Force Exit Game
            </Button>
          </Col>
        </Row>
      </React.Fragment>
    );
  }
};

GameContainer.propTypes = {
  player: PropTypes.shape({

  }).isRequired,
  game: PropTypes.shape({

  }),
  gameService: PropTypes.instanceOf(GameService),
};

export default withGameService(GameContainer);
