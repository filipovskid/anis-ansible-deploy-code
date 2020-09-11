import axios from 'axios';
import { config } from '../config/config.js';

const axios_instance = axios.create({
    baseURL: config.apiUrl,
    headers: { 'Access-Control-Allow-Origin': '*' },
    withCredentials: true
});

export default axios_instance
