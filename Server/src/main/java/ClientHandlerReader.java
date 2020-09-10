import com.sun.org.apache.xml.internal.security.Init;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientHandlerReader extends SimpleChannelInboundHandler {

    public String[] title;
    public String[] shortDescriptions;
    public String[] fullDescription;
    public ServerNews[] news;
    public ArrayList<ServerNews> listNews = new ArrayList<>();
    public String mainPath = new String("Server/src/main/resources/");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Клиент подключился");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Клиент отключился");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            String getNews = (String) msg;
            if (getNews.startsWith("/getNews")) {
                System.out.println("Сообщение получено " + msg);
                File file = new File(mainPath + "news.txt");
                FileInputStream input = new FileInputStream(file);
                byte[] bytes = new byte[input.available()];
                input.read(bytes, 0, bytes.length);
                ctx.writeAndFlush(bytes);
                System.out.println("Файл загружен на сервер");
                System.out.println("Отправлено: " + this.listNews.toString());
            }
            if (getNews.startsWith("/getAds")) {
                System.out.println("Сообщение получено " + msg);
                File file = new File(mainPath + "ads.txt");
                FileInputStream input = new FileInputStream(file);
                byte[] bytes = new byte[input.available()];
                input.read(bytes, 0, bytes.length);
                ctx.writeAndFlush(bytes);
                System.out.println("Файл загружен на сервер");
                System.out.println("Отправлено: " + this.listNews.toString());
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    public void loadBD(){
        System.out.println("Начинаем метод");
        //кол-во новостей
        int quantity = 5;
        news = new ServerNews[quantity];
        title = new String[]{"Заголовок", "Заголовок", "Заголовок", "Заголовок", "Заголовок"};
        shortDescriptions = new String[]{"Новость, пришедшая с свервера. На все равно как она отобразится, лишь бы пришла." +
                "Точно известно, что новость достаточно интересная",
                "Новость 2, В России закрыто большинство уголовных дел, которые заведены по статье" +
                        " Экстримизм. Неизвестно, почему, но факт остаётся фактом",
                "Новость 3 Ранее здесь была новость про жопу. В данный момент такие новости больше не" +
                        " публикуются, т.к. это не очень цензурно",
                "Новость 4 Просто короткая новость. Типа пару слов.<>",
                "Новость 5 Это вообще всем новостям новость. Пишу код и слушаю адвоката из Агоры. Очень интересно" +
                        "В скобочках (нет). Да, я конечно мастер юмора, но новости из головы писать действительно сложно"};
        fullDescription = new String[]{"Новость, пришедшая с свервера. На все равно как она отобразится, лишь бы пришла." +
                "Точно известно, что новость достаточно интересная.  Полное описание  Полное описание  Полное описание  Полное описание  Полное описание  Полное описание  Полное описание  Полное описание ",
                "Новость 2, В России закрыто большинство уголовных дел, которые заведены по статье" +
                        " Экстримизм. Неизвестно, почему, но факт остаётся фактом. Полное описание  Полное описание  Полное описание  Полное описание  Полное описание  Полное описание  Полное описание ",
                "Новость 3 Ранее здесь была новость про жопу. В данный момент такие новости больше не" +
                        " публикуются, т.к. это не очень цензурно.  Полное описание  Полное описание  Полное описание  Полное описание  Полное описание  Полное описание  Полное описание  Полное описание ",
                "Новость 4 Просто короткая новость. Типа пару слов.  Полное описание  Полное описание  Полное описание  Полное описание  Полное описание  Полное описание  Полное описание  Полное описание ",
                "Новость 5 Это вообще всем новостям новость. Пишу код и слушаю адвоката из Агоры. Очень интересно" +
                        "В скобочках (нет). Да, я конечно мастер юмора, но новости из головы писать действительно сложно.  Полное описание  Полное описание  Полное описание  Полное описание  Полное описание  Полное описание "};

        System.out.println("Загружены новости в стринги");
        int count = 0;
        for(int i = 0; i < quantity-1; i++){
            news[i] = new ServerNews();
            news[i].setTitle(title[count]);
            news[i].setFullDescription(StrBuilder.createDescr(fullDescription[count]));
            news[i].setShortDescription(StrBuilder.createShortDescr(shortDescriptions[count]));
            this.listNews.add(news[i]);
            count++;
        }
        System.out.println("Заканчиваем метод");
    }
}
