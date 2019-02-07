import React from 'react';
import PropTypes from 'prop-types';

const RadioButton = ({label, value, checked, onChange}) => (
  <div className="form-check form-check-inline">
    <input
      className="form-check-input"
      type="radio"
      name="inlineRadioOptions"
      id={label}
      value={value}
      checked={checked}
      onChange={onChange}
    />
    <label className="form-check-label" htmlFor={label}>{label}</label>
  </div>
);

RadioButton.propTypes = {
  label: PropTypes.string,
  checked: PropTypes.bool,
  value: PropTypes.number,
  onChange: PropTypes.func,
};

RadioButton.defaultProps = {
  label: '',
  checked: false,
  value: '',
  onChange: () => {}
};

export default RadioButton;