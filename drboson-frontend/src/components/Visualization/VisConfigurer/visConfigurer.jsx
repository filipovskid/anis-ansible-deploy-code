import React from 'react';
import './visConfigurer.css';

const VisConfigurer = (props) => {

    return (
        <div className="visualization-modal">
            <div className="visualization-config">
                <div className="visualization-config__preview">
                    {props.preview}
                </div>
                <div className="visualization-config__settings">
                    {props.configurer}
                </div>
            </div>
            <div className="visualization-actions">
                <button type="button" class="btn btn-cancel">Cancel</button>
                <button type="button" class="btn btn-ok">Save</button>
            </div>
        </div>

    );
}

export default VisConfigurer;