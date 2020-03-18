
import axios_instance from '../axios/axios-config'
import qs from 'qs';

const ProjectService = {
    fetchProjects: () => {
        return axios_instance.get('/project');
    },
    createProject: (project) => {
        const formParams = qs.stringify(project);

        return axios_instance.post('/project/create', formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
    }
};

export default ProjectService;