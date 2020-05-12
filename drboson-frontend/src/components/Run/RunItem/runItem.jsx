import React from 'react';
import { ReactSVG } from 'react-svg';
import RunStatus from '../RunStatus/runStatus';
import trash from '../../../images/trash-2.svg';
import terminal from '../../../images/terminal.svg';
import folder from '../../../images/folder.svg';
import './runItem.css';

const RunItem = (props) => {
    const { run } = props;

    return (
        <div className="run-container">
            <div className="run">
                <div className="run__info-item">
                    <div className="run__info-item--heading">Name</div>
                    <div className="run__info-item--info">{run.name}</div>
                </div>

                <div className="run__info-item">
                    <div className="run__info-item--heading">Status</div>
                    <div className="run__info-item--info"><RunStatus status={run.status} /></div>
                </div>

                <div className="run__info-item">
                    <div className="run__info-item--heading">Created</div>
                    <div className="run__info-item--info">N/A</div>
                </div>

                <div className="run__info-item run__actions">
                    <button onClick={() => { }} className="btn run--action" type="submit">
                        <ReactSVG src={trash} />
                    </button>
                    <button onClick={() => { }} className="btn run--action">
                        <ReactSVG src={folder} />
                    </button>
                    <button onClick={() => { }} className="btn run--action">
                        <ReactSVG src={terminal} />
                    </button>
                </div>
            </div>
        </div>
    );
}

export default RunItem;