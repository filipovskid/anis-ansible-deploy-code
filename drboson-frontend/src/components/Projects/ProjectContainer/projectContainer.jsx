import React, { useState, useEffect } from 'react';
import ProjectCard from '../ProjectCard/projectCard';
import projectIcon from '../../../images/project.svg';
import ProjectService from '../../../actions/project';
import NoProjects from '../../NoData/NoProjects/NoProjects';

const ProjectContainer = (props) => {
    const [projects, setProjects] = useState([]);

    useEffect(() => {
        console.log('effect');
        ProjectService.fetchProjects().then((response) => {
            console.log(response);
            setProjects(response.data);
        })
    }, []);

    const containerContent = () => {
        const projectCards = projects.map((project) => <ProjectCard key={project.id} projectDetails={project} username={props.username} />);
        if (projects.length > 0)
            return (
                <div className="projects row">
                    {projectCards}
                </div>
            );

        return <NoProjects />;
    }

    return (
        <div className="projects-container">
            <div className="projects-header">
                <img className="pr-2"
                    alt=""
                    src={projectIcon} style={{ width: '30px' }} />
                <span>Projects</span>
                <hr />
            </div>
            {containerContent()}
        </div>
    );
}

export default ProjectContainer;