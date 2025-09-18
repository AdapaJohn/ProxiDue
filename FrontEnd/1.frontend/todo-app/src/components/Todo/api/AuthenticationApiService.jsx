  import { apiClient } from "./ApiClient";
  
    //Token : "Basic bmFubmE6YW1tYQ=="
  export const executeBasicAuthenticationService = (token) =>
  apiClient.get(`/basicAuth`, {
    headers: {
      Authorization: token,
    },
  });

   export const executeJwtAuthenticationService = (username,password) =>
  apiClient.post(`/authenticate`, {username,password});