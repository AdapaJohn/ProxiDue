// import { createContext, useState, useContext } from "react";
// import { executeJwtAuthenticationService } from "../api/AuthenticationApiService";
// import { apiClient } from "../api/ApiClient";

// export const AuthContext = createContext();

// export const useAuth = () => useContext(AuthContext);

// export default function AuthProvider({ children }) {
//   const [isAuthenticated, setAuthenticated] = useState(false);
//   const [username, setUsername] = useState(null);

//   const [token, setToken] = useState(null);

//   // async function login(username, password) {
//   //   const baToken = "Basic " + window.btoa(username + ":" + password);

//   //   try {
//   //     const response = await executeBasicAuthenticationService(baToken);

//   //     if (response.status == 200) {
//   //       setAuthenticated(true);
//   //       setUsername(username);
//   //       setToken(baToken);

//   //       apiClient.interceptors.request.use((config) => {
//   //         console.log("Intercepting and adding token to all headers");
//   //         config.headers.Authorization = baToken;
//   //         return config;
//   //       });
//   //       return true;
//   //     } else {
//   //       logout();
//   //       return false;
//   //     }
//   //   } catch (error) {
//   //     logout();
//   //     return false;
//   //   }
//   // }

//   async function login(username, password) {

//     try {
//       const response = await executeJwtAuthenticationService(username,password);

//       if (response.status == 200) {

//         const jwtToken = 'Bearer ' + response.data.token

//         setAuthenticated(true);
//         setUsername(username);
//         setToken(jwtToken);

//         apiClient.interceptors.request.use((config) => {
//           console.log("Intercepting and adding token to all headers");
//           config.headers.Authorization = jwtToken;
//           return config;
//         });
//         return true;
//       } else {
//         logout();
//         return false;
//       }
//     } catch (error) {
//       logout();
//       return false;
//     }
//   }

//   function logout() {
//     setAuthenticated(false);
//     setToken(false);
//     setUsername(null);
//   }

//   return (
//     <AuthContext.Provider
//       value={{ isAuthenticated, login, logout, username, token }}
//     >
//       {children}
//     </AuthContext.Provider>
//   );
// }


import { createContext, useState, useContext } from "react";
import { executeJwtAuthenticationService } from "../api/AuthenticationApiService";
import { apiClient } from "../api/ApiClient";
import { jwtDecode } from "jwt-decode";
import axios from "axios";

// You may need to implement this on your backend
export const executeJwtRefreshService = (refreshToken) =>
  apiClient.post(`/refresh-token`, { refreshToken });

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({ children }) {
  const [isAuthenticated, setAuthenticated] = useState(false);
  const [username, setUsername] = useState(null);
  const [token, setToken] = useState(null);
  const [refreshToken, setRefreshToken] = useState(null);
  const [tokenExpiry, setTokenExpiry] = useState(null);

  // Helper to decode and set expiry
 function setTokenWithExpiry(jwtToken) {
  setToken(jwtToken);
  try {
    const decoded = jwtDecode(jwtToken.replace('Bearer ', ''));
    setTokenExpiry(decoded.exp * 1000);
  } catch (err) {
    setTokenExpiry(null);
  }
}

  async function login(username, password) {
    try {
      const response = await executeJwtAuthenticationService(username, password);

      if (response.status === 200) {
        const jwtToken = 'Bearer ' + response.data.token;
        setAuthenticated(true);
        setUsername(username);
        setTokenWithExpiry(jwtToken);
        // if backend returns refresh token
        if (response.data.refreshToken) setRefreshToken(response.data.refreshToken);

        // Set up axios interceptor
        setupAxiosInterceptor(jwtToken);
        return true;
      } else {
        logout();
        return false;
      }
    } catch (error) {
      logout();
      return false;
    }
  }

  function logout() {
    setAuthenticated(false);
    setToken(null);
    setUsername(null);
    setRefreshToken(null);
    setTokenExpiry(null);
  }

  // Axios interceptor for auto-refresh
  function setupAxiosInterceptor(jwtToken) {
    apiClient.interceptors.request.use(async (config) => {
      // Check expiry (within 60 seconds)
      if (tokenExpiry && tokenExpiry - Date.now() < 60000) {
        // Call refresh endpoint
        if (refreshToken) {
          try {
            const refreshResponse = await executeJwtRefreshService(refreshToken);
            const newJwtToken = 'Bearer ' + refreshResponse.data.token;
            setTokenWithExpiry(newJwtToken);
            config.headers.Authorization = newJwtToken;
            return config;
          } catch (err) {
            logout();
            throw err;
          }
        } else {
          logout();
        }
      }
      config.headers.Authorization = token || jwtToken;
      return config;
    });
  }

  // Set up interceptor on mount if token exists (useEffect could be added)

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, login, logout, username, token }}
    >
      {children}
    </AuthContext.Provider>
  );
}
// import { createContext, useState, useContext } from "react";
// import { executeBasicAuthenticationServixe } from "../api/HelloWorldRestAPIService";
// import { apiClient } from "../api/ApiClient";

// export const AuthContext = createContext();

// export const useAuth = () => useContext(AuthContext);

// export default function AuthProvider({ children }) {
//   //const [number, setNumber] = useState(10);

//   const [isAuthenticated, setAuthenticated] = useState(false);
//   const [username, setUsername] = useState(null);

//     const [token, setToken] = useState(null);

//   //const valueToBeShared = { number, isAuthenticated, setAuthenticated } - Simple Use

//   //setInterval(() => setNumber(number + 1), 10000);

//   // function login(username, password) {
//   //   if (username === "in28minutes" && password === "dummy") {
//   //     setAuthenticated(true);
//   //     setUsername(username);
//   //     return true;
//   //   } else {
//   //     setAuthenticated(false);
//   //     setUsername(null);
//   //     return false;
//   //   }
//   // }

//   async function login(username, password) {
//     const baToken = "Basic " + window.btoa(username + ":" + password);

//     try {
//       const response = await executeBasicAuthenticationServixe(baToken);
//       //   .then((response) => console.log("2: "+response))
//       //   .catch((error) => console.log(error));

//       // console.log("1: test")

//       // setAuthenticated(false);

//       if (response.status==200) {
//         setAuthenticated(true);
//         setUsername(username);
//         setToken(baToken)

//         apiClient.interceptors.request.use(
//           (config) => {
//             console.log('Intercepting and adding token to all headers')
//             config.headers.Authorization = baToken
//             return config
//           }
//         )
//         return true;
//       } else {
//         logout()
//         return false;
//       }
//     } catch (error) {
//       logout()
//       return false;
//     }
//   }

//   function logout() {
//     setAuthenticated(false);
//     setToken(false)
//     setUsername(null);
//   }

//   return (
//     // <AuthContext.Provider value={{ number, isAuthenticated, setAuthenticated }}>
//     <AuthContext.Provider value={{ isAuthenticated, login, logout, username ,token}}>
//       {children}
//     </AuthContext.Provider>
//   );
// }
