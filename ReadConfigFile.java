import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadConfigFile {

    public Map<String, String> readFile() {

        Map<String, String> configData = new HashMap<String, String>();

        try {
          
            File myObj = new File("your absolute Path for config text") ;
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String apiKey = myReader.nextLine();
                configData.put("apiKey",apiKey);

                String apiToken = myReader.nextLine();
                configData.put("apiToken",apiToken);

                String chatId = myReader.nextLine();
                configData.put("chatId",chatId);

            }

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }

        return configData;
    }
}
