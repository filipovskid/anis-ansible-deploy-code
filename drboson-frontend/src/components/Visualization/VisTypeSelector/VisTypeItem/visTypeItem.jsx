import React from 'react';
import '../visType.css';
import { ReactSVG } from 'react-svg'
import lineChart from '../chart-art/line-chart.svg';


const VisTypeItem = (props) => {

    return (
        <div onClick={props.onClick} className="vis-type-selector__item">
            <div className="vis-type-selector__item--type">
                <img src={lineChart} alt="" />
            </div>
            <div className="vis-type-selector__item--name">
                Item text
            </div>
        </div>
    );
}

export default VisTypeItem;