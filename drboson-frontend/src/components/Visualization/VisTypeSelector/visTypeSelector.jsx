import React from 'react';
import VisTypeItem from './VisTypeItem/visTypeItem';
import './visType.css'

const VisTypeSelector = (props) => {

    return (
        <div className="vis-type-selector">
            <div className="vis-type-selector__section">
                <div className="vis-type-selector__section--heading">
                    Charts
                </div>
                <div className="vis-type-selector__section--content">
                    <VisTypeItem />
                    <VisTypeItem />
                    <VisTypeItem />
                    <VisTypeItem />
                    <VisTypeItem />
                    <VisTypeItem />
                    <VisTypeItem />
                    <VisTypeItem />
                    <VisTypeItem />
                    <VisTypeItem />
                </div>
            </div>
            {/* <div className="vis-type-selector__section disabled">
                <div className="vis-type-selector__section--heading">
                    Charts
                </div>
                <div className="vis-type-selector__section--content">
                    <VisTypeItem />
                </div>
            </div> */}
        </div>
    );
}

export default VisTypeSelector;