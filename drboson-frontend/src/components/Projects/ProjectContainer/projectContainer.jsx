import React from 'react';
import ProjectCard from '../ProjectCard/projectCard';
import projectIcon from '../../../images/project.svg';

const ProjectContainer = (props) => {
    return (
        <div className="projects-container" style={{ backgroundColor: "#fbfbfb" }}>
            <div className="projects-header">
                <img className="pr-2"
                    alt=""
                    src={projectIcon} style={{ width: '30px' }} />
                Projects
            </div>

            <div className="projects row">
                <ProjectCard />
                <ProjectCard />
                <ProjectCard />
                <ProjectCard />
            </div>
        </div>
    );
}

export default ProjectContainer;