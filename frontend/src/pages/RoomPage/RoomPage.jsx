import "./RoomPage.css"
import NavigationBar from "../../components/NavigationBar/NavigationBar.jsx";
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";

const tkn = JSON.parse(localStorage.getItem('token'));

export default function RoomPage() {
  const {id} = useParams();
  const [film, setFilm] = useState({});
  const [filmUrl, setFilmUrl] = useState("");

  useEffect(() => {
    axios.get(`/films/${id}`,{
      headers: {
        'Authorization': 'Bearer ' + tkn.access_token
      }
    })
      .then(response => setFilm(response.data))
      .catch(err => console.log(err));
  }, []);

  useEffect(() => {
    film.path && axios.get("http://localhost:8080/api/v1/video/" + film.path,{
      headers: {
        'Authorization': 'Bearer ' + tkn.access_token
      },
      responseType: 'blob'
    }).then(response =>{
      setFilmUrl(URL.createObjectURL(response.data))
    })

  }, [film.path]);

  return (
    <div className="home-page-wrapper room-page-wrapper">
      <div className="home-page-wrapper__first-child">
        <NavigationBar/>
      </div>

      <div className="room-page-wrapper__second-child">
        {film.title}
      </div>

      {filmUrl !== "" &&
        <video className="room-page-wrapper__third-child" controls width="auto" height="100%">
          <source type="video/mp4" src={filmUrl}/>
          Ваш браузер не поддерживает встроенные видео :(
        </video>
      }
    </div>
  )
}