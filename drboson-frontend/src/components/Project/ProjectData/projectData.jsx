import React, { useState, useEffect } from 'react';
import { useParams } from "react-router-dom";
import DataUploader from './DataUploader/dataUploader';
import DatasetCreator from './DatasetCreator/datasetCreator';
import DatasetItem from './DatasetItem/datasetItem';
import NoDatasets from '../../NoData/NoDatasets/noDatasets';
import DatasetService from '../../../actions/dataset';
import './projectData.css';

const ProjectData = (props) => {

    const [dataItems, setDataItems] = useState([]);
    const [dataComposers, setDataComposers] = useState([]);
    const { projectId } = useParams();

    useEffect(() => {
        DatasetService.fetchDatasets(projectId)
            .then(response => {
                setDataItems(response.data);
            });
    }, [projectId]); // dataComposers

    const createDatasetComposer = (file) => {
        const shortid = require('shortid');

        const datasetComposer = {
            "id": shortid.generate(),
            "file": file
        };

        setDataComposers(oldData => [datasetComposer, ...oldData]);
    };

    const removeDatasetComposer = (composerId) => {
        setDataComposers(oldData => oldData.filter(composer => composer.id !== composerId));
    };

    const uploadDataset = (composerId, dataset) => {
        DatasetService.createDataset(projectId, dataset)
            .then(response => {
                removeDatasetComposer(composerId);
                setDataItems(dataItems => [...dataItems, response.data])
            });
    };

    const removeDataset = (datasetId) => {
        DatasetService.removeDataset(projectId, datasetId)
            .then(rseponse => {
                setDataItems(dataItems => dataItems.filter(dataset => dataset.id !== datasetId));
            });
    };

    const downloadDataset = (datasetId, name) => {
        DatasetService.downloadDataset(projectId, datasetId)
            .then(response => {
                var fileDownload = require('js-file-download');
                fileDownload(response.data, name);
            });
    }

    const composers = dataComposers.map(composer => {
        return <DatasetCreator onComposerRemove={removeDatasetComposer}
            uploadDataset={uploadDataset}
            id={composer.id}
            key={composer.id}
            file={composer.file} />;
    });

    const datasets = dataItems.map(dataset => <DatasetItem onDatasetRemove={removeDataset}
        onDatasetDownload={downloadDataset}
        dataset={dataset}
        key={dataset.id} />);

    return (
        <div className="project-data">
            <div className="project-data__uploader">
                <DataUploader onFileAttach={createDatasetComposer} />
            </div>
            {
                (!Array.isArray(composers) || !composers.length) && (!Array.isArray(datasets) || !datasets.length)
                    ? <NoDatasets />
                    : (
                        <div className="project-data__content">

                            <div className="project-data__content--composers">
                                {composers}
                            </div>
                            <div className="project-data__content--datasets">
                                {datasets}
                            </div>
                        </div>
                    )
            }
        </div >
    );
}

export default ProjectData;