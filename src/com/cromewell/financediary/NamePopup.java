package com.cromewell.financediary;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Random;


/**
 * Created by Jo on 11.07.2016.
 *
 */
class NamePopup {

    private static String fileName;

    static String getName(){
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Account name");
        stage.initModality(Modality.APPLICATION_MODAL);
        HBox root = new HBox(30);
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);

        TextField name = new TextField("Account name");
        Button ok = new Button("Ok");
        ok.setOnAction(event -> {
            if(!name.getText().equals("")){
                fileName =  name.getText();
                stage.close();

            }else{
                fileName = String.valueOf(new Random().nextInt(9999));
                stage.close();
            }
        });

        root.getChildren().addAll(name, ok);
        stage.showAndWait();

        return fileName;
    }
}
