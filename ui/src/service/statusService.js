import { get } from 'axios';

const STATUS_ENDPOINT = `${process.env.REACT_APP_SERVER}/api/status`;

export class StatusService {
  getStatus() {
    return get(STATUS_ENDPOINT);
  }
}

export default StatusService;
