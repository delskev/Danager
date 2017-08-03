package be.perzival.danager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Perzival on 30/07/2017.
 */
public class MainApp {



    public static void main(String [] args) throws IOException, IllegalAccessException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("SpringContext.xml");

        DanagerBot danagerBot = ctx.getBean(DanagerBot.class);
        String api = System.getenv().get("DISCORD");
        if (api != null) {
            danagerBot.Connect(api);
        }else if(args[0] != null) {
            danagerBot.Connect(args[0]);
        }else {
            throw new IllegalArgumentException("There's no argument");
        }

    }
}
