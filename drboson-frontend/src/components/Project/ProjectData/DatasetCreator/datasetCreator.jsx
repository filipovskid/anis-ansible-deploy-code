import React from 'react';
import { ReactSVG } from 'react-svg';
import trash from '../../../../images/trash-2.svg';
import arrow from '../../../../images/arrow-right.svg';
import './datasetCreator.css';

const DatasetCreator = (props) => {

    const onFormSubmit = (e) => {
        e.preventDefault();

        const dataset = {
            "name": e.target.name.value,
            "file": props.file
        }

        props.uploadDataset(props.id, dataset);
    };

    return (
        <form onSubmit={onFormSubmit} className="dataset-creator__wrapper">
            <div className="dataset-creator">
                <div className="dataset-creator__item">
                    <div className="dataset-creator__item--header">Name</div>
                    <div className="dataset-creator__item--input">
                        <input name="name" type="text" />
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
                    <button className="btn dataset-creator--action submit">
                        <ReactSVG src={arrow} />
                    </button>
                    <button onClick={() => props.onComposerRemove(props.id)} className="btn dataset-creator--action" type="submit">
                        <ReactSVG src={trash} />
                    </button>
                </div>
            </div>
        </form>
    );
}

export default DatasetCreator;