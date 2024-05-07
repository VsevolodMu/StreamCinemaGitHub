import {useRef, useState} from 'react';
import "../../pages/SignInUpPage/SignInUp.css";
import SocialIcons from "./SocialIcons.jsx";
import Email from "./Email.jsx";
import Telephone from "./Telephone.jsx";
import Username from "./Username.jsx";
import Password from "./Password.jsx";
import UniversalInput from "./UniversalInput.jsx";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {useAuth} from "../../provider/authProvider.jsx";

export default function SignInUp() {
  const [isSignUpActive, setIsSignUpActive] = useState(false);

  const handleRegisterClick = async () => {
    setIsSignUpActive(true);
  };

  const handleLoginClick = async () => {
    setIsSignUpActive(false);
  };

  //for login
  const universalInput = useRef();
  const passwordLogin = useRef();

  //for registration

  const passwordRegistration = useRef();
  const username = useRef();
  const email = useRef();
  const telephone = useRef();

  const {setToken} = useAuth();
  const navigate = useNavigate();

  const buildFormDataForLogin = (universalInput, passwordInput) => {
    const formData = new FormData();
    formData.append('username', universalInput);
    formData.append('password', passwordInput);

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

  const buildFormDataForRegister = (username,password, email,  number ) => {
    const formData = new FormData();
    const timestamp = Math.floor(new Date().getTime());

    formData.append('username', username);
    formData.append('password', password);
    formData.append('email', email);
    formData.append('registrationDate', timestamp.toString());
    formData.append('lastLoginDate', 5334);
    formData.append('favoriteGenreID', -1);
    formData.append('profileInfo', 'Здесь пока что пусто');
    formData.append('number', number);
    formData.append('birth', '434');

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
      if (isSignUpActive) {
        // Регистрация
        console.log("Registration");
        const jsonData = buildFormDataForRegister(username.current.value, passwordRegistration.current.value, email.current.value, telephone.current.value);
        response = await axios.post('http://localhost:8080/api/v1/auth/register', jsonData, {
          headers: {
            'Content-Type': 'application/json'
          }
        });
        alert('Успешная регистрация');
        console.log('Registration successful:', response.data);
        setIsSignUpActive(false);

      } else {
        // Вход
        console.log("Vxod");
        const jsonData = buildFormDataForLogin(universalInput.current.value, passwordLogin.current.value);
        console.log("jsonData:", jsonData);

        response = await axios.post('http://localhost:8080/api/v1/auth/authenticate', jsonData, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        console.log('Login successful:', response.data);
        setToken(response.data);
        navigate("/", {replace: true});
      }
    } catch (error) {
      alert('Неверно введены данные')

    }

    localStorage.setItem('username', '');

  };

  return (
    <div className={`container ${isSignUpActive ? 'active' : ''}`} id="container">
      <div className="form-container sign-up">
        <form onSubmit={handleSubmit}>
          <h1>Создать аккаунт</h1>
          <SocialIcons/>
          <span>или используйте следующую форму</span>
          <Username inputRef={username}/>
          <Telephone inputRef={telephone}/>
          <Email inputRef={email}/>
          <Password inputRef={passwordRegistration}/>
          <button type="submit">Зарегистрироваться</button>
        </form>
      </div>
      <div className="form-container sign-in">
        <form onSubmit={handleSubmit}>
          <h1>Войти</h1>
          <SocialIcons/>
          <span>или используйте следующую форму</span>
          <UniversalInput inputRef={universalInput}/>
          <Password inputRef={passwordLogin}/>
          <a href="#">Забыли пароль?</a>
          <button type="submit">Войти</button>
        </form>
      </div>
      <div className="toggle-container">
        <div className={`toggle ${isSignUpActive ? 'right' : 'left'}`}>
          <div className="toggle-panel toggle-left">
            <h1>С возвращением!</h1>
            <p>Введите ваши персональные данные, чтобы использовать все функции сайта</p>
            <button className="hidden" onClick={handleLoginClick} id="login">Войти</button>
          </div>
          <div className="toggle-panel toggle-right">
            <h1>Привет, киноман!</h1>
            <p>Зарегистрируйтесь с вашими персональными данными, чтобы использовать все функции сайта</p>
            <button className="hidden" onClick={handleRegisterClick} id="register">Зарегистрироваться</button>
          </div>
        </div>
      </div>
    </div>
  );
}

