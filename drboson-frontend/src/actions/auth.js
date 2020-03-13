import axios_instance from '../axios/axios-config'

const AuthenticationService = {
    registerUser: (userData) => {
        return axios_instance.post('/auth/join', userData);
    }
};

export default AuthenticationService;