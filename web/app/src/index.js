import React from 'react';
import ReactDOM from 'react-dom';
import App from './App.jsx';
import * as serviceWorker from './serviceWorker';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import 'font-awesome/css/font-awesome.min.css';
import './index.css';
import $ from 'jquery'; // eslint-disable-line no-unused-vars
import Popper from 'popper.js'; // eslint-disable-line no-unused-vars

ReactDOM.render(<App />, document.getElementById('root'));

serviceWorker.unregister();
