import React from 'react';
import './projectCard.css'

const ProjectCard = (props) => {
    return (
        <div className="project col-lg-6 col-12 mt-3" >
            <div className="card shadow-sm rounded">
                <div className="card-body mb-4 py-2">
                    <div className="project__name">
                        Darko's test project
                    </div>
                    <div className="project__owner">
                        filipovski
                    </div>
                    <div className="project__description">
                        Something something
                    </div>
                </div>
                <div className="project__info card-footer">

                </div>
            </div>
        </div >
    );
}

export default ProjectCard;