package com.home.practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

@Component
public class SeleniumTest implements CommandLineRunner {
    static {
        System.setProperty("java.awt.headless", "false");
    }

    String[] websites = {"https://example.com", "https://www.google.com", "https://stackoverflow.com", "https://www.tutorialspoint.com", "https://www.w3schools.com/", "https://github.com"};

    @Override
    public void run(String... args) throws Exception {
        readWebsites();
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        Dimension browserDimension = driver.manage().window().getSize();

        int browserHeight = browserDimension.getHeight();
        int browserWidth = browserDimension.getWidth();

        int previous = 0;
        int webSiteMaxCount = websites.length - 1;
        Random random = new Random();
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
                WebElement targetElement = driver.findElement(By.tagName("body"));

                // Perform mouse move to the target element
                actions.moveToElement(targetElement).perform();

                // Optionally, click on the element after moving
                actions.click().perform();

                Robot robot = new Robot();

                int randomX = random.nextInt(browserWidth);
                int randomY = random.nextInt(browserHeight);

                try {
                    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                    String script = String.format("document.elementFromPoint(%d, %d).click();", randomX, randomY);
                    jsExecutor.executeScript(script);
                } catch (Exception e){
                    System.out.println("Error clicking...");
                }

                // Move the mouse cursor
                robot.mouseMove(randomX, randomY);

                // Optionally, perform a click at the new location
//                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                System.out.println("Waiter: " + randomWaiter + " ---- Mouse moved to (" + randomX + ", " + randomY + ") and clicked. ");
                Thread.sleep(randomWaiter * 1000L); // 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    private int generateRandom(int max, int min) {
        return new Random().nextInt(max - min + 1) + min;
    }

    private void readWebsites() {
        // Get the current directory path
        String currentDirectory = System.getProperty("user.dir");

        // Define the file name
        String fileName = "websites.txt";

        // Create a Path object for the file
        Path filePath = Paths.get(currentDirectory, fileName);

        // Check if the file exists
        if (Files.exists(filePath)) {
            try {
                // Read all lines from the file
                List<String> lines = Files.readAllLines(filePath);

                // Convert the list of strings to an array
                websites = lines.toArray(new String[0]);
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }
        } else {
            System.out.println("File " + fileName + " does not exist in the current directory.");
        }
    }
}
