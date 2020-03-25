import React, { Component } from 'react';
import NavBar from './components/NavBar';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import TaskListTable from './components/TaskListTable';
import TaskForm from './components/TaskForm';
import Login from './components/Login';

class App extends Component {
  constructor(props) {  
    super(props)

    this.onRefreshHandler = this.onRefreshHandler.bind(this);
  }

  onRefreshHandler() {
    this.forceUpdate();
  }

  render() {
    return (
      <BrowserRouter>
        <div className="App">
          <NavBar onLinkClick={this.onRefreshHandler} />
          <div className="container" style={{ marginTop: 20 }}>
          <Switch>
          <Route exact path="/login" render={() => <Login onLoginSuccess={this.onRefreshHandler} />} />
            <Route exact path="/form" component={TaskForm} />
            <Route exact path="/form/:id" component={TaskForm} />
            <Route path="/" component={TaskListTable} />
          </Switch>
          </div>
        </div>
      </BrowserRouter>
    );
  }
}

export default App;
