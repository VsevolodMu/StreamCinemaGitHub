import "./CreateRoomPage.css";
import NavigationBar from "../../components/NavigationBar/NavigationBar.jsx";
import Header from "../../components/Header/Header.jsx";
import Input from "../../components/CreateRoomInput/Input.jsx";
import {Link, useParams} from "react-router-dom";
import {useEffect, useRef, useState} from "react";
import axios from "axios";

const tkn = JSON.parse(localStorage.getItem('token'));

export default function CreateRoomPage() {
  const {id} = useParams();
  const [film, setFilm] = useState({});

  const roomNameInput = useRef();
  const dateInput = useRef();
  const descriptionInput = useRef();


  const buildCreateRoomData = (roomNameInput, dateInput, descriptionInput) => {
    const formData = new FormData();
    formData.append('username', roomNameInput);
    formData.append('password', dateInput);
    formData.append('password', descriptionInput);

    // Создаем объект JavaScript из FormData
    let dataObject = {};
    formData.forEach((value, key) => {
      dataObject[key] = value;
    });

    // Преобразуем объект в строку JSON
    const jsonData = JSON.stringify(dataObject);

    console.log(jsonData);

    return jsonData;
  }

  const handleSubmit = async (event) => {
    event.preventDefault();


    try {
      let response;
      console.log("Создаём комнату");
      const jsonData = buildCreateRoomData(roomNameInput.current.value, dateInput.current.value, descriptionInput.current.value);
      console.log("jsonData:", jsonData);

      response = await axios.post('http://localhost:8080/api/v1/auth/authenticate', jsonData, {
        headers: {
          'Content-Type': 'application/json'
        }
      });

      console.log('Login successful:', response.data);
      setToken(response.data);
      navigate("/", {replace: true});
    } catch (error) {
      alert('Неверно введены данные')

    }

    localStorage.setItem('username', '');

  };

  useEffect(() => {
    axios.get(`/films/${id}`, {
      headers: {
        'Authorization': 'Bearer ' + tkn.access_token
      }
    })
      .then(response => setFilm(response.data))
      .catch(err => console.log(err));
  }, []);

  return (
    <div className="home-page-wrapper">
      <div className="home-page-wrapper__first-child">
        <NavigationBar/>
      </div>

      <div className="home-page-wrapper__second-child">
        <Header/>
      </div>

      <div className="create-room-page-wrapper__third-child">
        <div className="create-room-title">
          {film.title}
        </div>
        <img className={"create-room-img"} src={film.cover} alt="Фильм)"/>
        <div className="create-room-description">
          {film.description}
        </div>
      </div>

      <form onSubmit={handleSubmit} className="create-room-page-wrapper__fifth-child">
        <Input placeholder="Название комнаты" inputRef={roomNameInput}/>
        <Input placeholder="Время начала сеанса" inputRef={dateInput}/>
        <Input placeholder="Описание комнаты" inputRef={descriptionInput}/>
        <Link to={`/room/${id}`}>
          <button className="create-room-button">Создать</button>
        </Link>
      </form>
    </div>
  )
}