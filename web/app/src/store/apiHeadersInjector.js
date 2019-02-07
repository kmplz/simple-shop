import {RSAA} from "redux-api-middleware";

export default store => next => action => {
  const callApi = action[RSAA];
  if (callApi) {
    callApi.headers = state => {
      const token = state.auth.token;
      return {
        ...callApi.headers,
        'Content-Type': 'application/json',
        'Authorization': token ? `Bearer ${token}` : undefined
      }
    };
  }
  return next(action);
}