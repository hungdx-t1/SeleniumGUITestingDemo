package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class TestTutorial {
    // GUI Testing nên kết hợp Explicit Wait (WebDriverWait) vì phần tử trên GUI có thể chưa kịp load.
    // Đừng test GUI "cứng" kiểu sleep, hãy wait đến khi ready.

    // Phương thức sau đây là ví dụ về mẹo trên
    @Deprecated
    public static void test1() {
        WebDriver driver = new EdgeDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        button.click();

    }

    public static void initialize() {
        WebDriver driver = new EdgeDriver();
        // Web Driver hỗ trợ nhiều trình duyệt khác nhau, trong dòng trên là EdgeDriver, khi khởi chạy sẽ chạy
        // trình duyệt edge, hoặc là Firefox, IE (deprecated), Safari Browser:
        // WebDriver webDriver1 = new ChromeDriver();
        // WebDriver webDriver2 = new SafariDriver();

        // Tìm phần tử có id là submit
        WebElement button = driver.findElement(By.id("submit"));

        // Tìm phần tử có name là submit
        WebElement button1 = driver.findElement(By.name("submit"));

        driver.quit(); // hoặc driver.close(), để đóng ứng dụng sau khi test, nhưng close chỉ đóng tab test, còn quit là đóng hẳn trình duyệt
        driver.close();
    }

    public static void elementVisibility() {
        WebDriver driver = new EdgeDriver();

        WebElement button = driver.findElement(By.id("submit"));
        button.isDisplayed(); // true nếu phần tử có hiển thị trên trang
        button.isEnabled();   // true nếu phần tử có tương tác được (enabled) (ví dụ như có thể nhấn)
        button.isSelected();  // true nếu phần tử checkbox/radio có đang được chọn không
    }

    public static void textVerification() {
        WebDriver driver = new EdgeDriver();

        // lấy text hiển thị của phần tử
        String label = driver.findElement(By.id("welcome")).getText();

        // lấy giá trị value của input
        String inputValue = driver.findElement(By.id("search")).getAttribute("value");
    }

    public static void attributeStyleCheck() {
        WebDriver driver = new EdgeDriver();

        // Kiểm tra css
        String color = driver.findElement(By.id("submit")).getCssValue("background-color");
        String fontSize = driver.findElement(By.id("submit")).getCssValue("font-size");

        // Kiểm tra thuộc tính
        String placeholder = driver.findElement(By.name("q")).getAttribute("placeholder");
        String ariaLabel = driver.findElement(By.name("q")).getAttribute("aria-label");
    }

    public static void layoutVerification() {
        WebDriver driver = new EdgeDriver();

        Point location = driver.findElement(By.id("logo")).getLocation(); // trả về tọa độ (x, y)
        Dimension size = driver.findElement(By.id("logo")).getSize(); // trả về chiều rộng (width) và chiều cao (height)
    }

    public static void interactionTesting() {
        WebDriver driver = new EdgeDriver();

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium Java"); // sendKeys: điền dữ liệu vào element
        searchBox.clear(); // clear: clear dữ liệu
        searchBox.submit(); // submut: submit (form...)
        searchBox.click(); // click: nhấp vào element
    }

    public static void frameTesting() {
        WebDriver driver = new EdgeDriver();

        Alert alert = driver.switchTo().alert(); // Switch Alert

        // Switch Iframe
        driver.switchTo().frame("frameName"); // by name or id
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe); // by WebElement

        Set<String> wh = driver.getWindowHandles(); // Window handles
        System.out.println(alert.getText());
        alert.accept(); // bấm OK
    }

    public static void stateChangeTesting() {
        // Thường thì khi bạn click vào 1 element nào đó, nó sẽ đổi từ màu này sang màu khác. Nếu làm cách này,
        // ta có thể tạo test case bằng cách kết hợp giữa "wait" và "getAttribute" hoặc "getCssValue"

        WebDriver driver = new EdgeDriver();
        WebElement button = driver.findElement(By.id("submit"));
        String initialColor = button.getCssValue("background-color");

        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(button, "background-color", initialColor)));

        String changedColor = button.getCssValue("background-color");
        System.out.println("Color changed to: " + changedColor);
    }

    public static void animationTesting() {
        // Dùng JavascriptExecutor để đọc style hoặc đo thời gian nếu cần.
        // Hoặc dùng ExpectedConditions của Selenium để đợi một animation kết thúc.
        WebDriver driver = new EdgeDriver();

        WebElement element = driver.findElement(By.name("q"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String opacity = (String) js.executeScript("return window.getComputedStyle(arguments[0]).opacity;", element);

    }

    public static void advancedInteractionTest() {
        WebDriver driver = new EdgeDriver();
        WebElement element = driver.findElement(By.name("q"));

        WebElement element1 = null, element2 = new RemoteWebElement();

        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform(); // hover

        assert element1 != null;
        actions.dragAndDrop(element1, element2).perform(); // kéo thả
    }

    public static void resizeTesting() {
        WebDriver driver = new EdgeDriver();
        driver.manage().window().setSize(new Dimension(375, 667)); // Mobile size
    }

    // Phương thức chính để chạy test case
    /*
    public static void main() {
        int success = 0, failed = 0;
        boolean[] results = {
                GUITestingCases.testCase1(),
                GUITestingCases.testCase2(),
                GUITestingCases.testCase3(),
        };

        for(int i = 0; i < results.length; i++) {
            if (results[i]) {
                success++;
                System.out.println("✅ Test case " + (i+1) + " thực hiện thành công.");
            } else {
                failed++;
                System.out.println("❌ Test case " + (i+1) + " thất bại.");
            }
        }

        System.out.println();
        System.out.println("Tổng số test case thất bại: " + failed);
        System.out.println("Tổng số test case thành công: " + success);
    }

     */
}
