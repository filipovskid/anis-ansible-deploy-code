import React from 'react';
import { ReactSVG } from 'react-svg';
import trash from '../../../../images/trash-2.svg';
import download from '../../../../images/download.svg';
// import arrow from '../../../../images/arrow-right.svg';
import './datasetItem.css';

const DatasetItem = (props) => {
    // const [dataset, setDataset] = useState(props.dataset);

    return (
        <div className="dataset">
            <div className="dataset__item">
                <div className="dataset__item--header">Name</div>
                <div className="dataset__item--input">
                    {props.dataset.name}
                </div>
            </div>

            <div className="dataset__item">
                <div className="dataset__item--header">Type</div>
                <div className="dataset__item--input">
                    File Type
                </div>
            </div>

            <div className="dataset__item">
                <div className="dataset__item--header">Date</div>
                <div className="dataset__item--input">
                    Current Date
                </div>
            </div>

            <div className="dataset__item dataset__actions">
                <button onClick={() => props.onDatasetDownload(props.dataset.id, props.dataset.name)} className="btn dataset--action">
                    <ReactSVG src={download} />
                </button>
                <button onClick={() => props.onDatasetRemove(props.dataset.id)} className="btn dataset--action" type="submit">
                    <ReactSVG src={trash} />
                </button>
            </div>
        </div>
    );
}

export default DatasetItem;