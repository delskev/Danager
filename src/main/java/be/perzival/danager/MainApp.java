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
        danagerBot.Connect();

    }
}
