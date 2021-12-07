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

    public boolean sendMailPrank(String from, String to, String content) {
        try {
            String tmp;
            String response = "";

            Socket socket = new Socket(serverAddress, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), STANDARD_CHARSET));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), STANDARD_CHARSET));

            response = in.readLine();
            if (!response.startsWith("220"))
                return false;
            System.out.println(response);

            int i = 0;
            while (i < KEYWORDS.length) {
                out.write(KEYWORDS[i]);
                switch(i) {
                    case 0:
                        out.write(String.format(" %s\r\n", NAME));
                        out.flush();

                        response = "";
                        System.out.println("salut");
                        while ((tmp = in.readLine()) != null) {
                            System.out.println(tmp);
                            response += tmp;
                        }
                        System.out.println("salut done");

                        System.out.println(response);
                        break;
                    case 1:
                    case 2:
                        break;
                    case 3:
                }
                break;
            }

            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
