import React, { Component } from 'react';
import TaskService from '../api/TaskService';
import { Redirect } from 'react-router-dom';
import AuthService from '../api/AuthService';
import Spinner from './Spinner';
import Alert from './Alert'

class TaskForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            task: {
                id: 0,
                description: "",
                whenToDo: ""
            },
            redirect: false,
            buttonName: "Cadastrar",
            alert: null,
            loading: false,
            saving: false
        }
        
        this.onSubmitHandler = this.onSubmitHandler.bind(this);
        this.onInputChangeHandler = this.onInputChangeHandler.bind(this);
    }

    componentDidMount() {
        const editId = this.props.match.params.id;
        if (editId) {
            this.setState({ loading: true});
            TaskService.load(~~editId,
                task => this.setState({ task : task, loading: false, buttonName: "Alterar" }),
                error => {
                    if (error.response) {
                        if (error.response.status === 404) {
                            this.setErrorState("Tarefa não encontrada");
                        } else {
                            this.setErrorState(`Erro ao carregar dados: ${error.response}`);
                        }
                    } else {
                        this.setErrorState(`Erro na requisição: ${error.message}`);
                    }
                });
        }
    }

    setErrorState(error) {
        this.setState({ alert: error, loading: false, saving: false });
    }

    onSubmitHandler(event) {
        event.preventDefault();
        this.setState({ saving: true, alert: null });
        TaskService.save(this.state.task,
            () => this.setState({ redirect : true, saving: false }),
            error => {
                if (error.response) {
                    this.setErrorState(`Erro: ${error.response.data.error}`);
                } else {
                    this.setErrorState(`Erro na requisição: ${error.message}`);
                }
            })
    }

    onInputChangeHandler(event) {
        const field = event.target.name;
        const value = event.target.value;
        this.setState(prevState => ({ task: { ...prevState.task, [field]: value }}));
    }

    render() {
        if (!AuthService.isAuthenticated()) {
            return <Redirect to="/login" />
        }
        
        if (this.state.redirect) {
            return <Redirect to="/" />
        }

        if (this.state.loading) {
            return <Spinner />
        }

        return (
            <div>
                <h1>Cadastro da Tarefa</h1>
                { this.state.alert != null ? <Alert message={this.state.alert} /> : "" }
                <form onSubmit={this.onSubmitHandler}>
                    <div className="form-group">
                        <label htmlFor="description">Descrição</label>
                        <input type="text"
                            className="form-control"
                            name="description"
                            value={this.state.task.description}
                            placeholder="Digite a descrição"
                            onChange={this.onInputChangeHandler} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="whenToDo">Data</label>
                        <input type="date"
                            className="form-control"
                            name="whenToDo"
                            value={this.state.task.whenToDo}
                            placeholder="Informe a data"
                            onChange={this.onInputChangeHandler} />
                    </div>
                    <button
                        type="submit"
                        className="btn btn-primary"
                        disabled={this.state.saving}>
                            {
                                this.state.saving ?
                                    <span className="spinner-border spinner-border-sm"
                                        role="status" aria-hidden="true">
                                    </span>
                                : this.state.buttonName 
                            }
                        </button>
                    &nbsp;&nbsp;
                    <button
                        type="button"
                        className="btn btn-primary"
                        disabled={this.state.saving}
                        onClick={() => this.setState({ redirect: true })}>
                            Cancelar
                        </button>
                </form>
            </div>
        );
    }
}

export default TaskForm;