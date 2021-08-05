package lifestudio.backend.domain.user.service;

import lifestudio.backend.domain.user.exception.EmailDuplicateException;
import lifestudio.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final UserRepository userRepository;

    private final JavaMailSender javaMailSender;

    public void sendEmail(HttpSession session, String email){

        Boolean existUser = userRepository.findByEmail(email).isPresent();

        if (existUser){
            throw new EmailDuplicateException(email);
        } else {
            Random random = new Random(System.currentTimeMillis());
            int result = 100000 + random.nextInt(900000);
            try {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                mimeMessage.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email,"user","UTF-8"));
                mimeMessage.setSubject("인생사진관 이메일 인증 안내입니다.");
                String htmlContent = " <div style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif'; width: 540px; height: 600px; border-top: 4px solid #FF7300; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">\n" +
                        "\t<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">\n" +
                        "\t\t<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">인생사진관</span><br />\n" +
                        "\t\t<span style=\"color: #FF7300;\">메일인증</span> 안내입니다.\n" +
                        "\t</h1>\n" +
                        "\t<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">\n" +
                        "\t\t안녕하세요.<br />\n" +
                        "\t\t인생사진관에 가입해 주셔서 진심으로 감사드립니다.<br />\n" +
                        "\t\t아래 <b style=\"color: #FF7300;\">'인증 코드'</b> 를 복사한 뒤, 회원 가입창에 입력해주세요.<br />\n" +
                        "\t\t감사합니다.\n" +
                        "\t</p>\n" +
                        "\t<p style=\"font-size: 16px; margin: 40px 5px 20px; line-height: 28px;\">\n" +
                        "\t\t인증 코드: <br />\n" +
                        "\t\t<span style=\"font-size: 24px;\">"+ result +"</span>\n" +
                        "\t</p>\n" +
                        "\t<div style=\"border-top: 1px solid #DDD; padding: 5px;\">\n" +
                        "\t</div>\n" +
                        "</div>";
                mimeMessage.setText(htmlContent, "UTF-8", "html");
                javaMailSender.send(mimeMessage);
                session.setAttribute(email, result);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean emailCertification(HttpSession session, String email, int inputCode){
        try {
            int generateCode = (int) session.getAttribute(email);

            if (generateCode == inputCode){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }
}
