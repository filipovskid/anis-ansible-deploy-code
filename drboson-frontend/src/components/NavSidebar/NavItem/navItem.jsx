import React from 'react';
import { ReactSVG } from 'react-svg'
import { Link } from "react-router-dom";

const NavItem = (props) => {

    return (
        <Link to='/'>
            <div className="nav-sidebar__items__item">
                {/* <img src={props.item.icon} alt={props.item.name} /> */}
                <ReactSVG src={props.item.icon} />
            </div>
        </Link>
    );
}

export default NavItem;