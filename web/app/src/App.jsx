import React, {Fragment} from 'react';
import Navbar from './components/Navbar';
import Products from './pages/Products';
import ProductDetails from './pages/ProductDetails';
import Home from './pages/Home';
import Orders from './pages/Orders';
import {Switch, Route} from 'react-router-dom';

export default () => (
  <Fragment>
    <Navbar />
    <main className="container mt-4">
      <Switch>
        <Route exact path="/products/category/:id" component={Products} />
        <Route exact path="/product/:id" component={ProductDetails} />
        <Route exact path="/orders" component={Orders} />
        <Route exact path="/" component={Home} />
      </Switch>
    </main>
  </Fragment>
);
