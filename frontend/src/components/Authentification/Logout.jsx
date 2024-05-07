import { useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../provider/authProvider.jsx";

const Logout = () => {
  const { setToken } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    setToken();
    navigate("/", { replace: true });
  };

  useEffect(() => {
    handleLogout();
  }, []);

  return <>Logout Page</>;
};

export default Logout;
