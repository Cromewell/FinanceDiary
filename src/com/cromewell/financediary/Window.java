package com.cromewell.financediary;


import com.cromewell.financediary.utils.Utils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;



/**
 * @author Cromewell
 * Created by Jo on 11.07.2016.
 */
public class Window extends Application{

    private static Account acc = new Account(0, "default"); //default acc at start

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initWindow(primaryStage);
    }

    //builds and displayes the window
    private static void initWindow(Stage primaryStage){
        primaryStage.setTitle("FinanceDiary");
        primaryStage.setResizable(false);
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 700, 500);
        primaryStage.setScene(scene);

        Label money = new Label("0");
        money.setFont(new Font("Arial", 64));
        money.textProperty().bind(acc.moneyPropertyProperty().asString());

        TextField change = new TextField();
        change.setPrefSize(200, 60);
        change.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){ //if return was typed
                String text = change.getText();
                acc.addSum(Utils.stringToInt(text)); //account money += sum
            }
        });
        MenuBar menuBar = new MenuBar();
        Menu account = new Menu("Account");

        MenuItem newAccount = new MenuItem("create new Acc");
        newAccount.setOnAction(event -> {
            acc = new Account(0, NamePopup.getName()); //popup where the user enters his name
            money.textProperty().bind(acc.moneyPropertyProperty().asString()); //binds the label to the money
            System.out.println(acc.getName()+" has been created");
        });
        MenuItem saveAccount = new MenuItem("save Acc");
        saveAccount.setOnAction(event -> Utils.saveToFile(acc)); //writes the data to a file, chosen by the user

        MenuItem loadAccount = new MenuItem("load Acc");
        loadAccount.setOnAction(event -> Utils.loadFromFile(acc));

        //Packing//
        account.getItems().addAll(newAccount, saveAccount, loadAccount);
        menuBar.getMenus().addAll(account);
        VBox menu = new VBox();
        menu.getChildren().addAll(menuBar);

        //Positioning//
        borderPane.setCenter(money);
        borderPane.setBottom(change);
        borderPane.setTop(menu);


        primaryStage.show();
    }

}
