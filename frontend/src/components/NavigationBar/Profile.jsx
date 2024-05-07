import React, { useState, useEffect } from 'react';
import profileIconDefault from '../../assets/profileIconDefault.svg';

export default function Profile() {
  // Шаг 1: Используйте useState для хранения значения username
  const [username, setUsername] = useState('');

  // Шаг 2: Загрузите значение username из localStorage при монтировании компонента
  useEffect(() => {
    const storedUsername = JSON.parse(localStorage.getItem('token')).username;

    if (storedUsername) {
      setUsername(storedUsername);
    }
  }, []);


  return (
    <section className="user">
      <img className="user__profile" src={profileIconDefault} alt="Иконка пользователя"/>
      <h2 className="user__name">{username}</h2>
      <div className="user__line"/>
    </section>
  );
}
