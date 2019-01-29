import React from 'react';

export default () => (
    <nav className="navbar navbar-expand-lg navbar-dark py-3 sticky-top" style={{backgroundColor: "#424348" }}>
        <div className="container">
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNavDropdown">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item active">
                        <a className="nav-link font-weight-bold" href="#">Home<span className="sr-only">(current)</span></a>
                    </li>
                    <li className="nav-item dropdown">
                        <a className="nav-link dropdown-toggle font-weight-bold" href="#" id="navbarDropdownMenuLink" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Shop
                        </a>
                        <div className="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a className="dropdown-item" href="#">Mobile phones</a>
                            <a className="dropdown-item" href="#">Laptops</a>
                        </div>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link font-weight-bold" href="#">Orders<span className="sr-only">(current)</span></a>
                    </li>
                </ul>

                <div className="text-right align-text-bottom">
                  <span className="text-white">
                    <strong>$94.00</strong>
                    <span className="text-muted mx-3">4 items</span>
                    <span className="fa fa-shopping-cart"></span>
                  </span>
                </div>
            </div>
        </div>
    </nav>
);