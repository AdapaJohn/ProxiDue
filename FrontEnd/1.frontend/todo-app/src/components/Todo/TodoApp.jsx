import {
  BrowserRouter,
  Link,
  Navigate,
  Route,
  Routes,
  useNavigate,
  useParams,
} from "react-router-dom";
import "./TodoApp.css";
import LoginComponent from "./LoginComponent";
import WelcomeComponent from "./WelcomeComponent";
import LogOutComponent from "./LogOutComponent";
import TodoListCommponent from "./TodoListCommponent";
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
import ErrorComponent from "./ErrorComponent";
import AuthProvider, { useAuth } from "./security/AuthContext";
import TodoComponent from "./TodoComponent";

function AuthenticationRouter({ children }) {
  const authContext = useAuth();

  if (authContext.isAuthenticated) return children;

  return <Navigate to="/" />
}

export default function TodoApp() {
  return (
    <div className="TodoApp">
      <AuthProvider>
        <BrowserRouter>
          <HeaderComponent />
          <Routes>
            <Route path="/" element={<LoginComponent />} />
            <Route path="/login" element={<LoginComponent />} />
            <Route path="/logout" element={
              <AuthenticationRouter>
              <LogOutComponent />
              </AuthenticationRouter>
              } />
            <Route path="/welcome/:username" element={
              <AuthenticationRouter>
              <WelcomeComponent />
              </AuthenticationRouter>
              } />
            <Route path="/todos" element={
              <AuthenticationRouter>
              <TodoListCommponent />
              </AuthenticationRouter>
              } />
              <Route path="/todo/:id" element={
              <AuthenticationRouter>
              <TodoComponent />
              </AuthenticationRouter>
              } />
            <Route path="*" element={<ErrorComponent />} />
          </Routes>
          {/* <FooterComponent /> */}
        </BrowserRouter>
      </AuthProvider>
    </div>
  );
}
