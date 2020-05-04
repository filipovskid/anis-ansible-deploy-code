import React from 'react';
import VisPlane from '../../Visualization/VisPlane/visPlane';

const ProjectWorkspace = (props) => {
    return (
        <div className="workspace">
            <div className="workspace__header"></div>
            <div className="workspace__content">
                <VisPlane />
            </div>
        </div>
    );
}

export default ProjectWorkspace;