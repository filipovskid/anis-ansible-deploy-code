import React from 'react';
import { curveCatmullRom } from 'd3-shape';
import VisConfigurer from '../VisConfigurer/visConfigurer';
import './lineSeriesConfigurer.css';

import {
    // XYPlot,
    XAxis,
    YAxis,
    ChartLabel,
    HorizontalGridLines,
    VerticalGridLines,
    LineSeries,
    FlexibleXYPlot,
} from 'react-vis';

const LineSeriesConfigurer = (props) => {

    const lineSeriesConfigurer = (
        <div className="line-series-config">
            <h4 className="config-title">Line Plot Configurer</h4>
            <hr />
            <div className="input-group row no-gutters mb-2">
                <label for="plotTitle" className="col-2 col-form-label">Title</label>
                <div class="col-10 ml-sm-n4">
                    <input type="text" readonly class="form-control" id="plotTitle" value="" />
                </div>
            </div>

            <div className="input-group row no-gutters mb-2">
                <label for="X" className="col-2 col-form-label">X</label>
                <div class="col-10 ml-sm-n4">
                    <select class="custom-select" name="X">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                </div>
            </div>

            <div className="input-group row no-gutters mb-2">
                <label for="Y" className="col-2 col-form-label">Y</label>
                <div class="col-10 ml-sm-n4">
                    <select class="custom-select" name="Y">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                </div>
            </div>
        </div>
    );

    const lineSeriesPreview = (
        <React.Fragment>
            <h6 className="plot-title">Darko Filipvski</h6>
            <FlexibleXYPlot>
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
            </FlexibleXYPlot>
        </React.Fragment>
    );

    return (
        <VisConfigurer preview={lineSeriesPreview} configurer={lineSeriesConfigurer} />
    );
}

export default LineSeriesConfigurer;