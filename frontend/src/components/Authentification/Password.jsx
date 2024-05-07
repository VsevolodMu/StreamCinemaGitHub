const Password = ({inputRef}) => {
  return (
      <input type="password" placeholder="Пароль" name="example" ref={inputRef} minLength={5} maxLength={64} required/>
      );
};
export default Password;