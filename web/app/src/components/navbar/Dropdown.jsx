import React from 'react';
import DropdownButton from './DropdownButton';
import {Link} from 'react-router-dom';

export default ({title, links}) => (
  <li className="nav-item dropdown">
    <DropdownButton>{title}</DropdownButton>
    <div className="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
      {links.map(item => <Link
        key={item.id}
        className="dropdown-item"
        to={`/products/category/${item.id}`}
      >
        {item.name}
      </Link>)}
    </div>
  </li>
);