package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Scanner;

public class NAGAD {
    public static void nagad(WebDriver driver) throws InterruptedException {
        WebElement nagad_button = driver.findElement(By.id("nagad_button"));

        // Click the button
        nagad_button.click();

        System.out.println(" Payment through NAGAD ");

        String Url2 = driver.getCurrentUrl();
        System.out.println("URL of the next page: " + Url2);
        System.out.println("Title of the current page: " + driver.getTitle());

        //String inputNumbers = "01621215877"; // Example numbers to input
        Thread.sleep(6000);



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


        // Prompt the user to enter OTP manually
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the OTP: ");
        String otp = scanner.nextLine();



        // Find the OTP input field and enter the OTP manually
        WebElement otpField = driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[1]/input"));
        otpField.sendKeys(otp);
        WebElement button2=driver.findElement(By.xpath("/html/body/div/div/div[4]/form/div[3]/button[1]"));

        button2.click();
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
