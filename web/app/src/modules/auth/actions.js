import {RSAA} from 'redux-api-middleware';
import {SIGN_IN_FAILURE, SIGN_IN_REQUEST, SIGN_IN_SUCCESS} from './constants';

export const signIn = (login, password) => ({
  [RSAA]: {
    endpoint: '/api/auth/sign-in',
    method: 'POST',
    body: JSON.stringify({login, password}),
    types: [
      SIGN_IN_REQUEST,
      SIGN_IN_SUCCESS,
      SIGN_IN_FAILURE
    ]
  }
});

export const signUp = (username, login, password, roleId) => ({
  [RSAA]: {
    endpoint: '/api/auth/sign-up',
    method: 'POST',
    body: JSON.stringify({login, password, username, roleId}),
    types: [
      SIGN_IN_REQUEST,
      SIGN_IN_SUCCESS,
      SIGN_IN_FAILURE
    ]
  }
});