import React, {Fragment} from 'react';
import PropTypes from 'prop-types';
import RadioButton from './RadioButton';

const RoleSelect = ({roles, value, onChange}) => (
  <Fragment>
    {roles.map(role => <RadioButton
      label={role.name}
      value={role.id}
      key={role.id}
      checked={role.id === +value}
      onChange={onChange}
    />)}
  </Fragment>
);

RoleSelect.propTypes = {
  roles: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.number,
      name: PropTypes.string
    })
  ),
  value: PropTypes.string,
  onChange: PropTypes.func
};

RoleSelect.defaultProps = {
  roles: [],
  value: '',
  onChange: () => {}
};

export default RoleSelect;