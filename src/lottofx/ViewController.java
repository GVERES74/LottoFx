/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lottofx;


import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *editor-fold: jelöld ki az összecsukni kívánt sorokat, utána a villanykörte, majd válaszd ki a surround opciót
 * @author VERESG
 */
public class ViewController implements Initializable {
    
    private final int MAX = 90;
    private final int MIN = 1;
    private int generatedNum1, generatedNum2, generatedNum3, generatedNum4, generatedNum5;
    private int selectedNum1, selectedNum2, selectedNum3, selectedNum4, selectedNum5; 
      
    ArrayList<ToggleButton> lottoNumbers = new ArrayList<>(); 
    
    
    
    //<editor-fold defaultstate="collapsed" desc="FXML declarations">
    @FXML
    private TextField szam1, szam2, szam3, szam4, szam5, starttext;
    @FXML
    private Label alerttext, hitlabel1, hitlabel2, hitlabel3, hitlabel4, hitlabel5, pushlabel;
    @FXML
    private Button button1, btnClosePopup, closeApp, aboutbutton, btn_ccancel, pushbutton;
    @FXML
    private TextField talalattextfield, hittextfield1, hittextfield2, hittextfield3, hittextfield4, hittextfield5;
    @FXML
    private Pane alertpane, basepane, aboutanchorpane, stackpane, numberpane, startpane, confirmexit, menupane, javafxpane;
    @FXML
    private TextArea abouttextarea;
    @FXML
    private ToggleButton togglebtn1; 
    @FXML
    private Circle circle1, circle2, circle3, circle4, circle5, pushcircle; 
    @FXML
    private Hyperlink hyperlink1; 
      
    


//</editor-fold>
    
       
    @FXML
    private void pressButtonClosePopup(ActionEvent event){
        alertpane.setVisible(false);
        basepane.setOpacity(1.0);
        basepane.setDisable(false);
        menupane.setOpacity(1.0);
        menupane.setDisable(false);
    }
    
    @FXML
    private void pressButtonCloseAbout(ActionEvent event){
        aboutanchorpane.setVisible(false);
        basepane.setOpacity(1.0);
        basepane.setDisable(false);
        menupane.setDisable(false);
        menupane.setOpacity(1.0);
    }
    
    @FXML
    private void chooseMenuAbout1(ActionEvent event) throws FileNotFoundException, IOException{
        aboutanchorpane.setVisible(true);
        disableBasePanes();
        BufferedReader aboutfile = new BufferedReader(new FileReader("c:\\Users\\VERESG\\Documents\\NetBeansProjects\\LottoFX\\src\\lottofx\\about.txt"));
        aboutfile.skip(1); String str;
        while((str = aboutfile.readLine()) != null){
                    abouttextarea.appendText(str);
        }
            
        
        }
    
     @FXML
    private void chooseNewGame(ActionEvent event){
               
        addNumButtons();
        setAllToDefault();
        basepane.setDisable(false);
        basepane.setVisible(true);
        startpane.setVisible(false);
        javaFXBanner();
               
    }
    
    @FXML
    private void showExitConfirmPane(ActionEvent event){
        
        confirmexit.setVisible(true);
        disableBasePanes();
        btn_ccancel.setOnAction(new EventHandler<ActionEvent>(){
             @Override
                public void handle(ActionEvent e) {
                    confirmexit.setVisible(false);
                     basepane.setOpacity(1.0);
                     basepane.setDisable(false);
                     menupane.setOpacity(1.0);
                     menupane.setDisable(false);
                }
        });
    }
    
    @FXML
    private void pressButtonCloseApp(ActionEvent event){
        
        System.exit(0); 
    }
    
               
    @FXML
    private void hyperlinkSetOnAction(ActionEvent event){
        
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        webEngine.load("http://sanfranciscoboljottem.com/");
    }   
    
    @FXML
    private void pushCircleMouseOn(){
    
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(2.0); shadow.setSpread(0.75); shadow.setColor(Color.color(0.8, 0.3, 0.3));
        pushcircle.setOnMouseMoved(new EventHandler<MouseEvent>(){
             @Override
                public void handle(MouseEvent mevent) {
                    
                    pushcircle.setEffect(shadow);
                    pushcircle.setRadius(30);
                    pushlabel.setFont(Font.font("System", FontWeight.BOLD, 11));
                    
                }
        });
    }
    
