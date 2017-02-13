/*
 * This uses webview which uses webkit.
 * 
 * 
 */
package believe;

import java.io.File; 
import java.io.FileInputStream;
import java.io.FileWriter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author OLUYALE DAVID
 */
public class Believe extends Application {

     public String savedfilePath = "";
     public File sf;
     public String scode;
     public int tc = 0, ic=0, sc=0, bc=0;
     public boolean autosave=true;
     public Text status;
     public  javafx.scene.control.MenuItem saveas;
 
     public boolean Vorientation;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Believe Web Editor v1.5");
        primaryStage.sizeToScene();
        final Stage primaryStage2 = new Stage();
        primaryStage2.setTitle("Code Explanator");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.STEELBLUE);
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(5));
        gp.setHgap(5);
        gp.setVgap(5);
        //gp.setPrefHeight(550);
        
        
        
        /**********EXPLANATOR SCENE***************/
       final Group root2 = new Group();
       final Scene scene2 = new Scene(root2,500,500,Color.WHITE);
       final WebView w2 = new WebView();
       w2.setStyle("-fx-background-color:steelblue;");
GridPane gp2 = new GridPane();
        gp2.setPadding(new Insets(5));
        gp2.setHgap(5);
        gp2.setVgap(5);
        
        VBox bx = new VBox();
        bx.setPrefHeight(500);
        bx.setPrefWidth(900);
        bx.setStyle("-fx-border: 3px; -fx-border-color:black;");
        bx.getChildren().add(w2);
        gp2.getChildren().add(bx);
       // we.loadContent(sourceCode.getText());
    
        
        
        /***************************************/
        
        
        
        
        
        
        //STATUS
        
        //LABELS.
         status = new Text(0,0,"Press SPACE BAR if images or links do not load \n Mode :Auto save mode.");
         status.setFill(Color.WHEAT);
       
        /*<b>The menu...<b>
        
         */
        javafx.scene.control.MenuBar mbar = new javafx.scene.control.MenuBar();

        /*
         <b>File -> New, Open, save,saveas, quit;</b>
         */
        javafx.scene.control.Menu files = new javafx.scene.control.Menu("File");
        javafx.scene.control.MenuItem NEW = new javafx.scene.control.MenuItem("New");
        javafx.scene.control.MenuItem open = new javafx.scene.control.MenuItem("Open");
        javafx.scene.control.MenuItem save = new javafx.scene.control.MenuItem("Save");
        saveas = new javafx.scene.control.MenuItem("Save As");
        javafx.scene.control.MenuItem quit = new javafx.scene.control.MenuItem("Exit");
        
         
         
        files.getItems().add(NEW);
        files.getItems().add(open);
        files.getItems().add(save);
        //files.getItems().add(saveas);
        files.getItems().add(quit);
        
        saveas.setDisable(true);
        /*code explanator*/
        
        
        javafx.scene.control.Menu exp = new javafx.scene.control.Menu("Code Explanator");
        javafx.scene.control.MenuItem vexp = new javafx.scene.control.MenuItem("View Explanation");
        exp.getItems().add(vexp);
        
        /*
        
         <b>Insert -> form(textarea, test, color, date, calender, progress, meter, select, button)
         table
         */
        javafx.scene.control.Menu Insert = new javafx.scene.control.Menu("Insert");
        javafx.scene.control.Menu forms = new javafx.scene.control.Menu("Forms");

        javafx.scene.control.MenuItem textarea = new javafx.scene.control.MenuItem("Textarea");
        javafx.scene.control.MenuItem input = new javafx.scene.control.MenuItem("Input");
        javafx.scene.control.MenuItem select = new javafx.scene.control.MenuItem("Select");
        javafx.scene.control.MenuItem button = new javafx.scene.control.MenuItem("Button");

        Insert.getItems().add(forms);
        forms.getItems().add(textarea);
        forms.getItems().add(input);
        forms.getItems().add(select);
        forms.getItems().add(button);
       
       
        /*
        Autosave mode-> on(recommended) off
        When set to on, images, videos, audios and external files will be fully
        supported. 
        */
        javafx.scene.control.Menu Autosave = new javafx.scene.control.Menu("AutoSave");
         ToggleGroup tg= new ToggleGroup();
         RadioMenuItem autosaveon = new RadioMenuItem("ON (recommended)");     
         RadioMenuItem autosaveoff = new RadioMenuItem("OFF");
         autosaveon.setToggleGroup(tg);
         autosaveoff.setToggleGroup(tg);
         autosaveon.setSelected(true);
         Autosave.getItems().add(autosaveon);
         Autosave.getItems().add(autosaveoff);

         /*ORIENTATION*/
         
          javafx.scene.control.Menu Orien = new javafx.scene.control.Menu("Orientation");

        javafx.scene.control.RadioMenuItem V = new javafx.scene.control.RadioMenuItem("Vertical");
        javafx.scene.control.RadioMenuItem H = new javafx.scene.control.RadioMenuItem("Horizontal");
        ToggleGroup tg4= new ToggleGroup();
        V.setToggleGroup(tg4);
        H.setToggleGroup(tg4);
        V.setSelected(true);
        Orien.getItems().add(V);
        Orien.getItems().add(H);
        
       
         
         
         
         /*
         <b>Font -> size, color</b>
         */
         
          javafx.scene.control.Menu font = new javafx.scene.control.Menu("Font");
        javafx.scene.control.Menu size = new javafx.scene.control.Menu("Size");

        
         ToggleGroup tg2 = new ToggleGroup();
		 
        javafx.scene.control.RadioMenuItem s10 = new javafx.scene.control.RadioMenuItem("10%");
        javafx.scene.control.RadioMenuItem s20 = new javafx.scene.control.RadioMenuItem("20%");
        javafx.scene.control.RadioMenuItem s30 = new javafx.scene.control.RadioMenuItem("30%");
        javafx.scene.control.RadioMenuItem s40 = new javafx.scene.control.RadioMenuItem("40%");
        javafx.scene.control.RadioMenuItem s50 = new javafx.scene.control.RadioMenuItem("50%");
        
        javafx.scene.control.RadioMenuItem s60 = new javafx.scene.control.RadioMenuItem("60%");
        javafx.scene.control.RadioMenuItem s70 = new javafx.scene.control.RadioMenuItem("70%");
        javafx.scene.control.RadioMenuItem s80 = new javafx.scene.control.RadioMenuItem("80%");
        javafx.scene.control.RadioMenuItem s90 = new javafx.scene.control.RadioMenuItem("90%");
        javafx.scene.control.RadioMenuItem s100 = new javafx.scene.control.RadioMenuItem("100%");
        s10.setToggleGroup(tg2);
        s20.setToggleGroup(tg2);
        s30.setToggleGroup(tg2);
        s40.setToggleGroup(tg2);
        s50.setToggleGroup(tg2);
        s60.setToggleGroup(tg2);
        s70.setToggleGroup(tg2);
        s80.setToggleGroup(tg2);
        s90.setToggleGroup(tg2);
        s100.setToggleGroup(tg2);
        
        s20.setSelected(true);
         javafx.scene.control.Menu fcolor = new javafx.scene.control.Menu("color");
         
        
         ToggleGroup tg3 = new ToggleGroup();
		 
        javafx.scene.control.RadioMenuItem cblue = new javafx.scene.control.RadioMenuItem("Blue");
        javafx.scene.control.RadioMenuItem cred = new javafx.scene.control.RadioMenuItem("Red");
        javafx.scene.control.RadioMenuItem corange = new javafx.scene.control.RadioMenuItem("Orange");
        javafx.scene.control.RadioMenuItem cgreen = new javafx.scene.control.RadioMenuItem("Green");
        javafx.scene.control.RadioMenuItem cbrown = new javafx.scene.control.RadioMenuItem("Brown");
         javafx.scene.control.RadioMenuItem cpurple = new javafx.scene.control.RadioMenuItem("Purple");
        javafx.scene.control.RadioMenuItem cgray = new javafx.scene.control.RadioMenuItem("Gray");
         javafx.scene.control.RadioMenuItem cblack = new javafx.scene.control.RadioMenuItem("Black");
        
         
       
            cblue.setToggleGroup(tg3);
            cred.setToggleGroup(tg3); 
            corange.setToggleGroup(tg3);  
            cgreen.setToggleGroup(tg3);
            cbrown.setToggleGroup(tg3);
            
            cpurple.setToggleGroup(tg3);  
            cgray.setToggleGroup(tg3);
            cblack.setToggleGroup(tg3);
         cbrown.setSelected(true);
         
        font.getItems().add(size);
        font.getItems().add(fcolor);
       
        size.getItems().add(s10);
        size.getItems().add(s20);
        size.getItems().add(s30);
        size.getItems().add(s40);
        size.getItems().add(s50);
        size.getItems().add(s60);
        size.getItems().add(s70);
        size.getItems().add(s80);
        size.getItems().add(s90);
        size.getItems().add(s100);
         
        fcolor.getItems().add(cblue);
         fcolor.getItems().add(cred);
          fcolor.getItems().add(corange);
           fcolor.getItems().add(cgreen);
            fcolor.getItems().add(cbrown);
          fcolor.getItems().add(cpurple);
           fcolor.getItems().add(cgray);
            fcolor.getItems().add(cblack);
         
            ///
      
            //HELP
            
        javafx.scene.control.Menu help = new javafx.scene.control.Menu("Info");
        javafx.scene.control.MenuItem about = new javafx.scene.control.MenuItem("About");
           
         help.getItems().add(about);
       
        //SPLITPANES THAT WILL HOLD THE EDITOR AND THE PREVIEW
      final  SplitPane sp = new SplitPane();
      
              
     String hp = scene.heightProperty().toString();
     
        sp.prefWidthProperty().bind(scene.widthProperty());
       // sp.prefHeightProperty().bind(scene.heightProperty());
        sp.setPrefHeight(450); 
        
        
       sp.setStyle("-fx-background-color: steelblue;");

        final String defaultText = "";
                
        HBox vb1 = new HBox();
      final  TextArea   sourceCode = new TextArea();
        sourceCode.setStyle(" -fx-text-fill:brown; -fx-border-color: black; -fx-font-size:13px; -fx-background-color:white;");
          
        sourceCode.setText(defaultText);
      
        //vb1.getChildren().add(sourceCode);

        HBox vb2 = new HBox();
        final WebView preview = new WebView();
       // preview.setPrefHeight(480);
       // vb2.getChildren().add(preview);
