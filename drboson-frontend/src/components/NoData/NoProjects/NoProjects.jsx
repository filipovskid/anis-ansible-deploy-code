import React from 'react';
import noProjectArt from '../../../images/drawkit-charts-and-graphs-monochrome.svg';
import '../noData.css';

const NoProjects = (props) => (
    <div className="no-data d-flex justify-content-center mt-n3">
        <div className="no-data__art">
            <img src={noProjectArt} />
        </div>
        <div className="no-data__content text-muted align-self-center mt-5">
            <p className="h5">There are no projects yet.</p>
            <p>Create a project and analyse your results.</p>
        </div>
    </div>
);

export default NoProjects;