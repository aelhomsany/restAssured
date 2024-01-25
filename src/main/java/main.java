import utils.MqUtils;

import java.awt.event.InputEvent;
import java.util.logging.Level;

class Simple{
    public static void main(String[] args){

            System.out.println("Hello Java");
        MqUtils mqUtils = new MqUtils();
        mqUtils.connectToRabbitMQ();
        mqUtils.purgeQueue("q_bundles_categories_import_preprod");
        mqUtils.closeConnection();

    }
}  