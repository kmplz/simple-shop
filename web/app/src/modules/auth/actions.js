import {RSAA} from 'redux-api-middleware';
import {
  LOAD_ROLES_FAILURE,
  LOAD_ROLES_REQUEST,
  LOAD_ROLES_SUCCESS,
  AUTH_FAILURE,
  AUTH_REQUEST,
  AUTH_SUCCESS, ME_REQUEST, ME_SUCCESS, ME_FAILURE
} from './constants';

export const signIn = (login, password) => ({
  [RSAA]: {
    endpoint: '/api/auth/sign-in',
    method: 'POST',
    body: JSON.stringify({login, password}),
    types: [
      AUTH_REQUEST,
      AUTH_SUCCESS,
      AUTH_FAILURE
    ]
  }
});

export const signUp = (username, login, password, roleId) => ({
  [RSAA]: {
    endpoint: '/api/auth/sign-up',
    method: 'POST',
    body: JSON.stringify({login, password, username, roleId}),
    types: [
      AUTH_REQUEST,
      AUTH_SUCCESS,
      AUTH_FAILURE
    ]
  }
});

export const me = () => ({
  [RSAA]: {
    endpoint: '/api/auth/me',
    method: 'GET',
    types: [
      ME_REQUEST,
      ME_SUCCESS,
      ME_FAILURE
    ]
  }
});

export const loadRoles = () => ({
  [RSAA]: {
    endpoint: '/api/auth/roles',
    method: 'GET',
    types: [
      LOAD_ROLES_REQUEST,
      LOAD_ROLES_SUCCESS,
      LOAD_ROLES_FAILURE
    ]
  }
});