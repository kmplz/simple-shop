import rootReducer from './rootReducer';
import {apiMiddleware} from 'redux-api-middleware';
import {compose, createStore, applyMiddleware} from 'redux';
import thunk from 'redux-thunk';
import apiHeadersInjector from './apiHeadersInjector';
import apiResponseHandler from './apiResponseHandler';

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

export default createStore(
  rootReducer,
  composeEnhancers(
    applyMiddleware(
      apiHeadersInjector,
      apiMiddleware,
      apiResponseHandler,
      thunk
    )
  )
);