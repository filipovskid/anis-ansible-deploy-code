import React, { Component } from 'react';
import {
  BrowserRouter as Router, Route
} from "react-router-dom";
// import './App.css';
import '../../styles/main.css';
import Header from '../Header/header';
import Registration from '../Auth/Registration'
import Login from '../Auth/Login'
import AuthenticationService from '../../actions/auth';
import ProtectedRoute from '../ProtectedRoute/ProtectedRoute';
import HomePage from '../Page/HomePage/homePage';
import CreateProject from '../Projects/CreateProject/CreateProject';
import ProjectPage from '../Project/ProjectPage/projectPage';
import ProjectData from '../Project/ProjectData/projectData';
import ProjectInfo from '../Project/ProjectInfo/projectInfo';
import ProjectWorkspace from '../Project/ProjectWorkspace/projectWorkspace';
import CreateRun from '../Run/CreateRun/createRun';


class App extends Component {

  constructor(props) {
    super(props);

    this.state = {
      isAuthenticated: null,
      userDetails: {}
    }
  }

  componentDidMount() {
    this.checkLoginStatus();
  }

  onUserLogin = () => {
    return this.checkLoginStatus();
  }

  checkLoginStatus = () => {
    return AuthenticationService.checkLoginStatus()
      .then(response => {
        this.setState({
          isAuthenticated: true,
          userDetails: response.data
        });
      }).catch(error => {
        this.setState({
          isAuthenticated: false,
          userDetails: {}
        });
      });
  }

  render() {
    if (this.state.isAuthenticated == null) {
      return null;
    }

    return (
      <Router>
        <Header isAuthenticated={this.state.isAuthenticated} />
        <div className='container-xl'>
          <Route exact path='/join'>
            <Registration isAuthenticated={this.state.isAuthenticated} />
          </Route>
          <Route exact path='/login'>
            <Login isAuthenticated={this.state.isAuthenticated} onUserLogin={this.onUserLogin} />
          </Route>
          <ProtectedRoute exact path='/' userDetails={this.state.userDetails}
            isAuthenticated={this.state.isAuthenticated} component={HomePage} />
          <ProtectedRoute exact path='/new'
            isAuthenticated={this.state.isAuthenticated} component={CreateProject} />
          <ProtectedRoute exact path='/:projectId/run/new'
            isAuthenticated={this.state.isAuthenticated} component={CreateRun} />
        </div>

        <ProtectedRoute exact path='/:projectId/info'>
          <ProjectPage component={ProjectInfo} />
        </ProtectedRoute>

        <ProtectedRoute exact path='/:projectId/workspace'>
          <ProjectPage component={ProjectWorkspace} />
        </ProtectedRoute>

        <ProtectedRoute exact path='/:projectId/data'>
          <ProjectPage component={ProjectData} />
        </ProtectedRoute>
      </Router >
    );
  }
}

export default App;
