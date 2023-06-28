import org.json.simple.parser.ParseException;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.io.IOException;
import static java.lang.Thread.sleep;

public class Main {
    public static void main (String [] args) throws IOException, ParseException, InterruptedException {
      
        ReadChatMessage readMsg = new ReadChatMessage();
        SendMessage sendReceivedMsg = new SendMessage();
        String lastMessage = readMsg.getLastReceivedMessage();

        while (true) {
          
            sleep(10000);
            String newMessage = readMsg.getLastReceivedMessage();

            if (newMessage == null) {
                System.out.println("message is null");
                return;
            }

            if (! newMessage.equals(lastMessage)) {
                lastMessage = newMessage;
                sendReceivedMsg.sendToTelegram("your last received message is " + "'" + lastMessage + "'");
            }
        }

        // use this for create your bot
        /*try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new CreateBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } */
    }
}
