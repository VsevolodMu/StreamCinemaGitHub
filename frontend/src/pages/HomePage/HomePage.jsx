import "./HomePage.css"
import NavigationBar from "../../components/NavigationBar/NavigationBar.jsx";
import Header from "../../components/Header/Header.jsx";
import FilmSection from "../../components/FilmSection/FilmSection.jsx";
import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080/api/v1";

export default function HomePage() {
  return (
    <div className="home-page-wrapper">
      <div className="home-page-wrapper__first-child">
        <NavigationBar/>
      </div>
      <div className="home-page-wrapper__second-child">
        <Header/>
      </div>
      <div className="home-page-wrapper__third-child">
        <FilmSection text="Рекомендуем к просмотру"/>
      </div>

      {/*<div className="home-page-wrapper__empty">

      </div>

      <div className="home-page-wrapper__fourth-child">
        <FilmSection text="Топ 10 за месяц"/>
      </div>*/}
    </div>
  )
}