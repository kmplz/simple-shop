import React from 'react';
import {connect} from 'react-redux';
import {me} from '../modules/auth/actions';
import {Redirect} from 'react-router-dom';
import Spinner from '../components/Spinner';

export const protect = WrappedComponent => {

  class AuthenticatedComponent extends React.Component {

    componentWillMount() {
      const {isAuthenticated, me} = this.props;
      if (!isAuthenticated) {
        me();
      }
    }

    render () {
      const {isAuthenticated, loading} = this.props;
      return isAuthenticated ? <WrappedComponent {...this.props} /> : loading ? <Spinner /> : <Redirect to="/login" />
    }
  }

  const mapStateToProps = ({auth: {isAuthenticated, loadingUser}}) => ({
    isAuthenticated,
    loading: loadingUser
  });

  const mapDispatchToProps = dispatch => ({
    me: () => dispatch(me())
  });

  return connect(mapStateToProps, mapDispatchToProps)(AuthenticatedComponent);

};