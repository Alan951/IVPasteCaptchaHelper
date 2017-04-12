/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.fxml.FXML;
import ivpastecaptchahelper.IVPasteCaptchaHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class ResolverCaptchaController {

    private IVPasteCaptchaHelper main;
    
    @FXML private WebView webView;
    @FXML private Button btnSiguiente;
    @FXML private Button btnAnterior;
    
    @FXML private Label lblLinkActual;
    
    private WebEngine webEngine;
    
    private String links[];
    private int cursor;
    
    @FXML
    public void initialize() {
        cursor = 0;
        webEngine = webView.getEngine();
        
        webEngine.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/601.6.17 (KHTML, like Gecko) Version/9.1.1 Safari/601.6.17");
        
        System.setProperty("http.proxy", "8.8.8.8");
        System.setProperty("http.proxyPort", "53");
    }
    
    public void init(IVPasteCaptchaHelper main, String links[]){
        this.main = main;
        this.links = links;
        
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
          @Override
          public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
            if (newState == Worker.State.SUCCEEDED) {
              main.getMainStage().setTitle(webEngine.getLocation());
              //System.out.println(webEngine.getDocument().getTextContent());
            }
          }
        });
        
        showPag();
        
        lblLinkActual.setText("Enlace "+(cursor + 1)+" de "+links.length+".");
    }
    
    public void nextPage(){
        cursor++;
    }
    
    public void previusPage(){
        cursor--;
    }
    
    public void showPag(){
        webEngine.load(links[cursor]);
    }
    
    @FXML
    public void onSiguiente(){
        nextPage();
        showPag();
        
        lblLinkActual.setText("Enlace "+(cursor + 1)+" de "+links.length+".");
    }
    
    @FXML
    public void onAnterior(){
        previusPage();
        showPag();
        
        lblLinkActual.setText("Enlace "+(cursor + 1)+" de "+links.length+".");
    }
}
