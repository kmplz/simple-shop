import React from 'react';
import FormInput from './login/FormInput';

export default () => (
  <div className="h-100 d-flex align-items-center justify-content-center">
    <form
      className="login-form needs-validation column shadow-lg w-100 mx-2"
    >

      <div className="px-4 py-3 bg-dark text-white font-weight-bold">
        Авторизация
      </div>

      <div className="p-4">
        <FormInput
          id="inputLogin"
          label="Логин"
        />
        <FormInput
          id="inputPassword"
          label="Пароль"
          secured={true}
        />

        <div className="alert alert-danger rounded-0" role="alert">
          Ошибка
        </div>

        <div className="form-group row">
          <div className="col-12">
            <button type="submit" className="btn btn-dark w-100 rounded-0">Войти</button>
          </div>
        </div>
      </div>

    </form>
  </div>
);