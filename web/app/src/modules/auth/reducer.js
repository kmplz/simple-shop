import {LOCAL_STORAGE_TOKEN_KEY} from '../../utils/const';
import {
  LOAD_ROLES_SUCCESS,
  AUTH_FAILURE,
  AUTH_REQUEST,
  AUTH_SUCCESS,
  ME_REQUEST,
  ME_SUCCESS,
  ME_FAILURE
} from './constants';

const initialState = {
  isAuthenticated: null,
  error: '',
  token: localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY),
  loading: false,
  roles: [],
  user: null,
  loadingUser: true
};

export default (state = initialState, action) => {
  switch (action.type) {
    case AUTH_REQUEST:
      return {
        ...state,
        isAuthenticated: null,
        error: '',
        loading: true
      };
    case AUTH_SUCCESS:
      localStorage.setItem(LOCAL_STORAGE_TOKEN_KEY, action.payload);
      return {
        ...state,
        error: '',
        loading: false,
        isAuthenticated: true,
        token: action.payload
      };
    case AUTH_FAILURE:
      return {
        ...state,
        loading: false,
        isAuthenticated: false,
        error: action.errorMsg
      };
    case LOAD_ROLES_SUCCESS:
      return {
        ...state,
        roles: action.payload
      };
    case ME_REQUEST:
      return {
        ...state,
        loadingUser: true
      };
    case ME_SUCCESS:
      return {
        ...state,
        isAuthenticated: true,
        user: action.payload,
        loadingUser: false
      };
    case ME_FAILURE:
      return {
        ...state,
        loadingUser: false,
        isAuthenticated: false
      };
    default:
      return state;
  }
};