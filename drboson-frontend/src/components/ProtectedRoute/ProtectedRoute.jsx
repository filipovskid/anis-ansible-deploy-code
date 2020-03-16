import React from 'react';
import {
    Route, Redirect
} from "react-router-dom";

const ProtectedRoute = ({ component: Component, isAuthenticated, ...rest }) => {
    console.log(isAuthenticated + " - isAuthenticated");

    return (
        <Route {...rest} render={(props) => {
            if (isAuthenticated) {
                return <Component />;
            } else return <Redirect to='/login' />
        }} />
    );
}

export default ProtectedRoute;