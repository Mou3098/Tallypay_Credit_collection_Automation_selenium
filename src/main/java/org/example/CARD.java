package org.example;

import java.lang.String;
import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CARD {
    public static void CARDD(WebDriver driver) {
        //WebDriver driver =driver.getCurrentUrl();
        WebElement card_button = driver.findElement(By.id("card_button"));

        // Click the button
        card_button.click();

        System.out.println(" Payment through CARD ");

        String Url2 = driver.getCurrentUrl();
        System.out.println("URL of the next page: " + Url2);
        System.out.println("Title of the current page: " + driver.getTitle());

        WebElement input_name = driver.findElement(By.xpath("/html/body/input[1]"));
        String card_holder_name= "Mitu";
        input_name.sendKeys(card_holder_name);

        BigDecimal card_number = new BigDecimal("2223000000000007");
        WebElement card_no= driver.findElement(By.xpath("/html/body/input[2]"));
        card_no.sendKeys(String.valueOf(card_number));

        WebElement ex_month= driver.findElement(By.xpath("/html/body/input"));
        int month=01;
        ex_month.sendKeys(String.valueOf(month));

        WebElement ex_year= driver.findElement(By.xpath("/html/body/input"));
        int year=39;
        ex_year.sendKeys(String.valueOf(year));

        WebElement cvc= driver.findElement(By.id("securityCode"));
        int code=100;
        cvc.sendKeys(String.valueOf(code));


        WebElement pay = driver.findElement(By.id("pay-label"));
        pay.click();

        System.out.println("incomplete as it is not working now");

        // incomplete as it is not working now
    }

}
