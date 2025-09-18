import { apiClient } from "./ApiClient";

//http://localhost:8080/users/in28minutes/todos
export const retrieveAllTodos = (username) =>
  apiClient.get(`/users/${username}/todos`);

export const retrieveATodo = (username, id) =>
  apiClient.get(`/users/${username}/todos/${id}`);

export const deleteTodo = (username, id) =>
  apiClient.delete(`/users/${username}/todos/${id}`);

export const updateTodo = (username, id,todo) =>
  apiClient.put(`/users/${username}/todos/${id}`,todo);

export const createTodo = (username, todo) =>
  apiClient.post(`/users/${username}/todos`,todo);