import React from 'react';
import Link from './navbar/Link';
import Dropdown from './navbar/Dropdown';
import {CATEGORIES} from "../utils/const";

export default () => (
  <nav className="navbar navbar-expand-lg navbar-dark py-3 sticky-top" style={{backgroundColor: "#424348" }}>
    <div className="container">
      <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
              aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"/>
      </button>
      <div className="collapse navbar-collapse" id="navbarNavDropdown">
        <ul className="navbar-nav mr-auto">
          <Link to="/">Home</Link>
          <Dropdown title="Shop" links={CATEGORIES} />
          <Link to="/orders">Orders</Link>
        </ul>

        <div className="text-right align-text-bottom">
          <span className="text-white">
            <strong>$94.00</strong>
            <span className="text-muted mx-3">4 items</span>
            <span className="fa fa-shopping-cart"/>
          </span>
        </div>
      </div>
    </div>
  </nav>
);