    @FXML
    private void pushCircleMouseExit(){
        
            pushcircle.setOnMouseExited(new EventHandler<MouseEvent>(){
             @Override
                public void handle(MouseEvent mevent) {
                    
                    pushcircle.setEffect(null);
                    pushcircle.setRadius(30);
                    pushlabel.setFont(Font.font("System", FontWeight.BOLD, 12));
                  
                }
        });
    }
    
    @FXML
    private void pushCircleMouseClicked(){
        
            pushcircle.setOnMouseClicked(new EventHandler<MouseEvent>(){
             @Override
                public void handle(MouseEvent mevent) {
                    
                    setAllToDefault();        
                    checkIfEverythingIsOk();
                }
        });
    }
    
    @FXML
    private void pushCircleMousePressed(){
        
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(1.5); shadow.setSpread(0.90); shadow.setColor(Color.color(0.8, 0.4, 0.3));
            pushcircle.setOnMousePressed(new EventHandler<MouseEvent>(){
             @Override
                public void handle(MouseEvent mevent) {
                    
                    pushcircle.setEffect(shadow);
                    pushcircle.setRadius(28);
                    pushlabel.setFont(Font.font("System", FontWeight.BOLD, 10));
                    
                    
                }
        });
    }
    
    @FXML
    private void pushCircleMouseReleased(){
                    
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(2.0); shadow.setSpread(0.75); shadow.setColor(Color.color(0.8, 0.3, 0.3));
            pushcircle.setOnMouseReleased(new EventHandler<MouseEvent>(){
             @Override
                public void handle(MouseEvent mevent) {
                    
                    pushcircle.setEffect(shadow);
                    pushcircle.setRadius(30);
                    pushlabel.setFont(Font.font("System", FontWeight.BOLD, 11));
                    
                }
        });
    }
    private void disableBasePanes(){
        
        basepane.setDisable(true);
        menupane.setDisable(true);
        menupane.setOpacity(0.3);
        basepane.setOpacity(0.3);
        
    }
            
    public void checkIfEverythingIsOk(){
        ArrayList<Integer> choosenNumbers = new ArrayList<>(); 
        choosenNumbers.clear();
        for (ToggleButton cnum: lottoNumbers){
        if (cnum.isSelected()){
        cnum.setFont(Font.font("System", FontWeight.BOLD, 12));
        cnum.setTextFill(Color.RED);
        choosenNumbers.add(Integer.parseInt(cnum.getText()));
           
        }       
    }
         
        
    Set<Integer> generatedNums = new HashSet<>();
    generatedNums.add(generatedNum1);
    generatedNums.add(generatedNum2);
    generatedNums.add(generatedNum3);
    generatedNums.add(generatedNum4);
    generatedNums.add(generatedNum5);
    ArrayList<Integer> hitNumbers = new ArrayList<>(generatedNums);  
    
    System.out.println(choosenNumbers.size());
    System.out.println(choosenNumbers);
    
    
    int inc = 0;
    for (int i=0; i < choosenNumbers.size(); i++){
        if (choosenNumbers.size() != 5 ){ show_alertbox("5 számot kell bejelölnöd az érvényességhez!"); return;}
        
        if (choosenNumbers.get(i) == generatedNum1 || choosenNumbers.get(i) == generatedNum2 || choosenNumbers.get(i) == generatedNum3 || choosenNumbers.get(i) == generatedNum4 || choosenNumbers.get(i) == generatedNum5)
            inc++;
        
        }
        
        switch (inc){
            case 0: talalattextfield.setText("Sajnos nincs találat!"); break;
            case 1: talalattextfield.setText("1 találatod van! "); break;
            case 2: talalattextfield.setText("2 találatod van! "); break;
            case 3: talalattextfield.setText("3 találatod van! "); break;
            case 4: talalattextfield.setText("4 találatod van! "); break;
            case 5: talalattextfield.setText("Telitalálat! 5 találatod van! "); break;
        }
            try{
               
                for (int j=0; j<hitNumbers.size(); j++){
                    for (Integer choosenNumber : choosenNumbers) {
                        if (Objects.equals(choosenNumbers.get(0), hitNumbers.get(j))){circle1.setVisible(true); hitlabel1.setVisible(true); hitlabel1.setText(""+choosenNumbers.get(0));}
                        if (Objects.equals(choosenNumbers.get(1), hitNumbers.get(j))){circle2.setVisible(true); hitlabel2.setVisible(true); hitlabel2.setText(""+choosenNumbers.get(1));}
                        if (Objects.equals(choosenNumbers.get(2), hitNumbers.get(j))){circle3.setVisible(true); hitlabel3.setVisible(true); hitlabel3.setText(""+choosenNumbers.get(2));}
                        if (Objects.equals(choosenNumbers.get(3), hitNumbers.get(j))){circle4.setVisible(true); hitlabel4.setVisible(true); hitlabel4.setText(""+choosenNumbers.get(3));}
                        if (Objects.equals(choosenNumbers.get(4), hitNumbers.get(j))){circle5.setVisible(true); hitlabel5.setVisible(true); hitlabel5.setText(""+choosenNumbers.get(4));}
                            
                    }
            }
            }
            
            catch (Exception e){
                show_alertbox("Nem választottál egyetlen számot sem!");
                return;
                }
      return;                             
    }
    