preview.setStyle("-fx-border-color: black;");
       preview.getEngine().loadContent(defaultText);
       
       
       
       vb2.setStyle("-fx-border-color:  gray; -fx-font-size:15px; -fx-border-width:2;");
    
       
        sp.getItems().add(sourceCode);
        sp.getItems().add(preview);

        gp.add(mbar, 0, 0);
        gp.add(sp, 0, 2);
       // Text copyt = new Text(0, 0, "HTML5 editor and live preview");
                   
        //HBox ch = new HBox();        
       // ch.getChildren().add(copyt);
       // gp.add(ch, 0, 8);  
        
       GridPane.setHalignment(status,HPos.LEFT);
        gp.add(status,0,1);

        //The KeyCode event
        
        sourceCode.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent ke) {
               
                ///..
             if(autosave){
                KeyCode code = ke.getCode();
               
                 if(ke.getCode() == code.SPACE){
                    saveFile(sourceCode,preview);
                    
                }
                 else
                 {
                     preview.getEngine().loadContent(sourceCode.getText());
                   status.setText("Press SPACE BAR if images or links do not load.\nMode :Auto save mode.");
                 }
             }
             else
             {
                  preview.getEngine().loadContent(sourceCode.getText());
               status.setText("");
             }
                 
            }
        });
        
         
        

        /*
         <p>Events for the files menuitems:</p>
        
         */
        NEW.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        NEW.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                savedfilePath = "";
                sourceCode.setText(defaultText);
                preview.getEngine().loadContent(sourceCode.getText());
                saveas.setDisable(true);
            }
        });

        open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        open.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                loadFromLocalDisk(sourceCode, preview);

                //open file and load it on the textarea and webview
            }
        });

        save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        save.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
        
              
          
                saveFile(sourceCode,preview);
        
                //save the file
            }
        });

        saveas.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                saveFileas(sourceCode,preview);
                //save the file as...
            }
        });

        quit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
        quit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        /*   END OF EVENTS FOR files menu*/
        /*Event for Insert menu*/
        textarea.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                tc++;
                
               sourceCode.appendText("<textarea rows=\"10\"   cols=\"15\"   id = \"txtArea" + tc + "\" >Default Text</textarea>");
                preview.getEngine().loadContent(sourceCode.getText());
                 
            }
        });
        
        input.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                ic++;
               sourceCode.appendText("<Input type =\"text\"  placeholder =\"default value\"   id = \"txt" + ic + "\" />");
                preview.getEngine().loadContent(sourceCode.getText());
                 
                 
            }
        });
        
        select.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                sc++;
               sourceCode.appendText("<select id = \"select" + sc + "\">\n<option>Option1</option>\n <option>Option2</option>\n<option>Three</option>\n </select>");
                preview.getEngine().loadContent(sourceCode.getText());
                  
                 
            }
        });
        
         
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                bc++;
               sourceCode.appendText("<button  id = \"btn" + bc + "\" >Default value </button>");
                preview.getEngine().loadContent(sourceCode.getText());
                 
                 
            }
        });
        /*  END OF EVENTS FOR Insert Menu */

         /*Events for Autosave menu*/
        
        autosaveon.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
               autosave = true;
            }
        });
         autosaveoff.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
               autosave = false;
            }
        });
         
         
        
        /*End of events for Autosave menu*/
         
          //EVENTS FOR FONT SIZES..
        s10.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle("-fx-font-size:10px;  -fx-text-fill:steelblue;");
            }
        });
        s20.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle("-fx-font-size:15px; -fx-text-fill:steelblue;");
            }
        });
        s30.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
               
                sourceCode.setStyle("-fx-font-size:20px; -fx-text-fill:steelblue;");
            }
        });
        s40.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle("-fx-font-size:25px; -fx-text-fill:steelblue;");
            }
        });
        s50.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle("-fx-font-size:30px; -fx-text-fill:steelblue;");
            }
        });
        s60.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle("-fx-font-size:35px; -fx-text-fill:steelblue;");
            }
        });
        s70.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle("-fx-font-size:40px; -fx-text-fill:steelblue;");
            }
        });
        s80.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle("-fx-font-size:45px; -fx-text-fill:steelblue;");
            }
        });
        s90.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle("-fx-font-size:50px; -fx-text-fill:steelblue;");
            }
        });
        s100.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle("-fx-font-size:60px; -fx-text-fill:steelblue;");
            }
        });
        
       
        ///END OF EVENT FOR FONT SIZE SUB MENU....///
        
        //EVENTS FOR COLOR SUB MENU
              cblue.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle(" -fx-text-fill:steelblue;");
            }
        });
        
          cred.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle(" -fx-text-fill:red;");
            }
        });
            corange.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle(" -fx-text-fill:orange;");
            }
        });
            
              cgreen.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle(" -fx-text-fill: green;");
            }
        });
                cbrown.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle("-fx-text-fill:brown;");
            }
        });
                cpurple.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle("-fx-text-fill:purple;");
            }
        });
                cgray.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle("-fx-text-fill:gray;");
            }
        });
                
                cblack.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                sourceCode.setStyle("-fx-text-fill:black;");
            }
        });
        //END OF EVENTS FOR COLOR SUB MENU
                
                //ABT EVENT
                
                  about.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                JOptionPane.showMessageDialog(null,"Believe Web Editor is a simple Text editor that comes with a live preview browser that lets you see the result live as you type (no need to refresh). \n It acts as both a text editor and a web editor. \n Believe Web Editor is light-weight and has an auto-save capability which saves your work automatically. \n You can disable or enable the auto-save mode. \n\n Developer: Oluyale David. \n oluyaled@gmail.com","Info",1);
                
                
            }
        });
                  
                  
                  //v and h
                  
                  V.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                 sp.setOrientation(Orientation.HORIZONTAL);
        
            }
        });
                  
                     H.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                
                 sp.setOrientation(Orientation.VERTICAL);
        
            }
        });
                  
        /////..........///////
                     
                     //CODE EXPLANATOR
                    final Explanator1 exp1 = new Explanator1();
                     
                     vexp.setOnAction( new EventHandler<ActionEvent>(){
            
          @Override
          public void handle(ActionEvent ae)
            {
                 scode = sourceCode.getText();
                 
                w2.getEngine().loadContent(exp1.analyseCode(scode));
                primaryStage2.show();
            }
        });
        mbar.getMenus().addAll(files, Insert,Autosave,font,Orien,help);
         mbar.prefWidthProperty().bind(scene.widthProperty());
       mbar.setStyle("-fx-background-color:rgb(50,50,50);");
        
     
                    root2.getChildren().add(gp2);
                    primaryStage2.setScene(scene2);
        root.getChildren().add(gp);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    void loadFromLocalDisk(TextArea s, WebView w) {

        JFileChooser chooseFileToOpen = new JFileChooser();
        int openDialog = chooseFileToOpen.showOpenDialog(null);

        sf = chooseFileToOpen.getSelectedFile();

        if (openDialog == JFileChooser.CANCEL_OPTION) {
            return;
        } else {

            try {
                File fileToOpen = chooseFileToOpen.getSelectedFile();
                String filePath = fileToOpen.toString();
                FileInputStream fis = new FileInputStream(filePath);

                int c;
                String txt = "";
                while ((c = fis.read()) != -1) {
                    txt += (char) c;
                }
                savedfilePath = filePath;
                File f = new File(savedfilePath);
                s.setText(txt);
               // w.getEngine().loadContent(txt);
                w.getEngine().load(f.toURI().toURL().toString());
            } catch (Exception e) {

            }

        }
    }

    private void saveFile(TextArea s, WebView w) {
      saveas.setDisable(false);
        JFileChooser savechooser = new JFileChooser();

        if (savedfilePath != "" && new File(savedfilePath).exists()) {
      
            try {
                FileWriter writer2 = new FileWriter(savedfilePath);
                writer2.write(s.getText());
                writer2.flush();
                writer2.close();
               
                w.getEngine().load(new File(savedfilePath).toURI().toURL().toString());
             
               // JOptionPane.showMessageDialog(null, "File:"+savedfilePath+" Updated!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Oops! An error occured");
            }

        } else {

            int saveDialog = savechooser.showSaveDialog(null);
            if (saveDialog == JFileChooser.CANCEL_OPTION) {
                return;
            } else if (saveDialog == JFileChooser.APPROVE_OPTION) {

                try {

                    String fileName;
                    File Dir;
                    Dir = savechooser.getCurrentDirectory();
                    String file22 = savechooser.getSelectedFile() + "";
                    savedfilePath = file22;
                    FileWriter writer = new FileWriter(file22);
                    writer.write(s.getText());
                    writer.flush();
                    writer.close();

                w.getEngine().load(new File(savedfilePath).toURI().toURL().toString());
                } catch (Exception e) {

                }
                
             
            } 

        };
    }

    private void saveFileas(TextArea s1, WebView w) {

          if(savedfilePath !=""  && new File(savedfilePath).exists()){
        JFileChooser savechooser = new JFileChooser();
        int saveDialog = savechooser.showSaveDialog(null);
        if (saveDialog == JFileChooser.CANCEL_OPTION) {
            return;
        } else if (saveDialog == JFileChooser.APPROVE_OPTION) {
            try {
                String fileName;
                File Dir;
                Dir = savechooser.getCurrentDirectory();
                String file = savechooser.getSelectedFile() + "";

                FileWriter writer = new FileWriter(file);
                writer.write(s1.getText());
                writer.flush();
                writer.close();
                
               // w.getEngine().load(new File(savedfilePath).toURI().toURL().toString());
            } catch (Exception e) {
                //.setText("Could not save file" +e);
            }
        } else {
        };}
    }
    //////EOF 
}
