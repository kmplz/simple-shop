import React from 'react';
import PropTypes from 'prop-types';
import {AuthMode} from "../../utils/const";

const ModeSelect = ({activeMode, onChange}) => {
  const inactiveMode = activeMode === AuthMode.SIGN_IN ? AuthMode.SIGN_UP : AuthMode.SIGN_IN;
  return (
    <div className="px-4 py-3 bg-dark text-white font-weight-bold">
      <span>{activeMode}</span> / <span className="auth-mode" onClick={() => onChange(inactiveMode)}>{inactiveMode}</span>
    </div>
  );
};

ModeSelect.propTypes = {
  activeMode: PropTypes.oneOf([AuthMode.SIGN_UP, AuthMode.SIGN_IN]),
  onChange: PropTypes.func
};

ModeSelect.defaultProps = {
  onChange: () => {}
};

export default ModeSelect;