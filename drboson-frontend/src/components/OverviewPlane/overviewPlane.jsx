import React from 'react';
import './overviewPlane.css';

const OverviewPlane = (props) => {
    const { overview } = props;
    const items = overview.items.map(item => {
        return (
            <div className="overview-item">
                <div className="overview-item--key">{item.key}</div>
                <div className="overview-item--value">{item.value}</div>
            </div>
        );
    })

    return (
        <div className="overview-plane">
            <div className="overview-plane--wrapper">
                <div className="overview-header">
                    <div className="overview-header--heading">
                        <h1 className="header">{overview.heading}</h1>
                    </div>
                    <div className="overview-header--desc">{overview.desc}</div>

                    {items}
                </div>
            </div>
        </div>
    );
}

export default OverviewPlane;