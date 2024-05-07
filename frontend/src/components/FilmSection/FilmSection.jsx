import "./FilmSection.css"
import axios from "axios";
import {useEffect, useState} from "react";
import {Link} from "react-router-dom";

const tkn = JSON.parse(localStorage.getItem('token'));

export default function FilmSection(props) {
  const [films, setFilms] = useState([])

  useEffect(() => {
    axios.get("/films",{
      headers: {
        'Authorization': 'Bearer ' + tkn.access_token
      }
    })
         .then(response => setFilms(response.data))
         .catch(err => console.log(err))
  }, []);

  return (
    <>
      <h2 className="card-player-text">{props.text}</h2>

      <div className="card-player-recommendation-wrapper">
        {films.map(film => (
          <Link key={film.id} to={`/create-room/${film.id}`}>
            <img className={"film-img"} src={film.cover} alt="Фильм)"/>
          </Link>
          )
        )}
      </div>
    </>

  )
}