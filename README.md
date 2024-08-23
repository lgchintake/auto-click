# auto-click

This project will help you to keep your screen awake always.

This selenium project auto-opens random websites and clicks randomly. It works on Windows and MAC (tested)

## Prerequisites
1. Java 17 should be installed

## Installation
### Java 17 Installation
1. Visit the [Oracle website](https://www.oracle.com/java/technologies/downloads/) and download [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17) installer according to your operating system.
2. To verify Java installation type the below command on cmd / terminal
    ```bash
      java -version
    ```
3. If the above command gives Java 17 installed output then it is OK to go ahead otherwise, we need to fix the installation issue. (If you don't have the rights to install software or don't want to install JDK then find the below section with the heading **Use Java Without Installation**)

### Project Installation
1. Download the **auto-click.jar** file from this repository.
2. Run the below command on your cmd / terminal
    ```bash
      java -jar auto-click.jar
    ```
**Note: If you are running this jar on MAC then it may ask you for accessibility access so please provide it otherwise clicks will not work**
****
## Custom websites Input
If you want to open your own websites instead of predefined ones, you need to follow the process below.

1. Open any text editor like Notepad and type your website URLs line by line.
2. Every line should contain only one website URL
3. Save this file with name **websites.txt** at your folder where **auto-click.jar** file is placed.
****
## Use Java Without Installation

1. Visit [JDK Download](https://www.oracle.com/java/technologies/downloads/#java17) URL and select your operating system. Check the ZIP/Archive version of the JDK.
2. Unzip on your system downloaded JDK
3. Keep your **auto-click.jar** file inside the **bin** folder of the downloaded JDK and follow the above instructions.

**Note: This is educational purpose only**
