import React, { Component } from 'react';

class Registration extends Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            email: '',
            password: '',
            passwordConfirmation: ''
        }
    }

    handleChange = (e) => {
        const target = e.target;
        const name = target.name;

        this.setState({
            [name]: target.value
        });
    }

    render() {
        return (
            <div className="auth-form mx-auto mt-5" style={{ width: '500px' }}>
                <form>
                    <div className="auth-form__body border rounded p-4">
                        <div className="form-group">
                            <label for="username">Username</label>
                            <input type="email" className="form-control" aria-describedby="emailHelp"
                                placeholder="Username"
                                id="username"
                                name="username"
                                value={this.state.username}
                                onChange={this.handleChange} />
                        </div>
                        <div className="form-group">
                            <label for="email-input">Email address</label>
                            <input type="email" className="form-control" aria-describedby="emailHelp"
                                placeholder="johny@email.com"
                                id="email"
                                name="email"
                                value={this.state.email}
                                onChange={this.handleChange} />
                        </div>
                        <div className="form-group">
                            <label for="pass-input">Password</label>
                            <input type="password" className="form-control" placeholder="Password"
                                id="pass-input"
                                name="password"
                                value={this.state.password}
                                onChange={this.handleChange} />
                        </div>

                        <div className="form-group">
                            <label for="pass-confirm">Confirm password</label>
                            <input type="password" className="form-control" placeholder="Password"
                                id="pass-confirm"
                                name="passwordConfirmation"
                                value={this.state.passwordConfirmation}
                                onChange={this.handleChange} />
                        </div>
                        <button type="submit" className="btn btn-primary">Login</button>
                    </div>
                </form>
            </div>
        );
    }
}

export default Registration;