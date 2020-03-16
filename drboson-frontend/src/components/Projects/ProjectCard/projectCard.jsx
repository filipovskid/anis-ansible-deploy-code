import React from 'react';
import './projectCard.css'

const ProjectCard = (props) => {
    return (
        <div class="project col-lg-6 col-12 mt-3" >
            <div class="card shadow-sm rounded">
                <div class="card-body mb-4 py-2">
                    <div class="project__name">
                        Darko's test project
                    </div>
                    <div class="project__owner">
                        filipovski
                    </div>
                    <div class="project__description">
                        Something something
                    </div>
                </div>
                <div class="project__info card-footer">

                </div>
            </div>
        </div >
    );
}

export default ProjectCard;