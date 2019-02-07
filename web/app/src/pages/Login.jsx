import React, {Component} from 'react';
import FormInput from './login/FormInput';
import {AuthMode} from '../utils/const';
import ErrorLabel from '../components/ErrorLabel';
import SubmitButton from './login/SubmitButton';
import ModeSelect from './login/ModeSelect';
import {connect} from 'react-redux';

class Login extends Component {
  state = {
    mode: AuthMode.SIGN_IN,
    error: ''
  };

  switchMode = mode => {
    this.setState({mode});
  };

  render() {
    const {mode, error} = this.state;
    return (
      <div className="h-100 d-flex align-items-center justify-content-center">
        <form className="login-form needs-validation column shadow-lg w-100 mx-2">
          <ModeSelect activeMode={mode} onChange={this.switchMode} />
          <div className="p-4">
            <FormInput
              id="inputLogin"
              label="Login"
            />
            <FormInput
              id="inputPassword"
              label="Password"
              secured={true}
            />
            <ErrorLabel message={error} />
            <SubmitButton onSubmit={() => {}}>
              {mode}
            </SubmitButton>
          </div>
        </form>
      </div>
    );
  }
}

const mapStateToProps = state => ({});

const mapDispatchToProps = dispatch => ({});

export default connect(mapStateToProps, mapDispatchToProps)(Login);