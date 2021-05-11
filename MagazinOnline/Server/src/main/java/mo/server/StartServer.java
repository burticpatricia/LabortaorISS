package mo.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartServer {
    public static void main(String[] args) {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:MagazinOnlineConfig.xml");
        System.out.println("Waiting for clients...");
    }
}
