import React from 'react';
import { ReactSVG } from 'react-svg';
import trash from '../../../../images/trash-2.svg';
import download from '../../../../images/download.svg';
import './datasetCreator.css';

const DatasetCreator = (props) => {
    return (
        <div className="dataset-creator">
            <div className="dataset-creator__item">
                <div className="dataset-creator__item--header">Name</div>
                <div className="dataset-creator__item--input">
                    <input type="text" />
                </div>
            </div>

            <div className="dataset-creator__item">
                <div className="dataset-creator__item--header">Type</div>
                <div className="dataset-creator__item--input">
                    File Type
                </div>
            </div>

            <div className="dataset-creator__item">
                <div className="dataset-creator__item--header">Date</div>
                <div className="dataset-creator__item--input">
                    Current Date
                </div>
            </div>

            <div className="dataset-creator__item dataset-creator__actions">
                <button className="btn dataset-creator--action">
                    <ReactSVG src={trash} />
                </button>
                <button className="btn dataset-creator--action">
                    <ReactSVG src={download} />
                </button>
            </div>
        </div>
    );
}

export default DatasetCreator;