import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Client extends Application implements Initializable {

    public static Socket socket;
    public static ObjectDecoderInputStream is;
    public static ObjectEncoderOutputStream os;
    public TextArea textNews;

    public ListView<String> list;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("StartInterface.fxml"));
        primaryStage.setTitle("AlexApplication");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void readNews(){
        String news = new String("проверка проверка ааааааааааааааааааааааааа ыыыыыыыыыыыыыыыыыы ккккккккккккк");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            socket = new Socket("localhost", 8189);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Проблема с подключением к серверу");
        }
        try {
            is = new ObjectDecoderInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os = new ObjectEncoderOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Клиент запустился");
        textNews.setText("Хеллоу пипл крупный калибр у меня за пазухой поэтому и влип ты");
    }
}
