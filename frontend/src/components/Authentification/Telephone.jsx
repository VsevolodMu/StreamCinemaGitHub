import InputMask from 'react-input-mask';

const Telephone = ({ inputRef }) => {
  return (
    <InputMask mask="+7 (999) 999-99-99" ref={inputRef}>
      {(inputProps) => <input {...inputProps} type="tel" placeholder="Номер телефона" required />}
    </InputMask>
  );
};

export default Telephone;
