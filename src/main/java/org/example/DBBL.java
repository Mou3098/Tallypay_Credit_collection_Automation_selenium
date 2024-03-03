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
import java.util.Properties;
import java.util.Scanner;

public class DBBL {
    public WebDriver driver;
    Properties properties = new Properties();

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
    }

    @Test(priority = 6)
    public void security_code_insert() throws InterruptedException, JSONException {

       /* Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the Security Code : ");
        String otp = scanner.nextLine();*/

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
        WebElement sorry = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]"));
        String sorry_t = sorry.getText();
        System.out.println(sorry_t);
        WebElement failed = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]"));
        String failed_t = failed.getText();
        System.out.println(failed_t);
    }

}
