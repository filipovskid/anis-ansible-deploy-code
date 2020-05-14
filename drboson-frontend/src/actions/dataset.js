import axios_instance from '../axios/axios-config'
// import qs from 'qs';

const DatasetService = {
    fetchDatasets: (projectId) => {
        return axios_instance.get(`/${projectId}/dataset`);
    },
    createDataset: (projectId, dataset) => {
        const data = new FormData();
        data.set('name', dataset.name);
        data.set('file', dataset.file);

        return axios_instance.post(`/${projectId}/dataset`, data, {
            headers: { 'Content-Type': 'multipart/form-data' }
        });
    },
    removeDataset: (projectId, datasetId) => {
        return axios_instance.delete(`/${projectId}/dataset/${datasetId}`);
    },
    downloadDataset: (projectId, datasetId, fileName) => {
        return axios_instance.get(`/${projectId}/dataset/${datasetId}/download`);
    }
}

export default DatasetService;