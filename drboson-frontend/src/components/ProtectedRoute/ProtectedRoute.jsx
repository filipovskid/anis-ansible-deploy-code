import React, { Component } from 'react';
import {
    Redirect,
    BrowserRouter as Route,
    // Switch,
    // Route,
    // Link
} from "react-router-dom";

class ProtectedRoute extends Component {
    constructor(props) {
        super(props);
        this.state = {
            authenticated: this.props.authenticated
        }
    }

    render() {
        const { component: Component, ...props } = this.props;

        return (
            <Route
                {...props}
                render={props => (
                    this.state.authenticated ?
                        <Component {...props} /> :
                        <Redirect to='/login' />
                )}
            />
        )
    }
}

export default ProtectedRoute;