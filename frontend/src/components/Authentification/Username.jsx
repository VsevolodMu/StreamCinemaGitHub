const Username = ({inputRef}) => {
  return (
    <input type="text" placeholder="Имя пользователя" ref={inputRef} minLength={5} maxLength={48} required/>
  )
}
export default Username;