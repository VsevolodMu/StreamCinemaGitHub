import './App.css'
import AuthProvider from "./provider/authProvider.jsx";
import Routes from "./routes/index.jsx";

export default function App() {
  return (
    <>
      <AuthProvider>
        <Routes />
      </AuthProvider>
    </>
  )
}
