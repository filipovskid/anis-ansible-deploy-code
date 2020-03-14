import React, { Component } from 'react';
import AuthenticationService from '../../actions/auth';

class Login extends Component {

    constructor(props) {
        super(props);

        this.state = {
            username: '',
            password: ''
        };
    }

    handleChange = (e) => {
        const target = e.target;
        const name = target.name;
        console.log(name, target.value);

        this.setState({
            [name]: target.value
        });
    }

    render() {
        return (
            <div className="auth-form mx-auto mt-5" style={{ width: '300px' }}>
                <form onSubmit={this.registerUser}>
                    <div className="auth-form__body border rounded p-4">
                        <div className="form-group">
                            <label htmlFor="username">Username</label>
                            <input type="text" className="form-control" aria-describedby="emailHelp"
                                placeholder="Username"
                                id="username"
                                name="username"
                                value={this.state.username}
                                onChange={this.handleChange} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="pass-input">Password</label>
                            <input type="password" className="form-control" placeholder="Password"
                                id="pass-input"
                                name="password"
                                value={this.state.password}
                                onChange={this.handleChange} />
                        </div>
                        <button type="submit" className="btn btn-primary">Login</button>
                    </div>
                </form>
            </div>
        );
    }

}

export default Login;