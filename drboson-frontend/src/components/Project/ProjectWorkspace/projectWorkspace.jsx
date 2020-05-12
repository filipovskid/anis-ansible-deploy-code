import React from 'react';
import { ReactSVG } from 'react-svg';
import VisPlane from '../../Visualization/VisPlane/visPlane';
import VisBuilder from '../../Visualization/VisBuilder/visBuilder';
import plus from '../../../images/plus.svg';
import './projectWorkspace.css';

const ProjectWorkspace = (props) => {
    return (
        <div className="workspace">
            <div className="workspace__header">
                <div className="workspace__header--heading">Visuailization</div>
                <div className="workspace__header--actions">
                    <span className="action"><ReactSVG src={plus} /></span>
                </div>
            </div>
            <div className="workspace__content">
                <VisPlane />
                <VisBuilder />
            </div>
        </div>
    );
}

export default ProjectWorkspace;