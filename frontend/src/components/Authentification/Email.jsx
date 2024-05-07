const Email = ({inputRef}) => {
  return (
    <input className={"email-input"} type="email" placeholder="Email" ref={inputRef} required/>
  )
}
export default Email;