/* Par  : Alice Grunder et Hugo Huart
   Date : 2021-12-07
   Desc : Classe modélisant un client SMTP
*/

package ch.coolsmtp;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SmtpClient {
    private static final String NAME = "coolsmtp";
    private static final Charset STANDARD_CHARSET = StandardCharsets.UTF_8;

    private String serverAddress;
    private int port;

    public SmtpClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public boolean sendMailPrank(String from, String[] to, String content) {
        Socket socket;
        BufferedReader in;
        BufferedWriter out;

        try {
            String tmp;

            socket = new Socket(serverAddress, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), STANDARD_CHARSET));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), STANDARD_CHARSET));

            if (!in.readLine().startsWith("220"))
                return false;

            /* EHLO */
            out.write(String.format("EHLO %s\r\n", NAME));
            out.flush();

            while ((tmp = in.readLine()) != null) {
                if (!tmp.startsWith("250"))
                    return false;
                else if (tmp.startsWith("250 "))
                    break;
            }

            /* MAIL FROM */
            out.write(String.format("MAIL FROM: %s\r\n", from));
            out.flush();

            if (!(in.readLine()).startsWith("250"))
                return false;

            /* RCPT TO*/
            out.write("RCPT TO:");
            for (int i = 0; i < to.length; ++i)
                out.write(String.format("%s%s", to[i], i == to.length - 1 ? "\r\n" : ","));
            out.flush();

            if (!(in.readLine()).startsWith("250"))
                return false;

            /* DATA */
            out.write("DATA\r\n");
            out.flush();

            if (!(in.readLine()).startsWith("354"))
                return false;

            out.write(String.format("Content-type: text/plain; charset=utf-8\r\n%s\r\n.\r\n", content));
            out.flush();

            if (!(in.readLine()).startsWith("250"))
                return false;

            /* QUIT */
            out.write("QUIT\r\n");
            out.flush();

            if (!(in.readLine()).startsWith("221"))
                return false;

            /* Fermeture des flux */
            in.close();
            out.close();

            socket.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
