import React from 'react';

export default ({label, secured, value, onChange, id}) => (
  <div className="form-group row">
    <label htmlFor={id} className="col-sm-2 col-form-label">{label}</label>
    <div className="col-sm-10">
      <input
        id={id}
        className="form-control rounded-0"
        type={secured ? 'password' : 'text'}
        value={value}
        onChange={onChange}
        required
      />
    </div>
  </div>
);