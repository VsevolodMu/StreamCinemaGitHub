import googleIcon from "../../assets/AuthentificationIcons/googleIcon.png";
import facebookIcon from "../../assets/AuthentificationIcons/facebookIcon.png";
import githubIcon from "../../assets/AuthentificationIcons/githubIcon.png";
import linkedInIcon from "../../assets/AuthentificationIcons/linkedInIcon.png";

export default function SocialIcons() {
  return (
    <div className="social-icons">
      <a href="#" className="icon"><img src={googleIcon} alt="Авторизация через гугл"/></a>
      <a href="#" className="icon"><img src={facebookIcon} alt="Авторизация через фейсбук"/></a>
      <a href="#" className="icon"><img src={githubIcon} alt="Авторизация через гитхаб"/></a>
      <a href="#" className="icon"><img src={linkedInIcon} alt="Авторизация через линкед ин"/></a>
    </div>
  )
}