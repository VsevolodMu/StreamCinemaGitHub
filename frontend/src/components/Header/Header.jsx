import Search from "./Search.jsx";
import "./Header.css"
import Notification from "./NotificationIcons/Notification.jsx";

export default function Header() {
  return (
    <div className="header-wrapper">
      <h1>CinemaStream.com</h1>
      <Search/>
      <Notification/>
    </div>
  )
}