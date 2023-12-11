package com.busticket.booking.service;

import com.busticket.booking.model.EmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;


@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    public void sendEmail(EmailRequest emailRequest) {
        try {
           MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(emailRequest.getTo());
            mimeMessageHelper.setSubject(emailRequest.getSubject());
            mimeMessageHelper.setFrom(emailRequest.getFrom());
            if(emailRequest.getFilePath() != null){
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(""));}
           // mimeMessageHelper.addAttachment();
            String message = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Booking Confirmation</title>\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                    "            line-height: 1.6;\n" +
                    "            margin: 0;\n" +
                    "            padding: 20px;\n" +
                    "            background-color: #f8f8f8;\n" +
                    "        }\n" +
                    "\n" +
                    "        .container {\n" +
                    "            max-width: 600px;\n" +
                    "            margin: 0 auto;\n" +
                    "            background-color: #fff;\n" +
                    "            padding: 20px;\n" +
                    "            border-radius: 8px;\n" +
                    "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                    "        }\n" +
                    "\n" +
                    "        h2 {\n" +
                    "            color: #333;\n" +
                    "        }\n" +
                    "\n" +
                    "        img {\n" +
                    "            width: 100%;\n" +
                    "            max-height: 200px;\n" +
                    "            object-fit: cover;\n" +
                    "            border-radius: 8px;\n" +
                    "            margin-bottom: 20px;\n" +
                    "        }\n" +
                    "\n" +
                    "        p {\n" +
                    "            color: #666;\n" +
                    "        }\n" +
                    "\n" +
                    "        ul {\n" +
                    "            color: #666;\n" +
                    "            margin: 10px 0;\n" +
                    "            padding-left: 20px;\n" +
                    "        }\n" +
                    "\n" +
                    "        li {\n" +
                    "            margin-bottom: 8px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .btn-confirm {\n" +
                    "            display: inline-block;\n" +
                    "            margin-top: 20px;\n" +
                    "            padding: 10px 20px;\n" +
                    "            background-color: #3498db;\n" +
                    "            color: #fff;\n" +
                    "            text-decoration: none;\n" +
                    "            border-radius: 5px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "    <div class=\"container\">\n" +
                    "\n" +
                    "        <h2>Booking Confirmation</h2>\n" +
                    "\n" +
                    "        <img src=\"bus_image.jpg\" alt=\"Bus Image\">\n" +
                    "\n" +
                    "        <p>Dear [CustomerName],</p>\n" +
                    "\n" +
                    "        <p>Thank you for booking with us! Your reservation is confirmed. Below are the details:</p>\n" +
                    "\n" +
                    "        <ul>\n" +
                    "            <li><strong>Booking Reference:</strong> [BookingReference]</li>\n" +
                    "            <li><strong>Bus Details:</strong> [BusDetails]</li>\n" +
                    "            <li><strong>Departure Date:</strong> [DepartureDate]</li>\n" +
                    "            <li><strong>Seat Number:</strong> [SeatNumber]</li>\n" +
                    "        </ul>\n" +
                    "\n" +
                    "        <p>We look forward to welcoming you aboard!</p>\n" +
                    "\n" +
                    "        <a href=\"[ConfirmationLink]\" class=\"btn-confirm\">Confirm Reservation</a>\n" +
                    "\n" +
                    "        <p>If you have any questions, feel free to contact us.</p>\n" +
                    "\n" +
                    "        <p>Safe travels,<br>Your Bus Company Team</p>\n" +
                    "\n" +
                    "    </div>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>";
            mimeMessageHelper.setText("hi" , message);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            log.info("Email send successfully");
        } catch(Exception e){
            log.error("Error while sending the email", e);
        }
    }

}
