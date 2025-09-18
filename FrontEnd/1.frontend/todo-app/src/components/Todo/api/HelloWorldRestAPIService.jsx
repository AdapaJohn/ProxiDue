import { apiClient } from "./ApiClient";

// export function retrieveHelloWorld() {
//   return axios.get(`http://localhost:8080/hello-world-bean`);
// }

// const apiClient = axios.create({
//   baseURL: "http://localhost:8080",
// });

//Another Way (Simple)
export const retrieveHelloWorld = () => apiClient.get(`/hello-world-bean`);

export const retrieveHelloWorldParam = (username, token) =>
  apiClient.get(
    `/hello-world/path-variable/${username}`
    //   , {
    //   headers: {
    //     //Preflight access request doesnt pass access control check
    //     Authorization: token,
    //   },
    // }
  );
