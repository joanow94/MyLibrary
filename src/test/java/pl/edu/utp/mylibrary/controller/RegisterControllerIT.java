/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.controller;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author nowakowska joanna
 */
public class RegisterControllerIT {

    private WebDriver browser;

    @Before
    public void setup() {
        browser = new FirefoxDriver();
    }

    @Ignore
    @Test
    public void startTest() {

        browser.get("http://localhost:8080/MyLibrary/");

        (new WebDriverWait(browser, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                d.findElement(By.id("registerForm")).click();

                d.findElement(By.id("nameField")).sendKeys("Jan");
                d.findElement(By.id("lastnameField")).sendKeys("Kowalski");
                d.findElement(By.id("loginField")).sendKeys("janek01");
                d.findElement(By.id("passwordField")).sendKeys("zaq12wsx");
                d.findElement(By.id("password2Field")).sendKeys("zaq12wsx");
                d.findElement(By.id("registerButton")).click();
                return true;
            }
        });
        assertEquals("MyLibrary - strona główna", browser.findElement(By.id("homeTitle")).getText());
    }

    @After
    public void tearDown() {
        browser.close();
    }
}
