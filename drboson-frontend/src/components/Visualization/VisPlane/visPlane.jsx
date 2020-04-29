import React from 'react';
import { ReactSVG } from 'react-svg';
import VisPanel from '../VisPanel/visPanel';
import plus from '../../../images/plus.svg';
import './visPlane.css';


const VisPlane = (props) => {
    return (
        <div className="visualization-plane">
            <div className="visualization-plane__header">
                <h6 className="visualization-plane__header--title">Visualization</h6>
                <div className="visualization-plane__header--actions">
                    <span className="add-vis vis-action">
                        <ReactSVG src={plus} />
                    </span>
                </div>
            </div>
            <div className="visualization-plane__content">
                <VisPanel />
            </div>
        </div>
    );
}

export default VisPlane;