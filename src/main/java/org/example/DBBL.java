package org.example;

import java.lang.String;
import java.math.BigDecimal;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class DBBL {
    public static void dbbl(WebDriver driver) throws InterruptedException {

        WebElement rocket_button = driver.findElement(By.id("rocket_button"));
        // Click the button
        rocket_button.click();

        System.out.println(" Payment through DBBL ");

        String Url2 = driver.getCurrentUrl();
        System.out.println("URL of the next page: " + Url2);
        System.out.println("Title of the current page: " + driver.getTitle());

        Thread.sleep(6000);

        WebElement mobile = driver.findElement(By.id("cardnr"));
        String num ="019800016669";
        mobile.sendKeys(num);

        WebElement pass = driver.findElement(By.id("cvc2"));
        String p ="1234";
        pass.sendKeys(p);

        WebElement button1 = driver.findElement(By.id("pay"));
        button1.click();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the Security Code : ");
        String otp = scanner.nextLine();

        WebElement otp_l = driver.findElement(By.id("passCode"));
        otp_l.sendKeys(otp);

        WebElement button2 = driver.findElement(By.xpath("/html/body/div/div/form/div[2]/div/div/div[1]/div[1]/div/div/table/tbody/tr[3]/td[3]/input"));

        // Click the button
        button2.click();
        WebElement sorry = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]"));
        String sorry_t=sorry.getText();
        System.out.println(sorry_t);
        WebElement failed = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]"));
        String failed_t=failed.getText();
        System.out.println(failed_t);



    }

}
