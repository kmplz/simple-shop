import React from 'react';

export default ({children}) => (
  <button
    type="button"
    className="btn btn-dark w-100 rounded-0 font-weight-bold mt-3"
    style={{backgroundColor: "#424348"}}
  >
    {children}
  </button>
);