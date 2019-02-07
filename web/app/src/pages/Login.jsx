import React, {Component} from 'react';
import FormInput from './login/FormInput';
import {AuthMode} from '../utils/const';
import ErrorLabel from '../components/ErrorLabel';
import SubmitButton from './login/SubmitButton';
import ModeSelect from './login/ModeSelect';
import {connect} from 'react-redux';
import {signIn, signUp} from "../modules/auth/actions";

class Login extends Component {
  state = {
    mode: AuthMode.SIGN_IN,
    login: '',
    password: ''
  };

  switchMode = mode => {
    this.setState({mode});
  };

  onLoginChange = login => {
    this.setState({login})
  };

  onPasswordChange = password => {
    this.setState({password});
  };

  onSubmit = () => {
    const {mode, login, password} = this.state;
    if (mode === AuthMode.SIGN_IN) {
      this.props.signIn(login, password);
    } else {
      this.props.signUp(login, password);
    }
  };

  render() {
    const {mode} = this.state;
    const {error, loading} = this.props;
    return (
      <div className="h-100 d-flex align-items-center justify-content-center">
        <form className="login-form needs-validation column shadow-lg w-100 mx-2">
          <ModeSelect activeMode={mode} onChange={this.switchMode} />
          <div className="p-4">
            <FormInput
              id="inputLogin"
              label="Login"
              onChange={this.onLoginChange}
            />
            <FormInput
              id="inputPassword"
              label="Password"
              secured={true}
              onChange={this.onPasswordChange}
            />
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

const mapStateToProps = ({auth: {error, loading}}) => ({
  error,
  loading
});

const mapDispatchToProps = dispatch => ({
  signIn: (login, password) => dispatch(signIn(login, password)),
  signUp: (username, login, password, roleId) => dispatch(signUp(username, login, password, roleId))
});

export default connect(mapStateToProps, mapDispatchToProps)(Login);