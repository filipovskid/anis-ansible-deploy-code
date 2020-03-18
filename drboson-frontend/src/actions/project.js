
import axios_instance from '../axios/axios-config'

const ProjectService = {
    fetchProjects: () => {
        return axios_instance.get('/project');
    }
};

export default ProjectService;