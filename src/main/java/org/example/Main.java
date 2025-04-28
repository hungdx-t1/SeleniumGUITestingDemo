package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Main {
    public static void main(String[] args) {
        int passes = 0, fails = 0;

        WebDriver driver = new EdgeDriver();
        driver.get("https://tinchi.qnu.edu.vn/Login");

        try {
            Thread.sleep(5000); // ngủ 5 giây
        } catch (InterruptedException e) {
            System.out.println("Sleep thread was interrupted by an exception: " + e.getMessage());
        }

        boolean[] results = {
                GUITestingCases2.testCase1(driver),
                GUITestingCases2.testCase2(driver),
                GUITestingCases2.testCase3(driver),
                GUITestingCases2.testCase4(driver),
                GUITestingCases2.testCase5(driver),
                GUITestingCases2.testCase6(driver),
                GUITestingCases2.testCase7(driver),
                GUITestingCases2.testCase8(driver),
                GUITestingCases2.testCase9(driver),
                GUITestingCases2.testCase10(driver),
                GUITestingCases2.testCase11(driver),
                GUITestingCases2.testCase12(driver),
                GUITestingCases2.testCase13(driver),
                GUITestingCases2.testCase14(driver),
        };

        for(int i = 0; i < results.length; i++) {
            if (results[i]) {
                passes++;
                System.out.println("✅ Test case " + (i+1) + " thực hiện thành công.");
            } else {
                fails++;
                System.out.println("❌ Test case " + (i+1) + " thất bại.");
            }
        }

        System.out.println();
        System.out.println("Tổng số test case thất bại: " + fails);
        System.out.println("Tổng số test case thành công: " + passes);

        driver.quit();
    }
}