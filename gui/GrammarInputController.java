package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import lr0.LR1Parser;
//import lr1.LR1Parser;
import util.Grammar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GrammarInputController implements Initializable{

    @FXML
    private Label error;

    @FXML
    private TextArea input;

    @FXML
    private ComboBox parser;

    public static String parserKind;

    public static LR1Parser lr0Parser;

    //public static LR1Parser lr1Parser;
    @FXML
    private void handleStart(ActionEvent event) throws IOException {
        if(parser.getValue() == null){
            error.setText("Choose a parser");
            error.setVisible(true);
        }else{
            parserKind = (String)parser.getValue();
            boolean canBeParse = true;
            String grammarText = input.getText();
            Grammar grammar = new Grammar(grammarText);
            if( parser.getValue().equals("LR(1)")){
                lr0Parser = new LR1Parser(grammar);
                 
                    canBeParse = lr0Parser.parserLR1();
               
            }
            if(!canBeParse){
                error.setText("The grammar can not be parsed. choose a different parser or grammar");
                error.setVisible(true);
            }else{
                Button button = (Button)event.getSource();
                Stage stage = (Stage)button.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Output.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        error.setVisible(false);
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        
                        "LR(1)"
                        
                );
        parser.setItems(options);
    }
}
