/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivpastecaptchahelper;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.EntradaURLSController;
import view.ResolverCaptchaController;

/**
 *
 * @author Ck
 */
public class IVPasteCaptchaHelper extends Application {
    
    private Stage mainStage;
    
    @Override
    public void start(Stage stage) {
        abrirEntrarURLS(stage);
    }
    
    public Stage getMainStage(){
        return mainStage;
    }
    
    public void abrirEntrarURLS(Stage stage){
        AnchorPane anchorP = null;
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/EntradaURLS.fxml"));
        System.out.println(loader.getLocation());
        try{
            anchorP = (AnchorPane)loader.load();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        Scene scene = new Scene(anchorP);
        
        stage.setTitle("IVPaste");
        stage.setScene(scene);
        
        this.mainStage = stage;
        
        EntradaURLSController controlador = loader.getController();
        controlador.init(this);        
        
        stage.show();
        System.out.println("Ejecuto");
    }
    
    public void abrirResolverCaptcha(Stage stage, String[] links){
        AnchorPane anchorP = null;
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ResolverCaptcha.fxml"));
        System.out.println(loader.getLocation());
        try{
            anchorP = (AnchorPane)loader.load();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        Scene scene = new Scene(anchorP);
        
        stage.setTitle("Resuelve los captcha");
        stage.setScene(scene);
        
        this.mainStage = stage;
        
        ResolverCaptchaController controlador = loader.getController();
        controlador.init(this, links);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
