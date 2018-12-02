import { get } from 'axios';

const STATUS_ENDPOINT = '/api/status';

export class StatusService {
  getStatus() {
    // When the API is ready we can use line 8. For now we mock the response.
    // return get(STATUS_ENDPOINT);
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve({ data: { gameInProgress: true  } });
      }, 1000);
    });
  }
}

export default StatusService;
