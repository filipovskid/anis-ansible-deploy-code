import React from 'react';
import '../../styles/header.css';
import logo from '../../images/logos/drboson-light-yl.svg';

const Header = (props) => {
    return (
        <header class="d-flex align-items-center px-3 py-2">
            <div class="header-item header-logo flex-grow-1">
                <img src={logo} />
            </div>

            <div class="header-item avatar">
                <div data-toggle="dropdown">
                    <img src="https://avatars1.githubusercontent.com/u/37289276" />
                </div>
                <div class="dropdown-menu dropdown-menu-right">
                    <button class="dropdown-item" type="button">Action</button>
                    <button class="dropdown-item" type="button">Another action</button>
                    <button class="dropdown-item" type="button">Something else here</button>
                </div>
            </div>
        </header>
    );
}

export default Header