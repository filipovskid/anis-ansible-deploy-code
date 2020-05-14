import React, { useState } from 'react';
import { ReactSVG } from 'react-svg';
import { curveCatmullRom } from 'd3-shape';
import VisMaxPanel from './visMaxPanel';
import maximize from '../../../images/maximize.svg';
import './visPanel.css';

import {
    // XYPlot,
    XAxis,
    YAxis,
    // ChartLabel,
    HorizontalGridLines,
    // VerticalGridLines,
    LineSeries,
    FlexibleXYPlot,
} from 'react-vis';

const VisPanel = (props) => {
    const [isPanelMaximized, setMaximizedState] = useState(false);
    const { preview } = props;

    const maximizePanel = () => {
        setMaximizedState(true);
    }

    const minimizePanel = () => {
        setMaximizedState(false);
    }

    return (
        <React.Fragment>
            <div className="panel">
                <div className="panel__actions">
                    <span onClick={maximizePanel} className="panel__actions--action">
                        <ReactSVG src={maximize} />
                    </span>
                </div>
                <div className="panel__content">
                    <h6 className="panel__content--title">{preview.title}</h6>
                    <div className="panel__content--vis">
                        {preview.plot}
                    </div>
                </div>
            </div>

            <VisMaxPanel preview={preview}
                isMaximized={isPanelMaximized}
                minimizePanel={minimizePanel} />

        </React.Fragment>
    );
}

export default VisPanel;