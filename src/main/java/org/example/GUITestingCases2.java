package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
                System.out.println("TC_001: Thanh điền tên đăng nhập được hiển thị");
                return true;
            } else {
                System.out.println("TC_001: Thanh điền tên đăng nhập không được hiển thị");
                return false;
            }
        } catch (Exception e) {
            System.out.println("TC_001: Không tìm thấy thanh điền tên đăng nhập: " + e.getMessage());
            return false;
        }
    }

    /*
        Test case 2: Kiểm tra thanh điền password đang hiển thị
    */
    public static boolean testCase2(WebDriver drv) {
        WebElement passwordBox = drv.findElement(By.name("password"));
        if(!passwordBox.isDisplayed()) {
            System.out.println("TC_002: Thanh điền mật khẩu không được hiển thị");
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
                System.out.println("TC_003: Nút Đăng nhập không thể nhấp");
                return false;
            }
        } catch (Exception e) {
            System.out.println("TC_003: Không tìm thấy nút Đăng nhập: " + e.getMessage());
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
            System.out.println("TC_004: Màu nền của nút đăng nhập là: " + bgColor);
            return "rgba(66, 127, 237, 1)".equalsIgnoreCase(bgColor);
        } catch (Exception e) {
            System.out.println("TC_004: Không tìm thấy nút Đăng nhập: " + e.getMessage());
            return false;
        }
    }

    /*
        Test case 5: Kiểm tra tính hiển thị của logo (trong form đăng nhập)
     */
    public static boolean testCase5(WebDriver drv) {
        try {
            WebElement image = drv.findElement(By.id("logo"));
            if(image.isDisplayed() && image.isEnabled()) {
                Dimension imageSize = image.getSize();
                System.out.println("TC_005: Hình ảnh được hiển thị thành công.");
                System.out.println("TC_005: Kích cỡ hình ảnh: " + imageSize);
                return true;
            } else {
                System.out.println("TC_005: Hình ảnh không được hiển thị");
                return false;
            }
        } catch (Exception e) {
            System.out.println("TC_005: Không tìm thấy hình ảnh: " + e.getMessage());
            return false;
        }
    }

    /*
        Test case 6: Kiểm tra placeholder của ô nhập username đúng là "Tên đăng nhập"
    */
    public static boolean testCase6(WebDriver drv) {
        try {
            WebElement usernameInput = drv.findElement(By.id("username"));
            String placeholder = usernameInput.getAttribute("placeholder");
            System.out.println("TC_006: Placeholder của ô username: " + placeholder);
            return "Tên đăng nhập".equals(placeholder);
        } catch (Exception e) {
            System.out.println("TC_006: Không tìm thấy ô username: " + e.getMessage());
            return false;
        }
    }

    /*
        Test case 7: Kiểm tra placeholder của ô nhập password đúng là "Mật khẩu"
     */
    public static boolean testCase7(WebDriver drv) {
        try {
            WebElement passwordInput = drv.findElement(By.id("password"));
            String placeholder = passwordInput.getAttribute("placeholder");
            System.out.println("TC_007: Placeholder của ô password: " + placeholder);
            return "Mật khẩu".equals(placeholder);
        } catch (Exception e) {
            System.out.println("TC_007: Không tìm thấy ô password: " + e.getMessage());
            return false;
        }
    }

    /*
        Test case 8: Kiểm tra giá trị value của nút Submit
     */
    public static boolean testCase8(WebDriver drv) {
        try {
            WebElement submitButton = drv.findElement(By.className("btn-primary"));
            String value = submitButton.getAttribute("value");
            System.out.println("TC_008: Value của nút Submit: " + value);
            return "Đăng nhập".equals(value);
        } catch (Exception e) {
            System.out.println("TC_008: Không tìm thấy nút Submit: " + e.getMessage());
            return false;
        }
    }

    /*
        Test case 9: Kiểm tra ô username có thuộc tính required không
     */
    public static boolean testCase9(WebDriver drv) {
        try {
            WebElement usernameInput = drv.findElement(By.id("username"));
            String required = usernameInput.getAttribute("required");
            System.out.println("TC_009: Thuộc tính required của ô username: " + required);
            return required != null;
        } catch (Exception e) {
            System.out.println("TC_009: Không tìm thấy ô username: " + e.getMessage());
            return false;
        }
    }

    /*
        Test case 10: Kiểm tra form sử dụng method POST
     */
    public static boolean testCase10(WebDriver drv) {
        try {
            WebElement form = drv.findElement(By.tagName("form"));
            String method = form.getAttribute("method");
            System.out.println("TC_010: Phương thức form: " + method);
            return "post".equalsIgnoreCase(method);
        } catch (Exception e) {
            System.out.println("TC_010: Không tìm thấy form: " + e.getMessage());
            return false;
        }
    }

    /*
        Test case 11: Kiểm tra font-family của body có "sans-serif" không
    */
    public static boolean testCase11(WebDriver drv) {
        try {
            WebElement body = drv.findElement(By.tagName("body"));
            String fontFamily = body.getCssValue("font-family");
            System.out.println("font-family: " + fontFamily);
            return fontFamily.toLowerCase().contains("sans-serif");
        } catch (Exception e) {
            System.out.println("TC_011: Không tìm thấy body: " + e.getMessage());
            return false;
        }
    }

    /*
        Test case 12: Kiểm tra position của body
    */
    public static boolean testCase12(WebDriver drv) {
        try {
            WebElement body = drv.findElement(By.tagName("body"));
            String position = body.getCssValue("position");
            System.out.println("position: " + position);
            return "relative".equalsIgnoreCase(position);
        } catch (Exception e) {
            System.out.println("TC_012: Không tìm thấy body: " + e.getMessage());
            return false;
        }
    }

    /*
        Test case 13: Kiểm tra cỡ phông chữ của cụm từ "Đăng ký học phần"
     */
    public static boolean testCase13(WebDriver drv) {
        try {
            WebElement element = drv.findElement(By.className("social-title"));
            String fontSize = element.getCssValue("font-size");
            System.out.println("TC_013: font-size: " + fontSize);
            return fontSize.equalsIgnoreCase("10px");
        } catch (Exception e) {
            System.out.println("TC_013: Không tìm thấy phần tử: " + e.getMessage());
            return false;
        }
    }

    /*
        Test case 14: Kiểm tra cụm từ trên TC13 có phải màu đen không
     */
    public static boolean testCase14(WebDriver drv) {
        try {
            WebElement element = drv.findElement(By.className("social-title"));
            String color = element.getCssValue("color");
            System.out.println("TC_014: color: " + color);
            return color.equalsIgnoreCase("black");
        } catch (Exception e) {
            System.out.println("TC_014: Không tìm thấy phần tử: " + e.getMessage());
            return false;
        }
    }

}
