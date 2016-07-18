package com.cromewell.financediary;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Jo on 11.07.2016.
 * @author Cromewell
 */
public class Account {

    private String name;
    private SimpleStringProperty moneyProperty = new SimpleStringProperty();

    /**
     *
     * @param money     money on the account
     * @param name      name of the account owner
     */

    Account(int money, String name) {
        this.moneyProperty.set(String.valueOf(money));
        this.name = name;
    }

    /**
     *
     * @param sum    is the int which will be added to the money
     */
    public void addSum(int sum){
        moneyProperty.set(String .valueOf(Integer.parseInt(moneyProperty.get())+sum));
    }

    //GETTERS AND SETTER BELOW//

    public String getMoneyProperty() {
        return moneyProperty.get();
    }

    public SimpleStringProperty moneyPropertyProperty() {
        return moneyProperty;
    }

    public void setMoneyProperty(int moneyProperty) {
        this.moneyProperty.set(String.valueOf(moneyProperty));
    }

    public String getName() {
        return name;
    }

}
