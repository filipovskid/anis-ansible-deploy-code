import axios from 'axios';

const axios_instance = axios.create({
    baseURL: 'http://localhost:8080/',
    headers: { 'Access-Control-Allow-Origin': '*' },
    withCredentials: true
});

export default axios_instance
