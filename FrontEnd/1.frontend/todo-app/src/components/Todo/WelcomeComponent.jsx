import { Link, useParams } from "react-router-dom";
import "./TodoApp.css";
// import axios from "axios";
import { useState } from "react";
import {
  // retrieveHelloWorld,
  retrieveHelloWorldParam,
} from "./api/HelloWorldRestAPIService";
import { useAuth } from "./security/AuthContext";

function WelcomeComponent() {
  // const param = useParams()
  // console.log(param.username)
  const { username } = useParams();
  //console.log(username);
  const [message, setmessage] = useState(null);

  const contextAuth = useAuth()

  function callHelloWorldRestAPI() {
    console.log("callHelloWorldRestAPI");
    //   axios
    //     .get(`http://localhost:8080/hello-world`)
    //     .then((response) => successfulResponse(response))
    //     .catch((error) => errorResponse(error))
    //     .finally(() => console.log("cleanup"));
    // }

    // axios
    //     .get(`http://localhost:8080/hello-world-bean`)
    //     .then((response) => successfulResponse(response))
    //     .catch((error) => errorResponse(error))
    //     .finally(() => console.log("cleanup"));

    //Direct Way
    // retrieveHelloWorld()
    //   .then((response) => successfulResponse(response))
    //   .catch((error) => errorResponse(error))
    //   .finally(() => console.log("cleanup"));

    retrieveHelloWorldParam("John",contextAuth.token)
      .then((response) => successfulResponse(response))
      .catch((error) => errorResponse(error))
      .finally(() => console.log("cleanup"));
  }

  function successfulResponse(response) {
    console.log(response);
    //setmessage(response.data);
    setmessage(response.data.message);
  }

  function errorResponse(error) {
    console.log(error);
  }

  return (
    <div className="WelcomeComponent">
      <h1>Welcome Again Buddy {username}.</h1>
      <div>
        Here is your Todos - <Link to="/todos">My Todos</Link>
        {/* <a href="/todos">My Todos</a>  - It Refreshing page when opening*/}
        <div>
          <button
            className="btn btn-success m-5"
            onClick={callHelloWorldRestAPI}
          >
            Call Hello world
          </button>
        </div>
        <div className="text-info">{message}</div>
      </div>
    </div>
  );
}

export default WelcomeComponent;
