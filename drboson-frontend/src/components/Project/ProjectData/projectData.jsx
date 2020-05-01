import React from 'react';
import DataUploader from './DataUploader/dataUploader';
import DatasetCreator from './CreateDataset/datasetCreator';
import './projectData.css';

const ProjectData = (props) => {

    return (
        <div className="project-data">
            <div className="project-data__uploader">
                <DataUploader />
            </div>
            <div className="project-data__content">
                <DatasetCreator />
            </div>
        </div >
    );
}

export default ProjectData;