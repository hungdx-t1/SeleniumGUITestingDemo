package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GUITestingCases {

    public static WebElement waitForElement(WebDriver drv, By locator) {
        WebDriverWait wait = new WebDriverWait(drv, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /*
        Test case 1: Kiểm tra thanh tìm kiếm hiện đúng
    */
    public static boolean testCase1() {
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.google.com");
        try {
            WebElement searchBox = driver.findElement(By.name("q"));
            if(searchBox.isDisplayed()) {
                System.out.println("TC_GUI_001: Ô tìm kiếm hiển thị đúng.");
            } else {
                System.out.println("TC_GUI_001: Ô tìm kiếm không hiển thị.");
                return false;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Không tìm thấy phần tử input[name='q']");
            return false;
        } finally {
            driver.quit();
        }
        return true;
    }

    /*
        Test case 2: Kiểm tra placeholder
    */
    public static boolean testCase2() {
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.google.com");

        WebElement searchBox = driver.findElement(By.name("q"));

        String ariaLabel = searchBox.getAttribute("aria-label");

        if (ariaLabel != null && ariaLabel.toLowerCase().contains("search")) {
            System.out.println("TC_GUI_002: aria-label hợp lệ: " + ariaLabel);
        } else {
            System.out.println("TC_GUI_002: aria-label không đúng hoặc thiếu.");
            return false;
        }

        driver.quit();
        return true;
    }

    /*
        Test case 3: test màu sắc của nút tìm kiếm
    */
    public static boolean testCase3() {
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.google.com");

        WebElement button = driver.findElement(By.name("btnK")); // nút Google Search
        String bgColor = button.getCssValue("background-color");
        System.out.println("TC_GUI_003: Màu nền nút là: " + bgColor);

        driver.quit();
        return true;
    }
}
