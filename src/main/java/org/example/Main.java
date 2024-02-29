package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.String;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {



        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/conf.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String url = properties.getProperty("urll");

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Maximize window
        driver.manage().window().maximize();

        // Open URL
        driver.get(url);

        // Find the parent div element
        WebElement parentDiv = driver.findElement(By.className("d-flex"));

        // Find the child div elements containing the text
        WebElement textElement1 = parentDiv.findElement(By.className("bn-medium"));
        WebElement textElement2 = parentDiv.findElement(By.className("bn-bold"));

        // Get the text content of each element
        String text1 = textElement1.getText();
        String text2 = textElement2.getText();

        // Combine the text content to get the money amount
        String moneyAmount = text1 + " " + text2;

        // Output the money amount
        System.out.println("Money amount: " + moneyAmount);

        // Find the button element by class name
        WebElement button = driver.findElement(By.xpath("/html/body/div[1]/div/div[5]/div[3]/a/span"));

        // Click the button
        button.click();
        String Url2 = driver.getCurrentUrl();
        System.out.println("URL of the next page: " + Url2);
        WebElement inputElement = driver.findElement(By.id("id_amount"));
        int input_amount=10;
        inputElement.sendKeys(String.valueOf(input_amount));
        WebElement nextButton = driver.findElement(By.id("next_button"));
        nextButton.click();

        System.out.println("Payable Amount : "+ input_amount);
        String txn_medium = properties.getProperty("txn_medium");

        System.out.println(" txn medium given : "+txn_medium);

        switch (txn_medium) {
            case "DBBL" -> DBBL.dbbl(driver);
            case "NAGAD" -> NAGAD.nagad(driver);
            case "CARD" -> CARD.CARDD(driver);
            default -> System.out.println(" No proper txn medium given ");
        }




    }
}