import React, {Fragment} from 'react';
import PropTypes from 'prop-types';

const ErrorLabel = ({message}) => (
  <Fragment>
    {message ?
      <div className="alert alert-danger rounded-0" role="alert">
        {message}
      </div>
    :
    <div>&nbsp;</div>
    }
  </Fragment>
);

ErrorLabel.propTypes = {
  message: PropTypes.string
};

ErrorLabel.defaultProps = {
  message: ''
};

export default ErrorLabel;