import React from 'react';
import NavItem from '../NavItem/navItem'
import '../navSidebar.css';


const NavSidebar = (props) => {
    const { details } = props;

    const navItems = details.map(item => <NavItem name={item.name} icon={item.icon} to={item.to} />)

    return (
        <div className="nav-sidebar">
            <div className="nav-sidebar__items">
                {navItems}
            </div>
        </div>
    );

}

export default NavSidebar;