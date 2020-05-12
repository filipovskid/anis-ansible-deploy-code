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

const VisPanel = (params) => {

    const [isPanelMaximized, setMaximizedState] = useState(false);

    const maximizePanel = () => {
        setMaximizedState(true);
    }

    const minimizePanel = () => {
        setMaximizedState(false);
    }

    const lineSeriesPreview = (
        <FlexibleXYPlot margin={{ bottom: 34, left: 50, right: 20, top: 10 }}>
            <HorizontalGridLines style={{ strokeWidth: 0.5 }} />
            {/* <VerticalGridLines /> */}
            <XAxis />
            <YAxis style={{ line: { stroke: "none" } }} />

            <LineSeries
                className="first-series"
                data={[{ x: 1, y: 3 }, { x: 2, y: 5 }, { x: 3, y: 15 }, { x: 4, y: 12 }]}
            />
            <LineSeries className="second-series" data={null} />
            <LineSeries
                className="third-series"
                curve={'curveMonotoneX'}
                data={[{ x: 1, y: 10 }, { x: 2, y: 4 }, { x: 3, y: 2 }, { x: 4, y: 15 }]}
                strokeDasharray={'7, 3'}
            />
            <LineSeries
                className="fourth-series"
                curve={curveCatmullRom.alpha(0.5)}
                style={{
                    // note that this can not be translated to the canvas version
                    strokeDasharray: '2 2'
                }}
                data={[{ x: 1, y: 7 }, { x: 2, y: 11 }, { x: 3, y: 9 }, { x: 4, y: 2 }]}
            />
        </FlexibleXYPlot >
    );

    return (
        <React.Fragment>
            <div className="panel">
                <div className="panel__actions">
                    <span onClick={maximizePanel} className="panel__actions--action">
                        <ReactSVG src={maximize} />
                    </span>
                </div>
                <div className="panel__content">
                    <h6 className="panel__content--title">Darko Filipovski</h6>
                    <div className="panel__content--vis">
                        {lineSeriesPreview}
                    </div>
                </div>
            </div>

            <VisMaxPanel visualization={lineSeriesPreview}
                isMaximized={isPanelMaximized}
                minimizePanel={minimizePanel} />

        </React.Fragment>
    );
}

export default VisPanel;