/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Yuu
 */
public class Mailer implements Serializable {

    private static final String SENDEREMAIL = "datbaigiuxe@gmail.com";
    private static final String SENDERPASS = "1234!@#$";
    private static final String GMAILSMTP = "smtp.gmail.com";
    private static final String GMAILPORT = "465";

    public static void send(String to, String subject, String msg) {

        Properties props = new Properties();
        props.put("mail.smtp.host", GMAILSMTP);
        props.put("mail.smtp.socketFactory.port", GMAILPORT);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", GMAILPORT);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDEREMAIL, SENDERPASS);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject, "UTF-8");
            message.setText(msg, "UTF-8", "html");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void sendLockedUserMail(String to, String phone) {
        String subject = "PARKING ONLINE - KHOÁ TÀI KHOẢN";
        String msg = "Tài khoản " + phone + " đã bị khoá. Vui lòng liên hệ quản trị viên nếu có thắc mắc.<br/>";
        msg += "Thư này được gửi từ hệ thống tự động. Vui lòng không trả lời lại.";
        send(to, subject, msg);
    }

    public static void sendUnlockedUserMail(String to, String phone) {
        String subject = "PARKING ONLINE - MỞ KHOÁ TÀI KHOẢN";
        String msg = "Tài khoản " + phone + " đã được mở khoá.<br/>";
        msg += "Thư này được gửi từ hệ thống tự động. Vui lòng không trả lời lại.";
        send(to, subject, msg);
    }

    public static void sendLockedParkMail(String to, String parkID, String parkName) {
        String subject = "PARKING ONLINE - BÃI XE BỊ KHOÁ";
        String msg = "Bãi xe " + parkID + " - " + parkName + " đã khoá. Các tài khoản thuộc bãi xe này sẽ không thể truy cập được. Vui lòng liên hệ quản trị viên nếu có thắc mắc.<br/>";
        msg += "Thư này được gửi từ hệ thống tự động. Vui lòng không trả lời lại.";
        send(to, subject, msg);
    }

    public static void sendUnlockedParkMail(String to, String parkID, String parkName) {
        String subject = "PARKING ONLINE - BÃI XE BỊ KHOÁ";
        String msg = "Bãi xe " + parkID + " - " + parkName + " đã được mở khoá.<br/>";
        msg += "Thư này được gửi từ hệ thống tự động. Vui lòng không trả lời lại.";
        send(to, subject, msg);
    }

    public static void sendBookingLockedPark(String to, String parkName) {
        String subject = "PARKING ONLINE - HUỶ ĐẶT XE";
        String msg = "Bãi xe " + parkName + " đã không còn hoạt động. Lượt đặt xe của bạn tại bãi sẽ bị huỷ. Chân thành xin lỗi.<br/>";
        msg += "Thư này được gửi từ hệ thống tự động. Vui lòng không trả lời lại.";
        send(to, subject, msg);
    }

    public static void sendRecoveryMail(String to, String link) {
        link = "<a href='" + link + "'>Nhấn vào đây</a>";
        String subject = "PARKING ONLINE - KHÔI PHỤC MẬT KHẨU";
        String msg = "Bạn đã gửi yêu cầu khôi phục mật khảu. Vui lòng " + link + " để khôi phục. <br/>";
        msg += "Thư này được gửi từ hệ thống tự động. Vui lòng không trả lời lại.";
        send(to, subject, msg);
    }

    public static void sendNewPassMail(String to, String pass) {
        String subject = "PARKING ONLINE - MẬT KHẨU MỚI";
        String msg = "Đây là mật khẩu mới theo yêu cầu khôi phục: " + pass + "<br/>Vui lòng đổi mật khẩu sớm nhất có thể.<br/>";
        msg += "Thư này được gửi từ hệ thống tự động. Vui lòng không trả lời lại.";
        send(to, subject, msg);
    }

    public static void sendReplyFeedbackMail(String to, String title, String time, String mess, String reply) {
        String subject = "PARKING ONLINE - HỒI ĐÁP FEEDBACK";
        String msg = "Hồi âm cho Phản hồi của bạn tới hệ thống " + title + " vào lúc " + time + " với nội dung bạn đã gửi:<br/>\"";
        msg += mess + "\"<br/><br/>";
        msg += "CHI TIẾT NỘI DUNG HỒI ÂM.<br/>\"";
        msg += reply + "\"<br/><br/>";
        msg += "Thư này được gửi từ hệ thống tự động. Vui lòng không trả lời lại.";
        send(to, subject, msg);
    }

}
