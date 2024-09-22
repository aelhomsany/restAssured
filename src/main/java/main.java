import utils.MqUtils;
import utils.TokenUtils;

import java.awt.event.InputEvent;
import java.util.logging.Level;

class Simple{
    public static void main(String[] args){

            System.out.println("Hello Java");
//        MqUtils mqUtils = new MqUtils();
//        mqUtils.connectToRabbitMQ();
//        mqUtils.purgeQueue("q_bundles_categories_import_preprod");
//        mqUtils.closeConnection();

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        System.out.println(TokenUtils.getItemAsString(token, "name"));
        System.out.println(TokenUtils.getItemAsInteger(token, "iat"));



    }
}  