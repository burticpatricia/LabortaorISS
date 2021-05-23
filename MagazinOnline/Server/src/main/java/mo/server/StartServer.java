package mo.server;

import mo.model.Client;
import mo.model.Produs;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;
import java.util.Set;

public class StartServer {
    public static void main(String[] args) {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:MagazinOnlineConfig.xml");
        System.out.println("Waiting for clients...");
    }
}
