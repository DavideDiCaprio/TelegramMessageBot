import org.json.JSONObject;
import org.json.JSONArray;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class ReadChatMessage {

    ReadConfigFile configData = new ReadConfigFile();
    String apiToken = configData.readFile().get("apiToken");

    String request = "https://api.telegram.org/bot" + apiToken + "/getUpdates";
    
    public String getResultString (){

        String StrResult = null;

        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(request);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            StrResult = String.valueOf(result);
            rd.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return StrResult;
    }

    public String getLastReceivedMessage() {

        String jsonText = getResultString();
        JSONObject jsonObject = new JSONObject(jsonText);
        JSONArray resultArray = jsonObject.getJSONArray("result");

        if (resultArray.length() == 0) {
            return null;
        }

        // Get the last message from the result list
        JSONObject lastMessageObject = resultArray.getJSONObject(resultArray.length() - 1);
        JSONObject messageObject = lastMessageObject.getJSONObject("message");
        String lastMessageText = messageObject.getString("text");

        return lastMessageText;
    }
}
