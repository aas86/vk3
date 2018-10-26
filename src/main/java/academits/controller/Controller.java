package academits.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;


@RestController
@RequestMapping("/testTask/api")
public class Controller {

    @RequestMapping(value = "/logIn", method = RequestMethod.GET)
    public String logIn(/*@RequestParam(value = "x", required = false) String x,*/
                      @RequestParam(value = "code", required = false) String code,
                      @RequestParam(value = "access_token", required = false) String accessToken,
                      HttpServletResponse resp, HttpServletRequest req) throws IOException {
        // System.out.println(x);
        System.out.println(code);
        System.out.println(accessToken);


        if (code == null && accessToken == null) {
            //resp.sendRedirect("https://oauth.vk.com/authorize?client_id=6731226&display=popup&redirect_uri=http://localhost:8080/testTask/api/logIn&scope=wall&response_type=code&v=5.87");
            // resp.sendRedirect("http://ya.ru");
            return "https://oauth.vk.com/authorize?client_id=6731226&display=popup&redirect_uri=http://localhost:8080/testTask/api/logIn&scope=wall&response_type=code&v=5.87";
        } else if (code != null) {
            String url = "https://oauth.vk.com/access_token?client_id=6731226&client_secret=hLu7TXcJ7PKQ3vwvWoNI&redirect_uri=http://localhost:8080/testTask/api/logIn&code=" + code;
            //System.out.println(url);
            return url;
        } else {
             return accessToken;
        }
    }
    //return "A";

/*
    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    public void getToken(@RequestParam(value = "code", required = true) String code,
                         @RequestParam(value = "token", required = false) String token) {
        System.out.println(code);
        System.out.println(token);
        StringBuilder sb = new StringBuilder();
        sb.append("https://oauth.vk.com/access_token?client_id=6731226&client_secret=hLu7TXcJ7PKQ3vwvWoNI&redirect_uri=http://localhost:8080/testTask/api/getToken&code=")
                .append(code);
        System.out.println(sb.toString());
        HttpURLConnection connection = null;
        /*try {
            connection = (HttpURLConnection) new URL(sb.toString()).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            System.out.println(connection.getResponseMessage());
            System.out.println(connection.getContent());
            System.out.println(connection.getOutputStream().toString());
            //return sb.toString();
        } catch (Throwable e) {
            e.printStackTrace();
        } *//*finally {
            if (connection != null) {
                connection.disconnect();
            }*/


   /* @RequestMapping(value = "/signIn", method = RequestMethod.GET)
  /*  public void logIn(@RequestParam(value = "login", required = true) String login,
                        @RequestParam(value = "password", required = true) String password, HttpServletResponse resp) {
        System.out.println("login = " + login);
        System.out.println("password = " + password);

        String url = "https://oauth.vk.com/authorize?client_id=6731226&display=page&redirect_uri=" +
                "https://oauth.vk.com/blank.html/callback&scope=wall&response_type=token&v=5.87";


       // UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            System.out.println(connection.getResponseMessage());
            StringBuilder sb = new StringBuilder();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                 System.out.println(sb.toString());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

        }
        //resp.setStatus(300);
        //return url;



    }*/


}

