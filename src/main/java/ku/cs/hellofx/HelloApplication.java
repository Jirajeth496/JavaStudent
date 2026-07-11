package ku.cs.hellofx;

import ku.cs.services.FXRouter;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        configRoute();
        FXRouter.bind(this, stage);
        FXRouter.goTo("hello");
    }

    private void configRoute() {
        String viewPath = "ku/cs/views/";
        FXRouter.when("hello", viewPath + "hello-view.fxml");
        FXRouter.when("student-list", viewPath + "student-list.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}