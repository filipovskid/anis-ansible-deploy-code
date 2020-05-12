import axios_instance from '../axios/axios-config'
import qs from 'qs';

const RunService = {
    // fetchProjects: () => {
    //     return axios_instance.get('/project');
    // },
    // fetchProject: (projectId) => {
    //     return axios_instance.get(`/project/${projectId}`);
    // },
    createRun: (projectId, run) => {
        const formParams = qs.stringify(run);

        return axios_instance.post(`/${projectId}/run`, formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
    }
};

export default RunService;