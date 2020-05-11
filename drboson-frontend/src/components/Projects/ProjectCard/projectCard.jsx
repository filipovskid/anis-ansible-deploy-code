import React from 'react';
import './projectCard.css'

const ProjectCard = (props) => {
    const { id, name, description } = props.projectDetails;

    return (
        <div className="project col-lg-6 col-12 mt-3" data={id} >
            <div className="card shadow-sm rounded">
                <div className="card-body mb-4 py-2">
                    <div className="project__name">
                        <span>{name}</span>
                    </div>
                    <div className="project__owner">
                        <span>{props.username}</span>
                    </div>
                    <div className="project__description">
                        <span>{description}</span>
                    </div>
                </div>
                <div className="project__info card-footer">

                </div>
            </div>
        </div >
    );
}

export default ProjectCard;