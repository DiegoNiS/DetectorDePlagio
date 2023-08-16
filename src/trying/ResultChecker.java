package trying;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ResultChecker {
    public static void btnAdvert(String title, String content){
        Alert mensaje = new Alert(Alert.AlertType.WARNING);
        mensaje.setTitle(title);
        mensaje.setContentText(content);
        mensaje.showAndWait();
    }
    
    public static void btnInf(String title, String content){
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setHeaderText("Mensaje de Informacion");
        mensaje.setTitle(title);
        mensaje.setContentText(content);
        mensaje.showAndWait();
    }
    
    public static boolean btnConf(String title, String content){
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setHeaderText("Mensaje de Informacion");
        mensaje.setTitle(title);
        mensaje.setContentText(content);
        
        ButtonType botonSi = new ButtonType("Si");
        ButtonType botonNo = new ButtonType("No");
        
        mensaje.getButtonTypes().setAll(botonSi, botonNo);
        Optional<ButtonType> option = mensaje.showAndWait();
        
        return option.get() == botonSi;
    }
}
