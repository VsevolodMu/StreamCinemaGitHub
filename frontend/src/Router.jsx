import {createBrowserRouter} from "react-router-dom";
import HomePage from "./pages/HomePage/HomePage.jsx";
import CreateRoomPage from "./pages/CreateRoomPage/CreateRoomPage.jsx";
import RoomPage from "./pages/RoomPage/RoomPage.jsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <HomePage/>
  },
  {
    path: "/create-room/:id",
    element: <CreateRoomPage/>
  },
  {
    path: "/room/:id",
    element: <RoomPage/>
  }
])

export default router