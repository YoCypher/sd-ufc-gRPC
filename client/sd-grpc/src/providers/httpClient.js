import axios from 'axios'

const httpClient = axios.create({
  baseURL: 'http://localhost:8080/webservlet/api'
})

export default httpClient
