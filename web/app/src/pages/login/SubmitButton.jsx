import React from 'react';
import PropTypes from 'prop-types';

const SubmitButton = ({onSubmit, children, loading}) => (
  <div className="form-group row">
    <div className="col-12">
      <button
        onClick={e => {
          e.preventDefault();
          onSubmit();
        }}
        disabled={loading}
        type="submit"
        className="btn btn-dark w-100 rounded-0"
      >
        {loading ? '...' : children}
      </button>
    </div>
  </div>
);

SubmitButton.propTypes = {
  onSubmit: PropTypes.func,
  children: PropTypes.string,
  loading: PropTypes.bool
};

SubmitButton.defaultProps = {
  onSubmit: () => {},
  children: '',
  loading: false
};

export default SubmitButton;