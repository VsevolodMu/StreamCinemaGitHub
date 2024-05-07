import { RouterProvider, createBrowserRouter } from "react-router-dom";
import { useAuth } from "../provider/authProvider.jsx";
import { ProtectedRoute } from "./ProtectedRoute";
import Login from "../pages/SignInUpPage/SignInUp.jsx";
import HomePage from "../pages/HomePage/HomePage.jsx";
import RoomPage from "../pages/RoomPage/RoomPage.jsx";
import Logout from "../components/Authentification/Logout.jsx";
import CreateRoomPage from "../pages/CreateRoomPage/CreateRoomPage.jsx";

const Routes = () => {
  const { token } = useAuth();

  // Define public routes accessible to all users
  const routesForPublic = [
  ];

  // Define routes accessible only to authenticated users
  const routesForAuthenticatedOnly = [
    {
      path: "/",
      element: <ProtectedRoute />, // Wrap the component in ProtectedRoute
      children: [
        {
          path: "",
          element: <HomePage/>,
        },
        {
          path: "create-room/:id",
          element: <CreateRoomPage/>
        },
        {
          path: "room/:id",
          element: <RoomPage/>
        },
        {
          path: "logout",
          element: <Logout/>,
        }
      ]
    }
  ];

  // Define routes accessible only to non-authenticated users
  const routesForNotAuthenticatedOnly = [
    {
      path: "/login",
      element: <Login/>,
    },
  ];

  // Combine and conditionally include routes based on authentication status
  const router = createBrowserRouter([
    ...routesForPublic,
    ...(!token ? routesForNotAuthenticatedOnly : []),
    ...routesForAuthenticatedOnly,
  ]);

  // Provide the router configuration using RouterProvider
  return <RouterProvider router={router} />;
};

export default Routes;
