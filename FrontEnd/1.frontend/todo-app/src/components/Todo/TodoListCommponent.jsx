import { useEffect, useState } from "react";
import { retrieveAllTodos , deleteTodo } from "./api/TodoRestAPIService";
import { useAuth } from "./security/AuthContext";
import { useNavigate } from "react-router-dom";

function TodoListCommponent() {
  // const today = new Date();
  // const targetDate = new Date(
  //   today.getFullYear() + 1,
  //   today.getMonth(),
  //   today.getDay()
  // );
  const [todos, setTodos] = useState([]);

  const [message,setMessage] = useState(null)

  const AuthContext = useAuth()

  const username = AuthContext.username

  const navigate = useNavigate()
  // const todos = [
  //   { id: 1, description: "Learn AWS", done: false, targetDate: targetDate },
  //   { id: 2, description: "Learn DevOps", done: false, targetDate: targetDate },
  //   { id: 3, description: "Learn Docker", done: false, targetDate: targetDate },
  // ];

  useEffect(() => refreshTodos(), []);

  function refreshTodos() {
    retrieveAllTodos(username)
      .then((response) => {
        setTodos(response.data);
        //console.log(response.data)
      })
      .catch((error) => console.log(error));
  }

  function DeleteTodo(id) {
    console.log('Clicked '+ id)
    deleteTodo(username,id)
    .then(
      () => {
        //1: Display Message
        setMessage(`Delete Todo of id ${id} successful`);
        refreshTodos();
        //2: Update Message
      }
    )
    .catch((error) => console.log(error));
  }

  function UpdateTodo(id) {
    console.log('Clicked '+ id)
    navigate(`/todo/${id}`)
  }

  function addNewTodo() {
    navigate(`/todo/-1`)
  }

  return (
    <div className="container">
      <h1>Here is List Todos</h1>
      <div>Make Todo's for ur Convience</div>
      {message && <div className="alert alert-warning">{message}</div>}
      <div>
        <table className="table">
          <thead>
            <tr>
              {/* <td>Id</td> */}
              <th>Description</th>
              <th>Is Done?</th>
              <th>Target Date</th>
              <th>Delete</th>
              <th>Update</th>
            </tr>
          </thead>
          <tbody>
            {todos.map((todo) => (
              <tr key={todo.id}>
                {/* <td>{todo.id}</td> */}
                <td>{todo.description}</td>
                <td>{todo.done.toString()}</td>
                {/* <td>{todo.targetDate.toDateString()}</td> */}
                <td>{todo.targetDate.toString()}</td>
                <td><button className="btn btn-warning" onClick={() => DeleteTodo(todo.id)}>Delete</button></td>
                <td><button className="btn btn-success" onClick={() => UpdateTodo(todo.id)}>Update</button></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div className="btn btn-success m-3 " onClick={addNewTodo}>Add New Todo</div>
    </div>
  );
}

export default TodoListCommponent;
