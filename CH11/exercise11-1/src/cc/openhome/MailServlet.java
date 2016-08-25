package cc.openhome;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.http.Part;

import javax.mail.*;
import javax.mail.internet.*;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

@MultipartConfig
@WebServlet(
    urlPatterns={"/mail.do"},
    initParams={
        @WebInitParam(name = "mailHost", value = "smtp.gmail.com"),
        @WebInitParam(name = "mailPort", value = "465"),
        @WebInitParam(name = "username", value = "your_username"),
        @WebInitParam(name = "password", value = "your_password")
    }
)
public class MailServlet extends HttpServlet {
    private String mailHost;
    private String mailPort;
    private String username;
    private String password;
    private Properties props;
    
    private String html = 
    "<html>" + 
    "    <head>" +
    "        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>" +
    "    </head>" +
    "    <body>" +
    "    <img" +
    "        style='border: 3px solid ; font-weight: bold;'" +
    "            src='cid:#filename'" +
    "            hspace='3' vspace='3'> <br>" +
    "        #text" +
    "    </body>" +
    "</html>";

	@Override
    public void init() throws ServletException {
	    mailHost = getServletConfig().getInitParameter("mailHost");
	    mailPort = getServletConfig().getInitParameter("mailPort");
	    username = getServletConfig().getInitParameter("username");
	    password = getServletConfig().getInitParameter("password");
	    
        props = new Properties();
        props.put("mail.smtp.host", mailHost);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", mailPort);
        props.setProperty("mail.smtp.socketFactory.port", mailPort);
        props.setProperty("mail.smtp.auth", "true");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String text = request.getParameter("text");
        Part part = request.getPart("image");
        
        try {
            Message message = getMessage(from, to, subject, text, part);
            Transport.send(message);
            response.getWriter().println("郵件傳送成功");
        } catch (Exception e) {
            throw new ServletException(e);
        } 
	}

    private Message getMessage(String from, String to, String subject,
            String text, Part part) throws MessagingException, AddressException, IOException {
        Session session = Session.getDefaultInstance(props, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }}
        );
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setSentDate(new Date());
        
        String filename = getFilename(part);
        
        MimeBodyPart htmlPart = new MimeBodyPart(); 
        htmlPart.setContent(html.replace("#filename", filename).replace("#text", text), "text/html;charset=UTF-8");
        
        Multipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(htmlPart);
        
        
        
            MimeBodyPart filePart = new MimeBodyPart();
            filePart.setFileName(MimeUtility.encodeText(filename, "UTF-8", "B"));

            filePart.setHeader("Content-ID", "<" + filename + ">");
            filePart.setContent(getBytes(part), part.getContentType());
            multiPart.addBodyPart(filePart);
        
        
        message.setContent(multiPart);
        
        return message;
    }
    
    private String getFilename(Part part) {
        String header = part.getHeader("Content-Disposition");
        String filename =
               header.substring(header.indexOf("filename=\"") + 10,
                header.lastIndexOf("\""));
        return filename;
    }
    
    private byte[] getBytes(Part part) throws IOException {
        InputStream in = part.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
         byte[] buffer = new byte[1024];
         int length = -1;
         while ((length = in.read(buffer)) != -1) {
             out.write(buffer, 0, length);
         }
         in.close();
         out.close();
         return out.toByteArray();
    }

}
