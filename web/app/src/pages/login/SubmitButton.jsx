import React from 'react';
import PropTypes from 'prop-types';

const SubmitButton = ({onSubmit, children}) => (
  <div className="form-group row">
    <div className="col-12">
      <button onClick={onSubmit} type="submit" className="btn btn-dark w-100 rounded-0">
        {children}
      </button>
    </div>
  </div>
);

SubmitButton.propTypes = {
  onSubmit: PropTypes.func,
  children: PropTypes.array
};

SubmitButton.defaultProps = {
  onSubmit: () => {},
  children: []
};

export default SubmitButton;