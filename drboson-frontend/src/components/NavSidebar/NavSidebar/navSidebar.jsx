import React from 'react';
import NavItem from '../NavItem/navItem'
import '../navSidebar.css';
import info from '../../../images/info.svg';
import analytics from '../../../images/analytics.svg';

const NavSidebar = (props) => {
    const testItems = [
        { name: 'Info', icon: info },
        { name: 'Workspace', icon: analytics }
    ]

    const navItems = testItems.map(item => <NavItem item={item} />)

    return (
        <div className="nav-sidebar">
            <div className="nav-sidebar__items">
                {navItems}
            </div>
        </div>
    );

}

export default NavSidebar;