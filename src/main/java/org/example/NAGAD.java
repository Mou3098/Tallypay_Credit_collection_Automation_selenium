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

public class NAGAD {
    public WebDriver driver;
    Properties properties = new Properties();

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

        //String inputNumbers = "01621215877"; // Example numbers to input
        Thread.sleep(6000);
    }

    @Test(priority = 4)
    public void nagad_number_input()
    {
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
        Thread.sleep(35000);

        String filePath = "D:/m/Selenium_project/credit_collection_automation/src/main/java/org/example/nagad_otp.json";
        String jsonContent = null;
        try {
            jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
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
        String Url3 = driver.getCurrentUrl();
        System.out.println("URL of the next page: " + Url3);
        System.out.println("Title of the current page: " + driver.getTitle());

        WebElement tnx = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/span[1]"));
        String tnx_t=tnx.getText();
        System.out.println(tnx_t);
        WebElement suc = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/span[2]"));
        String suc_t=suc.getText();
        System.out.println(suc_t);

        Thread.sleep(15000);
    }

}
