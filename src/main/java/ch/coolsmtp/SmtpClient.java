package ch.coolsmtp;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SmtpClient {
    private static final String NAME = "coolsmtp";
    private static final String[] KEYWORDS = new String[]{"EHLO", "MAIL FROM:", "RCPT TO:","DATA"};

    private static final Charset STANDARD_CHARSET = StandardCharsets.UTF_8;


    private String serverAddress;
    private int port;

    public SmtpClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public boolean sendMailPrank(String from, String[] to, String content) {
        try {
            String tmp;
            String response = "";

            Socket socket = new Socket(serverAddress, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), STANDARD_CHARSET));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), STANDARD_CHARSET));

            response = in.readLine();
            if (!response.startsWith("220"))
                return false;

            /* EHLO */
            response = "";

            out.write(String.format("EHLO %s\r\n", NAME));
            out.flush();

            while ((tmp = in.readLine()) != null) {
                System.out.println(tmp);
                response += tmp;
                if (!tmp.startsWith("250"))
                    return false;
                else if (tmp.startsWith("250 "))
                    break;
            }

            /* MAIL FROM */
            out.write(String.format("MAIL FROM: %s\r\n", from));
            out.flush();

            if (!(response = in.readLine()).startsWith("250"))
                return false;
            System.out.println(response);

            /* RCPT TO*/
            out.write("RCPT TO:");
            for (int i = 0; i < to.length; ++i)
                out.write(String.format("%s%s", to[i], i == to.length - 1 ? "\r\n" : ","));
            out.flush();

            if (!(response = in.readLine()).startsWith("250"))
                return false;
            System.out.println(response);

            /* DATA */
            out.write("DATA\r\n");
            out.flush();

            if (!(response = in.readLine()).startsWith("354"))
                return false;
            System.out.println(response);

            out.write(String.format("%s\r\n.\r\n", content));
            out.flush();

            if (!(response = in.readLine()).startsWith("250"))
                return false;
            System.out.println(response);

            /* QUIT */
            out.write("QUIT\r\n");
            out.flush();

            if (!(response = in.readLine()).startsWith("221"))
                return false;
            System.out.println(response);

            /* Fermeture des flux */
            in.close();
            out.close();

            socket.close();

            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
