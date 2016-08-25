package cc.openhome;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

@WebServlet("/captcha")
public class Captcha extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("image/jpeg");

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            int number = (int)(Math.random() * 10);
            builder.append(number);
        }
        String password = builder.toString();
        
        OutputStream out = response.getOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(getPasswdImage(password));
        out.close();
    }

    public BufferedImage getPasswdImage(String password) throws IOException {
        BufferedImage bufferedImage =
                 new BufferedImage(60, 25,
                         BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.WHITE);
        g.setFont(new Font("標楷體", Font.BOLD, 16));
        g.drawString(password, 10, 15);
        return bufferedImage;
    }}
