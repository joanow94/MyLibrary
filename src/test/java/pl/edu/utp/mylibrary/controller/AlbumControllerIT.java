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
public class AlbumControllerIT {

    private WebDriver browser;
    private int rowCount = 0;

    @Before
    public void setup() {
        browser = new FirefoxDriver();
    }

    @Ignore
    @Test
    public void startTest() {
        browser.get("http://localhost:8080/MyLibrary/");

        (new WebDriverWait(browser, 30)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                d.findElement(By.id("loginField")).sendKeys("admin");
                d.findElement(By.id("passwordField")).sendKeys("x");
                d.findElement(By.id("loginButton")).click();

                d.findElement(By.id("seeAlbums")).click();
                rowCount = browser.findElements(By.xpath("//table[@id='DataTable']/tbody/tr")).size();
                d.findElement(By.id("addAlbum")).click();

                d.findElement(By.id("title")).sendKeys("Nevemind");
                d.findElement(By.id("artist")).sendKeys("Nirvana");
                d.findElement(By.id("year")).sendKeys("1994");
                d.findElement(By.id("genre")).sendKeys("Grunge");

                d.findElement(By.id("add")).click();

                return true;
            }
        });

        assertEquals(rowCount + 1, browser.findElements(By.xpath("//table[@id='DataTable']/tbody/tr")).size());
    }

    @After
    public void tearDown() {
        browser.close();
    }
}