 /*--------------Core Functions------------------------*/   
    private void show_alertbox(String outtext){
        disableBasePanes();
        alertpane.setVisible(true);
        alerttext.setText(outtext);
    }
    
       
    private int getRandomNumber(){
        int random = (int) (Math.random()* MAX) + MIN;
        
        if (random == generatedNum1 || random == generatedNum2 || random == generatedNum3 || random == generatedNum4 || random == generatedNum5){
        return getRandomNumber();
    }
        return random;
    
    }
   
       
    private void setAllToDefault(){
        talalattextfield.setVisible(true);
        talalattextfield.setText("Izgatottan várjuk az eredményt...");

        generatedNum1 = generatedNum2 = generatedNum3 = generatedNum4 = generatedNum5 = 0;
        generatedNum1 = getRandomNumber(); generatedNum2 = getRandomNumber(); generatedNum3 = getRandomNumber(); generatedNum4 = getRandomNumber(); generatedNum5 = getRandomNumber();
        
                 circle1.setVisible(false); hitlabel1.setVisible(false);
                 circle2.setVisible(false); hitlabel2.setVisible(false);
                 circle3.setVisible(false); hitlabel3.setVisible(false);
                 circle4.setVisible(false); hitlabel4.setVisible(false);
                 circle5.setVisible(false); hitlabel5.setVisible(false);
                 szam1.setText(""+generatedNum1);
                 szam2.setText(""+generatedNum2);
                 szam3.setText(""+generatedNum3);
                 szam4.setText(""+generatedNum4);
                 szam5.setText(""+generatedNum5);
    }
        
    private void addNumButtons(){
        
        double xpos = 10.0;
        double ypos = 10.0;
        
        for (int i = 1; i < 91; i++){
        ToggleButton btn = new ToggleButton();
        btn.setPrefSize(32.0, 32.0);
        
        numberpane.getChildren().add(btn);
        lottoNumbers.add(btn);
        btn.setText(""+i);
        btn.setLayoutX(xpos); btn.setLayoutY(ypos);
        xpos = 10.0;
        xpos += btn.getLayoutX()+25;
        for (int j = 10; j < 100; j=j+10){
        if (i == j ) {
            ypos = 10.0;
            ypos += btn.getLayoutY()+25;
            xpos = 10.0;
        }                
        }
        }
        
    }
    private void javaFXBanner(){
        Image banner = new Image("https://docs.oracle.com/javafx/javafx/images/fx_boxback_logo.jpg");
        ImageView imgviewer = new ImageView(banner);
        imgviewer.setScaleX(0.5); imgviewer.setScaleY(0.5);
        javafxpane.getChildren().add(imgviewer);
        
    }   
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println();
    }    


}
