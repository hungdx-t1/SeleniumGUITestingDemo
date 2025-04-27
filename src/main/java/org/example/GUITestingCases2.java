package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GUITestingCases2 {

    // Phương thức đợi trang web load hẳn sau đó test
    public static WebElement waitForElement(WebDriver drv, By locator) {
        WebDriverWait wait = new WebDriverWait(drv, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /*
        Test case 1: Kiểm tra thanh điền username đang hiển thị
    */
    public static boolean testCase1(WebDriver drv) {
        try {
            WebElement usernameBox = waitForElement(drv, By.id("username"));
            if(usernameBox.isDisplayed()) {
                System.out.println("Thanh điền tên đăng nhập được hiển thị");
                return true;
            } else {
                System.out.println("Thanh điền tên đăng nhập không được hiển thị");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Không tìm thấy thanh điền tên đăng nhập: " + e.getMessage());
            return false;
        }
    }

    /*
        Test case 2: Kiểm tra thanh điền password đang hiển thị
    */
    public static boolean testCase2(WebDriver drv) {
        WebElement passwordBox = drv.findElement(By.name("password"));
        if(!passwordBox.isDisplayed()) {
            System.out.println("Thanh điền mật khẩu không được hiển thị");
            return false;
        }
        return true;
    }

    /*
        Test case 3: Kiểm tra nút Đăng nhập (submit) có thể nhấp
     */
    public static boolean testCase3(WebDriver drv) {
        try {
            WebElement button = drv.findElement(By.className("btn-primary"));
            // or
            // WebElement button = drv.findElement(By.xpath("//input[@type='submit' and @value='Đăng nhập']"));

            // Web:
            // <input type="submit" class="btn btn-primary btn-block" value="Đăng nhập" onclick="ValidateUser()">

            if (button.isDisplayed() && button.isEnabled()) {
                button.click();
                return true;
            } else {
                System.out.println("Nút Đăng nhập không thể nhấp");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Không tìm thấy nút Đăng nhập: " + e.getMessage());
            return false;
        }
    }

    /*
        Test case 4: Kiểm tra nút Đăng nhập có phải màu xanh tuyền không
     */
    public static boolean testCase4(WebDriver drv) {
        try {
            WebElement button = drv.findElement(By.className("btn-primary"));
            String bgColor = button.getCssValue("background-color");
            System.out.println("Test Case 4: Màu nền của nút đăng nhập là: " + bgColor);
            if("rgba(66, 127, 237, 1)".equalsIgnoreCase(bgColor)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Không tìm thấy nút Đăng nhập: " + e.getMessage());
            return false;
        }
    }

    /*
        Test case 5: 
     */
}
