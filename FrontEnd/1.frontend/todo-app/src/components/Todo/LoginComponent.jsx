import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "./security/AuthContext";

function LoginComponent() {
  const [username, setUser] = useState("nanna");
  const [password, setPassword] = useState("amma");

  //const [success, setsuccessmessage] = useState(false);
  const [failure, setfailuremessage] = useState(false);

  const navigate = useNavigate();

  const authContext = useAuth();

  function HandleUserNameChange(event) {
    //console.log(event.target.value);
    setUser(event.target.value);
  }

  function HandlePasswordChange(event) {
    //console.log(event.target.value);
    setPassword(event.target.value);
  }

  async function HandleButton() {
    // console.log(username)
    // console.log(password)
    //   if (username === "Nanna@143" && password === "dummy") {
    //     authContext.setAuthenticated(true)
    //     console.log("success");
    //     setsuccessmessage(true);
    //     setfailuremessage(false);
    //     navigate(`/welcome/${username}`);
    //   } else {
    //     authContext.setAuthenticated(false)
    //     console.log("failure");
    //     setsuccessmessage(false);
    //     setfailuremessage(true);
    //   }
    // }

    if (await authContext.login(username, password)) {
      navigate(`/welcome/${username}`);
    } else {
      setfailuremessage(true);
    }
  }

  // function SuccessMessageFunction() {
  //   if (success) {
  //     return <div className="successmessage">Authentication Success</div>;
  //   }
  //   return null;
  // }

  // function ErrorMessageFunction() {
  //   if (failure) {
  //     return <div className="errormessage">Authentication Failed</div>;
  //   }
  //   return null;
  // }

  return (
    <div className="LoginComponent">
      {/* {success && <div className="successmessage">Authentication Success</div>} */}
      {failure && <div className="errormessage">Authentication Failed</div>}
      {/* <SuccessMessageFunction />
      <ErrorMessageFunction /> */}
      <h1>Time to Login!</h1>
      <div className="Loginform">
        <div>
          <label>UserName</label>
          <input
            type="text"
            name="username"
            value={username}
            onChange={HandleUserNameChange}
          />
        </div>
        <div>
          <label>Password</label>
          <input
            type="password"
            name="password"
            value={password}
            onChange={HandlePasswordChange}
          />
        </div>
        <div>
          <button type="button" name="login" onClick={HandleButton}>
            Submit
          </button>
        </div>
      </div>
    </div>
  );
}

export default LoginComponent;
