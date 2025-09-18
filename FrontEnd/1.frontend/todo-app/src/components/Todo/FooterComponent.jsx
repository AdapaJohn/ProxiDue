import { useAuth } from "./security/AuthContext";

function FooterComponent() {

  const authContext = useAuth()
  //const authContext = useContext(AuthContext);
  //console.log(`Footer Component - ${authContext.number}`);


  return (
    <footer className="footer">
      <div className="container">
        Copyrights on these app on @2003
      </div>
    </footer>
  );
}

export default FooterComponent