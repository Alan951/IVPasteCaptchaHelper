/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import ivpastecaptchahelper.IVPasteCaptchaHelper;
import ivpastecaptchahelper.Utils;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Ck
 */
public class EntradaURLSController{
    
    @FXML private Label lblHeaderTitle;
    @FXML private TextArea txtLinks;
    @FXML private Button btnLimpiarLinks;
    @FXML private Button btnContinuar;
    
    String links[];
    
    private static final String HEADER_TITLE = "IVPaste Links";
    
    private IVPasteCaptchaHelper main;
    
    @FXML
    public void initialize() {
        lblHeaderTitle.setText(HEADER_TITLE);
    }
    
    public void init(IVPasteCaptchaHelper main){
        this.main = main;
    }
    
    public EntradaURLSController(){}
    
    @FXML
    public void onLimpiarLinks(){
        String links[] = Utils.extractUrls(txtLinks.getText());
        
        if(links.length > 0){
            lblHeaderTitle.setText(HEADER_TITLE + " ("+links.length+" links)");
            
            txtLinks.clear();
        
            for(String link : links){
                txtLinks.appendText(link+"\n");
            }

            this.links = links;
            
            btnContinuar.setDisable(false);
        }   
    }
    
    @FXML
    public void onContinuar(){
        
        main.abrirResolverCaptcha(main.getMainStage(), links);
    }
    
    
    
}
