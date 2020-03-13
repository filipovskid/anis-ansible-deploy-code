import React from 'react';
import '../../styles/header.css';
import logo from '../../images/logos/drboson-light-yl.svg';

const Header = (props) => {
    return (
        <header className="d-flex align-items-center px-3 py-2">
            <div className="header-item header-logo flex-grow-1">
                <img src={logo} />
            </div>

            <div className="header-item avatar">
                <div data-toggle="dropdown">
                    <img src="https://avatars1.githubusercontent.com/u/37289276" />
                </div>
                <div className="dropdown-menu dropdown-menu-right">
                    <button className="dropdown-item" type="button">Action</button>
                    <button className="dropdown-item" type="button">Another action</button>
                    <button className="dropdown-item" type="button">Something else here</button>
                </div>
            </div>
        </header>
    );
}

export default Header