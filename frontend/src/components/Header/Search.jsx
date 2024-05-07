import "./Search.css"
import SearchLoop from "../../assets/Notifications/SearchLoop.svg"

export default function Search() {
  return (
    <div className="header-search">
      <input className={"header-search__input"} type="text" placeholder="Поиск по фильмам, сериалам и т.д." required/>
      <img className={"header-search__loop"} src={SearchLoop} alt="Поисковая лупа"/>
</div>
)
}