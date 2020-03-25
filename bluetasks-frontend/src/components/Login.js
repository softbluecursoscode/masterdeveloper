import React, { Component } from 'react';
import AuthService from '../api/AuthService';
import Alert from './Alert'
import { Redirect } from 'react-router-dom';

class Login extends Component {
    constructor(props) {
        super(props);
        
        this.state = {
            username: "",
            password: "",
            alert: null,
            processing: false,
            loggedIn: false
        }

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleInputChanged = this.handleInputChanged.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        this.setState({ processing: true });
        AuthService.login(this.state.username, this.state.password,
            success => {
                if (success) {
                    this.setState({ loggedIn: true, processing: false });
                    this.props.onLoginSuccess();
                } else {
                    this.setState({ alert: "O login não pode ser realizado", processing: false });
                }
        });
    }

    handleInputChanged(event) {
        const field = event.target.name;
        const value = event.target.value;
        this.setState({ [field]: value });
    }

    render() {
        if (AuthService.isAuthenticated() || this.state.loggedIn) {
            return <Redirect to="/" />
        }

        return (
            <div>
                <h1>Login</h1>
                { this.state.alert !== null ? <Alert message={this.state.alert} /> : "" }
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="username">Usuário</label>
                        <input
                            type="text"
                            className="form-control"
                            onChange={this.handleInputChanged}
                            value={this.state.username}
                            name="username"
                            placeholder="Digite o usuário" />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Senha</label>
                        <input
                            type="password"
                            className="form-control"
                            onChange={this.handleInputChanged}
                            value={this.state.password}
                            name="password"
                            placeholder="Digite a senha" />
                    </div>
                    <button
                        type="submit"
                        className="btn btn-primary"
                        disabled={this.state.processing}>Login</button>
                </form>
            </div>
        );
    }
}

export default Login;