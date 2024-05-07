const UniversalInput = ({inputRef}) => {
  return (
    <input type="text" placeholder="Email, имя пользователя или телефон" name="example" ref={inputRef} required/>
  )
}
export default UniversalInput;