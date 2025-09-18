// import { apiClient } from "./ApiClient";

// export const sendOrderEmail = (emailData, token) => {
//     return apiClient.post(`/api/orders/send-notification`, emailData, {
//         headers: {
//             'Authorization': token,
//             'Content-Type': 'application/json'
//         }
//     });
// };

// export const testEmail = () => {
//     return apiClient.get(`/test/email`);
// };

import { apiClient } from "./ApiClient";

export const sendOrderEmail = (emailRequest) =>
  apiClient.post("/api/orders/send-notification", emailRequest);

export const testEmail = () => apiClient.get("/test/email");
