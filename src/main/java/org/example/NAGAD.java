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

public class NAGAD {
    public WebDriver driver;
    Properties properties = new Properties();

    private String np_lg_txn=null;
    private String payment_gw=null;
    private String txn_num=null;

    private String sofol_text=null;
    public NAGAD()
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
    public void select_nagad() throws InterruptedException {
        System.out.println("NAGAD");
        WebElement nagad_button = driver.findElement(By.id("nagad_button"));

        // Click the button
        nagad_button.click();

        System.out.println(" Payment through NAGAD ");

        String Url2 = driver.getCurrentUrl();
        System.out.println("URL of the next page: " + Url2);
        System.out.println("Title of the current page: " + driver.getTitle());

        Thread.sleep(6000);
    }

    @Test(priority = 4)
    public void nagad_number_input()
    {

        // give each digits of nagad number
        WebElement input1 = driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/div[1]/input[1]"));
        input1.sendKeys("0");
        WebElement input2= driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/div[1]/input[2]"));
        input2.sendKeys("1");
        WebElement input3= driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/div[1]/input[3]"));
        input3.sendKeys("6");
        WebElement input4= driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/div[1]/input[4]"));
        //WebElement input4= driver.findElement(By.xpath(""));
        input4.sendKeys("2");
        WebElement input5= driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/div[1]/input[5]"));
        input5.sendKeys("1");
        WebElement input6= driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/div[1]/input[6]"));
        input6.sendKeys("2");
        WebElement input7= driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/div[1]/input[7]"));
        input7.sendKeys("1");
        WebElement input8= driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/div[1]/input[8]"));
        input8.sendKeys("5");
        WebElement input9= driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/div[1]/input[9]"));
        input9.sendKeys("8");
        WebElement input10= driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/div[1]/input[10]"));
        input10.sendKeys("7");
        WebElement input11= driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/div[1]/input[11]"));
        input11.sendKeys("7");

        WebElement button1= driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[3]/button[1]"));

        button1.click();

    }

    @Test(priority = 5)
    public void otp_insert() throws JSONException, InterruptedException {
        // Prompt the user to enter OTP manually
        /*Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the OTP: ");
        String otp = scanner.nextLine();*/
        Thread.sleep(45000);

        String filePath_otp = "D:/m/Selenium_project/credit_collection_automation/src/main/java/org/example/nagad_otp.json";
        String jsonContent = null;
        try {
            jsonContent = new String(Files.readAllBytes(Paths.get(filePath_otp)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Parse JSON string to JSONObject
        JSONObject jsonObject = new JSONObject(jsonContent);

        // Get integer value from JSONObject
        int otp = jsonObject.optInt("otp1");

// Check if the key exists in the JSON object
        if (otp == 0 && !jsonObject.has("otp1")) {
            throw new RuntimeException("Key 'otp1' not found in JSON object");
        }


        // Find the OTP input field and enter the OTP manually
        WebElement otpField = driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/input"));
        otpField.sendKeys(String.valueOf(otp));
        WebElement button2=driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[3]/button[1]"));

        button2.click();

    }
    @Test(priority = 6)
    public void password_insert()
    {
        // password inserted
        WebElement pass1 = driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/div/input[1]"));
        pass1.sendKeys(String.valueOf(1));
        WebElement pass2 = driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/div/input[2]"));
        pass2.sendKeys(String.valueOf(2));
        WebElement pass3 = driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/div/input[3]"));
        pass3.sendKeys(String.valueOf(1));
        WebElement pass4 = driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/div/input[4]"));
        pass4.sendKeys(String.valueOf(2));

        WebElement button3 = driver.findElement(By.id("confirmButton"));

        // Click the button
        button3.click();
    }

    @Test(priority = 7)
    public void success_page() throws InterruptedException {
        String Url_of_txn_state = driver.getCurrentUrl();
        System.out.println("URL of the next page: " + Url_of_txn_state);
        System.out.println("Title of the current page: " + driver.getTitle());

        WebElement tnx = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/span[1]"));
        String text1=tnx.getText();
        System.out.println(text1);
        WebElement suc = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/span[2]"));
        String text2=suc.getText();
        System.out.println(text2);

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

       /* String txn_num="MMMMS1L8F";*/
        // SQL query to retrieve column value
        String query = String.format("SELECT * FROM np_txn_log WHERE transaction_number = '%s'", txn_num);
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
