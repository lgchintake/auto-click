package com.home.practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;

@Component
public class SeleniumTest implements CommandLineRunner {
    static {
        System.setProperty("java.awt.headless", "false");
    }

    String[] websites = {"https://example.com", "https://www.google.com", "https://stackoverflow.com", "https://www.tutorialspoint.com", "https://www.w3schools.com/", "https://github.com"};

    @Override
    public void run(String... args) throws Exception {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int previous = 0;
        int webSiteMaxCount = websites.length - 1;
        do {
            int randomWaiter;
            do {
                randomWaiter = generateRandom(10, 1);
            } while (previous == randomWaiter);
            previous = randomWaiter;

            try {
                // Open a website
                driver.get(websites[generateRandom(webSiteMaxCount, 0)]);

                // Create an instance of Actions class
                Actions actions = new Actions(driver);

                // Locate the element to move to
                WebElement targetElement = driver.findElement(By.tagName("div"));

                // Perform mouse move to the target element
                actions.moveToElement(targetElement).perform();

                // Optionally, click on the element after moving
                actions.click().perform();

                Robot robot = new Robot();

                int[] position = getMouseNewPosition(screenWidth, screenHeight);
                // Move the mouse cursor
                robot.mouseMove(position[0], position[1]);

                // Optionally, perform a click at the new location
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                System.out.println("Waiter: " + randomWaiter + " ---- Mouse moved to (" + position[0] + ", " + position[1] + ") and clicked. ");
                Thread.sleep(randomWaiter * 1000L); // 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    private int generateRandom(int max, int min) {
        return new Random().nextInt(max - min + 1) + min;
    }

    private int[] getMouseNewPosition(int screenWidth, int screenHeight) {
        return new int[]{generateRandom(screenWidth, 300), generateRandom(screenHeight, 300)};
    }
}
