package database;

import database.ManageModel.ManageCustomer;

public class Main {
    public static void main(String[] args) {

        ManageCustomer manageCustomer = new ManageCustomer();
        manageCustomer.addCustomer("id", "email", "firstname", true, "name");
        manageCustomer.updateCustomer("id", "test update");
        manageCustomer.deleteCustomer("id");
        /**/
    }

    private static void insertDataToCustomerTable(ManageCustomer manageCustomer)
    {

    }
}
