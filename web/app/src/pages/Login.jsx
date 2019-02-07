import React, {Component, Fragment} from 'react';
import FormInput from './login/FormInput';
import {AuthMode} from '../utils/const';
import ErrorLabel from '../components/ErrorLabel';
import SubmitButton from './login/SubmitButton';
import ModeSelect from './login/ModeSelect';
import {connect} from 'react-redux';
import {loadRoles, signIn, signUp} from '../modules/auth/actions';
import RoleSelect from './login/RoleSelect';
import {Redirect} from 'react-router-dom';

class Login extends Component {
  state = {
    mode: AuthMode.SIGN_IN,
    login: '',
    password: '',
    username: '',
    roleId: null
  };

  componentWillMount() {
    this.props.loadRoles();
  }

  switchMode = mode => {
    this.setState({mode});
  };

  onFieldChange = (field, event) => {
    const newState = {...this.state};
    newState[field] = event.target.value;
    this.setState(newState);
  };

  onSubmit = () => {
    const {mode, login, password, username, roleId} = this.state;
    if (mode === AuthMode.SIGN_IN) {
      this.props.signIn(login, password);
    } else {
      this.props.signUp(login, password, username, roleId);
    }
  };

  render() {
    const {mode, roleId, login, password, username} = this.state;
    const {error, loading, roles, isAuthenticated} = this.props;

    if (isAuthenticated) {
      return <Redirect to="/" />
    }

    return (
      <div className="h-100 d-flex align-items-center justify-content-center">
        <form className="login-form needs-validation column shadow-lg w-100 mx-2">
          <ModeSelect activeMode={mode} onChange={this.switchMode} />
          <div className="p-4">
            <FormInput
              value={login}
              onChange={event => this.onFieldChange('login', event)}
              id="inputLogin"
              label="Login"
            />
            <FormInput
              value={password}
              onChange={event => this.onFieldChange('password', event)}
              secured={true}
              id="inputPassword"
              label="Password"
            />
            {mode === AuthMode.SIGN_UP &&
              <Fragment>
                <FormInput
                  value={username}
                  onChange={event => this.onFieldChange('username', event)}
                  id="inputUsername"
                  label="Username"
                />
                <RoleSelect
                  roles={roles}
                  value={roleId}
                  onChange={event => this.onFieldChange('roleId', event)}
                />
              </Fragment>
            }
            <ErrorLabel message={error} />
            <SubmitButton onSubmit={this.onSubmit} loading={loading}>
              {mode}
            </SubmitButton>
          </div>
        </form>
      </div>
    );
  }
}

const mapStateToProps = ({auth: {error, loading, roles, isAuthenticated}}) => ({
  error,
  loading,
  roles,
  isAuthenticated
});

const mapDispatchToProps = dispatch => ({
  signIn: (login, password) => dispatch(signIn(login, password)),
  signUp: (username, login, password, roleId) => dispatch(signUp(username, login, password, roleId)),
  loadRoles: () => dispatch(loadRoles())
});

export default connect(mapStateToProps, mapDispatchToProps)(Login);