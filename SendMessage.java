import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class SendMessage {
    public static void sendToTelegram(String message) {

        ReadConfigFile configData = new ReadConfigFile();

        //Add chatId
        String userChatID = configData.readFile().get("chatId");

        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        //Add Telegram token
        String apiToken = configData.readFile().get("apiToken");

        urlString = String.format(urlString, apiToken, userChatID, message);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());

            //getting text, we can set it to any TextView
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            StringBuilder sb = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            //You can set this String to any TextView
            String response = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
