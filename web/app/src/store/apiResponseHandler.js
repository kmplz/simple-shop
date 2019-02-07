const getResponse = payload => {
  return payload && payload.response;
};

const getData = response => {
  return (response && response.data) || {};
};

const getMessage = response => {
  return (response && response.error && response.error.message) || 'Network error';
};

export default store => next => action => {
  const response = getResponse(action.payload);
  if (action.error) {
    action.errorMsg = getMessage(response);
  } else {
    action.payload = getData(response);
  }
  next(action);
}