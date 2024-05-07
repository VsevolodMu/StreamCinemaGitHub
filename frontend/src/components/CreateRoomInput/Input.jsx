import "./Input.css"
export default function Input(props) {
  return (
    <input className={"room-input"} type="text" placeholder={props.placeholder} required/>
  )
}