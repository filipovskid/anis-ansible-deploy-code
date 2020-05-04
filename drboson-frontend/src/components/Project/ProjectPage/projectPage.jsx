import React from 'react';
import NavSidebar from '../../NavSidebar/NavSidebar/navSidebar';
import './projectPage.css';

const ProjectPage = (props) => {

    return (
        <div className="project-page">
            <NavSidebar />
            <div className="project-page__content">
                {props.children}
            </div>
        </div >
    );
}

export default ProjectPage