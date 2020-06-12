import axios_instance from '../axios/axios-config'
import qs from 'qs';

const RunService = {
    createRun: (projectId, run) => {
        const formParams = qs.stringify(run);

        return axios_instance.post(`/${projectId}/run`, formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
    },
    fetchProjectRuns: (projectId) => {
        return axios_instance.get(`${projectId}/run`);
    },
    fetchProjectRunMetrics: (projectId) => {
        return axios_instance.get(`/${projectId}/run/logs`);
    },
    fetchRunMetrics: (projectId, runId) => {
        return axios_instance.get(`/${projectId}/run/${runId}/logs`);
    },
    fetchProjectRun: (projectId, runId) => {
        return axios_instance.get(`/${projectId}/run/${runId}`);
    },
    fetchRunFiles: (projectId, runId) => {
        return axios_instance.get(`/${projectId}/run/${runId}/files`);
    },
    downloadRunFile: (projectId, runId, fileId) => {
        return axios_instance.get(`/${projectId}/run/${runId}/file?file_id=${fileId}`)
    },
    deleteRun: (projectId, runId) => {
        return axios_instance.delete(`/${projectId}/run/${runId}`)
    }
};

export default RunService;