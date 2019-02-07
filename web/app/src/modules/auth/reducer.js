import {LOCAL_STORAGE_TOKEN_KEY} from "../../utils/const";
import {SIGN_IN_FAILURE, SIGN_IN_REQUEST, SIGN_IN_SUCCESS} from "./constants";

const initialState = {
  isAuthenticated: null,
  error: '',
  token: localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY),
  loading: false
};

export default (state = initialState, action) => {
  switch (action.type) {
    case SIGN_IN_REQUEST:
      return {
        ...initialState,
        loading: true
      };
    case SIGN_IN_SUCCESS:
      return {
        ...initialState,
        isAuthenticated: true,
        token: action.payload
      };
    case SIGN_IN_FAILURE:
      return {
        ...state,
        loading: false,
        isAuthenticated: false,
        error: action.errorMsg
      };
    default:
      return state;
  }
};