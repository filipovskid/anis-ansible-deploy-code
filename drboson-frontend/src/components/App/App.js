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
// import RepositorySearch from '../Repository/RepositorySearch/repositorySearch';


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
        <Header />
        <div className='container-xl'>
          <Route exact path='/join'>
            <Registration />
          </Route>
          <Route exact path='/login'>
            <Login onUserLogin={this.onUserLogin} />
          </Route>
          <ProtectedRoute exact path='/' userDetails={this.state.userDetails}
            isAuthenticated={this.state.isAuthenticated} component={HomePage} />
          <ProtectedRoute exact path='/new'
            isAuthenticated={this.state.isAuthenticated} component={CreateProject} />
          <ProtectedRoute exact path='/:projectId/run/new'
            isAuthenticated={this.state.isAuthenticated} component={CreateRun} />
        </div>

        <ProtectedRoute exact path='/:projectId/data'>
          <ProjectPage>
            <ProjectData />
          </ProjectPage>
        </ProtectedRoute>

        <ProtectedRoute exact path='/:projectId/info'>
          <ProjectPage>
            <ProjectInfo />
          </ProjectPage>
        </ProtectedRoute>

        <Route exact path='/test'>
          <ProjectPage>
            <ProjectWorkspace />
          </ProjectPage>
        </Route>
      </Router >
    );
  }
}

export default App;
