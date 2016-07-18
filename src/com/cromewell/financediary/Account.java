package com.cromewell.financediary;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Jo on 11.07.2016.
 * @author Cromewell
 */
public class Account {

    private String name;
    private SimpleIntegerProperty moneyProperty = new SimpleIntegerProperty();

    /**
     *
     * @param money     money on the account
     * @param name      name of the account owner
     */

    Account(int money, String name) {
        this.moneyProperty.set(money);
        this.name = name;
    }

    /**
     *
     * @param sum    is the int which will be added to the money
     */
    public void addSum(int sum){
        moneyProperty.set(moneyProperty.get()+sum);
    }

    //GETTERS AND SETTER BELOW//

    public int getMoneyProperty() {
        return moneyProperty.get();
    }

    public SimpleIntegerProperty moneyPropertyProperty() {
        return moneyProperty;
    }

    public void setMoneyProperty(int moneyProperty) {
        this.moneyProperty.set(moneyProperty);
    }

    public String getName() {
        return name;
    }

}
