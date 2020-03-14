import React, { Component } from 'react';
import {
  BrowserRouter as Router, Route,
  // Switch,
  // Route,
  // Link
} from "react-router-dom";
import './App.css';
import Header from '../Header/header';
import Registration from '../Auth/Registration'
import Login from '../Auth/Login'


class App extends Component {

  render() {
    return (
      <Router>
        <Header />
        <div className="container-xl">
          <Route exact path='/join'>
            <Registration />
          </Route>

          <Route exact path='/login'>
            <Login />
          </Route>
        </div>
      </Router>
    );
  }
}

export default App;
