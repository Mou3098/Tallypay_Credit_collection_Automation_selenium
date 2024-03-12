/*package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;*/

package org.example;

import graphql.VisibleForTesting;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class DBBL {
    public WebDriver driver;
    Properties properties = new Properties();

    private String np_lg_txn=null;
    private String payment_gw=null;
    private String txn_num=null;
    private String sofol_text=null;
    public DBBL()
    {
        driver = new ChromeDriver();
        try (FileInputStream fis = new FileInputStream("src/main/resources/conf.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    @Test(priority = 0)
    public void credit_collection_page_display() throws InterruptedException {
        String credit_collection_url = properties.getProperty("urll");
        driver.get(credit_collection_url);
        Thread.sleep(2000);
    }

    @Test(priority = 1)
    public void porishodh_kori_button() throws InterruptedException {
        WebElement btn_porishodh_kori = driver.findElement(By.xpath("/html/body/div[1]/div/div[5]/div[3]/a/span"));
        btn_porishodh_kori.click();
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void amount_input_and_proceed() throws InterruptedException {
        WebElement amount_input = driver.findElement(By.xpath("/html/body/div/div[1]/form/div[1]/div[2]/input"));
        amount_input.sendKeys("10");

        WebElement btn_next = driver.findElement(By.xpath("/html/body/div/div[1]/form/div[4]/button"));
        btn_next.click();
        Thread.sleep(2000);
    }


    @Test(priority = 3)
    public void select_dbbl() throws InterruptedException {
        WebElement rocket_button = driver.findElement(By.id("rocket_button"));
        // Click the button
        rocket_button.click();

        System.out.println(" Payment through DBBL ");

        String Url2 = driver.getCurrentUrl();
        System.out.println("URL of the next page: " + Url2);
        System.out.println("Title of the current page: " + driver.getTitle());

        Thread.sleep(6000);
    }

    @Test(priority = 4)
    public void dbbl_number_input() {

        WebElement mobile = driver.findElement(By.id("cardnr"));
        String num = "019800016669";
        mobile.sendKeys(num);
    }

    @Test(priority = 5)
    public void password_insert() {

        WebElement pass = driver.findElement(By.id("cvc2"));
        String p = "1234";
        pass.sendKeys(p);

        WebElement button1 = driver.findElement(By.id("pay"));
        button1.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 6)
    public void security_code_insert() throws InterruptedException, JSONException {

        Thread.sleep(35000);

        String filePath = "D:/m/Selenium_project/credit_collection_automation/src/main/java/org/example/dbbl_security_code.json";
        String jsonContent = null;
        try {
            jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Parse JSON string to JSONObject
        JSONObject jsonObject = new JSONObject(jsonContent);

        // Get integer value from JSONObject
        int otp = jsonObject.optInt("code");

// Check if the key exists in the JSON object
        if (otp == 0 && !jsonObject.has("code")) {
            throw new RuntimeException("Key 'code' not found in JSON object");
        }

        WebElement otp_l = driver.findElement(By.id("passCode"));
        otp_l.sendKeys(String.valueOf(otp));

        WebElement button2 = driver.findElement(By.xpath("/html/body/div/div/form/div[2]/div/div/div[1]/div[1]/div/div/table/tbody/tr[3]/td[3]/input"));

        // Click the button
        button2.click();
    }

    @Test(priority = 7)
    public void success_page() throws InterruptedException {
        WebElement txt1 = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]"));
        String text1 = txt1.getText();
        System.out.println(text1);
        WebElement txt2 = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]"));
        String txt_t = txt2.getText();
        System.out.println(txt_t);

        this.sofol_text=text1;

        Thread.sleep(15000);
    }

    @Test(priority = 8)
    public void txn_number_fetch(){
        if(sofol_text.equals("ধন্যবাদ।")){
        WebElement txn_id = driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[2]"));
        String txn_num_local=txn_id.getText();
        System.out.println(" Transaction_number : "+txn_num_local);

        this.txn_num=txn_num_local;}
        else{
            System.out.println("No Txn num fetch from here.");
        }


    }

    @Test(priority = 9)
    public void np_txn_log_status_test(){

        String url_of_db = "jdbc:postgresql://10.9.0.77:5432/backend_db";
        String username = "mitu";
        String password = "!Mitu@PSL123";
/*
        if(txn_num == null)
        {
            System.out.println();
            throw
        }*/

        // SQL query to retrieve column value
        String query = String.format("SELECT * FROM np_txn_log WHERE transaction_number = '%s'", txn_num);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection
            connection = DriverManager.getConnection(url_of_db, username, password);

            // Create statement
            statement = connection.createStatement();

            // Execute query
            resultSet = statement.executeQuery(query);

            // Process query result
            if (resultSet.next()) {
                String status = resultSet.getString("status");
                System.out.println("Status: " + status);
                System.out.println("Successfully txn completed .");
                this.np_lg_txn=status;
            } else {
                System.out.println("No data found for the transaction number: " + txn_num);
            }
        } catch (SQLException e) {
            // Handle SQL exception
            System.out.println("cannot proceeded");
            e.printStackTrace();
        } finally {
            // Close result set, statement, and connection in the finally block
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    @Test(priority = 10)
    public void nobopay_payment_gw_status_test(){

        String url_of_db = "jdbc:postgresql://10.9.0.77:5432/nobopay_payment_gw";
        String username = "mitu";
        String password = "!Mitu@PSL123";

        String query2=String.format("SELECT * FROM nagad_txn nt WHERE nt.tp_transaction_number = '%s'", txn_num);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection
            connection = DriverManager.getConnection(url_of_db, username, password);

            // Create statement
            statement = connection.createStatement();

            // Execute query
            resultSet = statement.executeQuery(query2);

            // Process query result
            if (resultSet.next()) {
                String status = resultSet.getString("status");
                System.out.println("Status: " + status);
                System.out.println("Successfully txn completed .");
                this.payment_gw=status;
            } else {
                System.out.println("No data found for the transaction number: " + txn_num);
            }
        } catch (SQLException e) {
            // Handle SQL exception
            System.out.println("cannot proceeded");
            e.printStackTrace();
        } finally {
            // Close result set, statement, and connection in the finally block
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test(priority = 11)
    public void txn_status(){
        if (np_lg_txn == null && payment_gw == null) {
            System.out.println("Both np_lg_txn and payment_gw are null.");
        }
        else if (np_lg_txn == null) {
            System.out.println("np_lg_txn is null.");
        }
        else if (payment_gw == null) {
            System.out.println("payment_gw dbbl_txn is null.");
        }
        else if (np_lg_txn.equals("COMPLETE") && payment_gw.equals("SUCCESS")) {
            System.out.println("Txn successful from both Backend and Payment GW .");
        } else if (!np_lg_txn.equals("COMPLETE") && payment_gw.equals("SUCCESS")) {
            System.out.println("Failed from Backend");
        } else if ( !payment_gw.equals("SUCCESS") && np_lg_txn.equals("COMPLETE")) {
            System.out.println("Failed from Payment GW");
        } else {
            System.out.println("Failed from both Backend and Payment GW .");
        }
    }

}
