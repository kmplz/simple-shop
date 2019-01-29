import React from 'react';

export default ({children}) => (
  <span
    className="nav-link dropdown-toggle font-weight-bold"
    id="navbarDropdownMenuLink"
    role="button"
    data-toggle="dropdown"
    aria-haspopup="true"
    aria-expanded="false"
  >
    {children}
  </span>
);