import React, { useState } from "react";
import { Link, useNavigate } from 'react-router-dom';

export const Register = (props) => {

  const handleSubmit = async (e) => {
    e.preventDefault();

    const requestBody = {
      nome: name,
      email: email,
      senha: pass
    };

    try {
      const response = await fetch('/api/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
      });

      const data = await response.json();

      if (response.ok) {
        navigate('/');
      } else {
        setErrorMsg(data.descricao);
      }
    } catch (error) {
      setErrorMsg('Ocorreu um erro ao tentar registrar');
    }
  }

    return (
        <div className="auth-form-container">
            <h2>Registrar</h2>
            {errorMsg && <div className="error">{errorMsg}</div>}
        <form className="register-form" onSubmit={handleSubmit}>
            <label htmlFor="name">Nome completo: </label>
            <input value={name} name="name" onChange={(e) => setName(e.target.value)} id="name" placeholder="Nome completo" />
            <label htmlFor="email">E-mail: </label>
            <input value={email} onChange={(e) => setEmail(e.target.value)}type="email" placeholder="youremail@gmail.com" id="email" name="email" />
            <label htmlFor="password">Senha: </label>
            <input value={pass} onChange={(e) => setPass(e.target.value)} type="password" placeholder="********" id="password" name="password" />
            <button type="submit">Registrar</button>
        </form>
        <Link to="/" className="link-btn">
      Já possui conta? Faça o login.
    </Link>
    </div>
    )
}