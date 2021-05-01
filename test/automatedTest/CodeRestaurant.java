/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automatedTest;

import config.AbstractTest;
import config.GeneralDataInialiation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author emmanuel
 */
public class CodeRestaurant extends AbstractTest{
    private WebDriver driver;
    
//    @Test
    public void testSignup(){
        System.setProperty("webdriver.chrome.driver", "/home/emmanuel/NetBeansProjects/Code-Restaurant/test/config/chromedriver");
        driver = new ChromeDriver();
        
        driver.get("http://localhost:8080/Code-Restaurant/signup.jsp");
        
        Assert.assertEquals(driver.getTitle(), "Code | Restaurant | Signup");
        driver.findElement(By.id("fname")).sendKeys("Emmanuel");
        driver.findElement(By.name("lname")).sendKeys("NTIVUGURUZWA");
        driver.findElement(By.name("email")).sendKeys("djntivuguruzwaemmanuel@gmail.com");
        driver.findElement(By.name("phone")).sendKeys("0788596281");
        driver.findElement(By.name("password")).sendKeys("Default");
        driver.findElement(By.name("password2")).sendKeys("Default");
        driver.findElement(By.id("signup")).click();

        Assert.assertEquals(driver.getTitle(), "Code | Restaurant | Signin");
    }
    
//    @Test
    public void testSignupWitExistingAccount(){
        System.setProperty("webdriver.chrome.driver", "/home/emmanuel/NetBeansProjects/Code-Restaurant/test/config/chromedriver");
        driver = new ChromeDriver();
        
        driver.get("http://localhost:8080/Code-Restaurant/signup.jsp");
        
        Assert.assertEquals(driver.getTitle(), "Code | Restaurant | Signup");
        driver.findElement(By.id("fname")).sendKeys("Emmanuel");
        driver.findElement(By.name("lname")).sendKeys("NTIVUGURUZWA");
        driver.findElement(By.name("email")).sendKeys("default@gmail.com");
        driver.findElement(By.name("phone")).sendKeys("0788596281");
        driver.findElement(By.name("password")).sendKeys("Default");
        driver.findElement(By.name("password2")).sendKeys("Default");
        driver.findElement(By.id("signup")).click();

        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals(message, "Account already exist !");
        Assert.assertEquals(driver.getTitle(), "Code | Restaurant | Signup");
    }
    
//    @Test
    public void testSigninWithWrongAccount(){
        System.setProperty("webdriver.chrome.driver", "/home/emmanuel/NetBeansProjects/Code-Restaurant/test/config/chromedriver");
        driver = new ChromeDriver();
        
        driver.get("http://localhost:8080/Code-Restaurant");
        
        driver.findElement(By.name("email")).sendKeys("wrongdefault@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Wrong Password");
        driver.findElement(By.id("login")).click();
        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals(message, "Account not fount !");
    }
    
//    @Test
    public void testSigninWithWrongPassword(){
        System.setProperty("webdriver.chrome.driver", "/home/emmanuel/NetBeansProjects/Code-Restaurant/test/config/chromedriver");
        driver = new ChromeDriver();
        
        driver.get("http://localhost:8080/Code-Restaurant");
        
        driver.findElement(By.name("email")).sendKeys("default@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Emmanuel12345!");
        driver.findElement(By.id("login")).click();
        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals(message, "Wrong Password");
    }
    
//    @Test
    public void testSingin(){
        System.setProperty("webdriver.chrome.driver", "/home/emmanuel/NetBeansProjects/Code-Restaurant/test/config/chromedriver");
        driver = new ChromeDriver();
        
        driver.get("http://localhost:8080/Code-Restaurant");
        
        driver.findElement(By.name("email")).sendKeys("default@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Default");
        driver.findElement(By.id("login")).click();
        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals(driver.getTitle(), "Code | Restaurant | Index");
    }
    
//    @Test
    public void booktableWithWrongAcount(){
        System.setProperty("webdriver.chrome.driver", "/home/emmanuel/NetBeansProjects/Code-Restaurant/test/config/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/Code-Restaurant");
        
        driver.findElement(By.name("email")).sendKeys("default@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Default");
        driver.findElement(By.id("login")).click();
        Assert.assertEquals(driver.getTitle(), "Code | Restaurant | Index");
        
        driver.findElement(By.name("bookTable")).click();
        driver.findElement(By.name("names")).sendKeys("Emmanuel NTIVUGURUZWA");
        driver.findElement(By.name("email")).sendKeys("default@gmail.com");
        driver.findElement(By.name("phone")).sendKeys("0788596281");
        driver.findElement(By.name("dates")).sendKeys("04/07/2021");
        driver.findElement(By.name("time")).sendKeys("14:30 AM");
        driver.findElement(By.name("people")).sendKeys("2");
        driver.findElement(By.name("tid")).sendKeys("3");
        driver.findElement(By.name("message")).sendKeys("Pizza + all Party Food");
        
        driver.findElement(By.xpath("//button[@name='reserveTableBtn']")).submit();
        driver.findElement(By.name("bookTable")).click();
        Assert.assertEquals(driver.findElement(By.id("reservationMessage")).getText(), "");
        
    }
    
    @Test
    public void booktable(){
        System.setProperty("webdriver.chrome.driver", "/home/emmanuel/NetBeansProjects/Code-Restaurant/test/config/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/Code-Restaurant");
        
        driver.findElement(By.name("email")).sendKeys("default@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Default");
        driver.findElement(By.id("login")).click();
        Assert.assertEquals(driver.getTitle(), "Code | Restaurant | Index");
        
        driver.findElement(By.name("bookTable")).click();
        driver.findElement(By.name("names")).sendKeys("Emmanuel NTIVUGURUZWA");
        driver.findElement(By.name("email")).sendKeys("default@gmail.com");
        driver.findElement(By.name("phone")).sendKeys("0788596281");
        driver.findElement(By.name("dates")).sendKeys("04/07/2021");
        driver.findElement(By.name("time")).sendKeys("14:30 AM");
        driver.findElement(By.name("people")).sendKeys("2");
        driver.findElement(By.name("tid")).sendKeys("3");
        driver.findElement(By.name("message")).sendKeys("Pizza + all Party Food");
        
        driver.findElement(By.xpath("//button[@name='reserveTableBtn']")).submit();
        driver.findElement(By.name("bookTable")).click();
        Assert.assertEquals(driver.findElement(By.id("reservationMessage")).getText(), "");
    }
    
    
//    @BeforeTest
    public void clearDefaultData(){
        excute(GeneralDataInialiation.DELETE_ALL_ORDERs);
        excute(GeneralDataInialiation.DELETE_ALL_RESERVATIONs);
        excute(GeneralDataInialiation.DELETE_ALL_ACCOUNTs);
    }
    
//    @BeforeMethod
    public void initialiseDatabaseData(){
        excute(GeneralDataInialiation.INSERT_ACCOUNTs);
        excute(GeneralDataInialiation.PREPARE_RESERVATIONs);
    }
    
//    @AfterMethod
    public void resetDatabase(){
        excute(GeneralDataInialiation.DELETE_ALL_ORDERs);
        excute(GeneralDataInialiation.DELETE_ALL_RESERVATIONs);
        excute(GeneralDataInialiation.DELETE_ALL_ACCOUNTs);
    }
    
//    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
