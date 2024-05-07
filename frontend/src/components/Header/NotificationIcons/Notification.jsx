import NotificationBell from "./NotificationBell.jsx";
import NotificationMessage from "./NotificationMessage.jsx";
import NotificationSettings from "./NotificationSettings.jsx";
import "./Notification.css"

export default function Notification() {
  return (
    <div className="notification-wrapper">
      <div className="notification-wrapper__icons notification-wrapper__bell">
        <NotificationBell/>
      </div>
      <div className="notification-wrapper__icons notification-wrapper__message">
        <NotificationMessage/>
      </div>
      <div className="notification-wrapper__icons notification-wrapper__settings">
        <NotificationSettings/>
      </div>
    </div>
  )
}