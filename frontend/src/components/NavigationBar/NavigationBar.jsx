import Square from "./Square.jsx";
import Profile from "./Profile.jsx";
import "./NavigationBarStyles.css"
import {Link} from "react-router-dom";

export default function NavigationBar() {
  return (
      <div className="nav-bar">
        <Square>
          <Profile />
        </Square>
        <Square>
          <Link to={"/"} className="nav-btn">Главная</Link>
          <div className="nav-btn">Смотреть позже</div>
          <div className="nav-btn">Любимые</div>
          <div className="nav-btn">Обзор</div>
        </Square>
        <Square>
          <div className="nav-btn">Комнаты</div>
          <div className="nav-btn">Друзья</div>
          <div className="nav-btn">Мессенджер</div>
        </Square>
        <Square>
          <Link to={"/logout"}>
            <div className="nav-btn">Выход</div>
          </Link>
        </Square>
      </div>
  )
}