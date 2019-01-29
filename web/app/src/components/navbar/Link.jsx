import React from 'react';
import {NavLink} from 'react-router-dom';

export default ({to, children}) => (
  <li className="nav-item">
    <NavLink to={to} exact className="nav-link font-weight-bold" activeClassName="text-white">
      {children}
    </NavLink>
  </li>
);