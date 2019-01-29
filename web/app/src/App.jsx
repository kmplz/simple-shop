import React, { Component, Fragment } from 'react';
import Navbar from './components/Navbar';
import Products from './pages/Products';

class App extends Component {
  render() {
    return (
        <Fragment>

          <Navbar />
          <Products />

        </Fragment>
    );
  }
}

export default App;
