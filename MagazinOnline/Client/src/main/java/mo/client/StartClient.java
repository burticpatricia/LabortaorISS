package mo.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mo.client.controllers.LoginController;
import mo.client.controllers.MainPageController;
import mo.client.controllers.ProductsController;
import mo.services.IService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartClient extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        IService server=(IService) factory.getBean("MOService");
        System.out.println("Obtained a reference to remote server");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/loginView.fxml"));
        Parent root = loader.load();
        LoginController ctrl = loader.getController();


        FXMLLoader cloader = new FXMLLoader();
        cloader.setLocation(getClass().getResource("/productsView.fxml"));
        Parent croot = cloader.load();
        ProductsController cctrl = cloader.getController();



        ctrl.setContext(server,primaryStage,cctrl,croot);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome!");
        primaryStage.setOnCloseRequest(event->System.exit(0));
        primaryStage.show();

    }
}
