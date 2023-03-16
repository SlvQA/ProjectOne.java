import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectOne {
    @Test
    public void test() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.get("http://duotify.us-east-2.elasticbeanstalk.com/register.php"); // navigate to the website
        String actualTitle = driver.getTitle(); // returns the Title of the page
        String expectedTitle = "Welcome to Duotify!";
        //driver.close(); // closing the active window
        Assert.assertEquals(actualTitle, expectedTitle, "Titles are not matching");
        WebElement signUpLink = driver.findElement(By.id("hideLogin")); // finding the link
        Thread.sleep(500);
        signUpLink.click(); // clicking

        Thread.sleep(500);

            Faker faker = new Faker();
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys(faker.name().username());
            WebElement firstName = driver.findElement(By.id("firstName"));
            firstName.sendKeys(faker.name().firstName());
        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys(faker.name().lastName());
            WebElement email = driver.findElement(By.id("email")); // creating email
            email.sendKeys(faker.internet().emailAddress());
            WebElement email2 = driver.findElement(By.id("email2"));  // copying as a confirmation of the email
            String emailConfirmation = email.getAttribute("value");
            email2.sendKeys("" + emailConfirmation);
        WebElement password = driver.findElement(By.id("password")); // creating a password
        password.sendKeys(faker.internet().password());
        WebElement password2 = driver.findElement(By.id("password2")); // copying as a confirmation of the password
        String passConfirmation = password.getAttribute("value");
        password2.sendKeys("" + passConfirmation);

        String fname = firstName.getAttribute("value"); // storing generated info for future use
        String lname = lastName.getAttribute("value");
        String usrName = username.getAttribute("value");
        String pass = password.getAttribute("value");

        Thread.sleep(700);

        WebElement signUpButton = driver.findElement(By.name("registerButton")); // finding the button
        signUpButton.click(); // clicking

        Thread.sleep(500);

        String actualURL = driver.getCurrentUrl(); // checking is it took me to the right URL
        Assert.assertEquals(actualURL, "http://duotify.us-east-2.elasticbeanstalk.com/browse.php?", "The URLs don't match!");

        String fullNameExpected = fname + " " + lname;
        String fullNameOnThePage = driver.findElement(By.id("nameFirstAndLast")).getText(); // checking if the name matches the one I used while signing up
        Assert.assertEquals(fullNameOnThePage, fullNameExpected, "Names don't match! Expected name is " + fullNameExpected + " and Actual Name is " + fullNameOnThePage + ".");
        WebElement nameLink = driver.findElement(By.id("nameFirstAndLast")); // finding the name-link
        nameLink.click(); // clicking

        Thread.sleep(700);

        String fullNameOnThisPage = driver.findElement(By.className("userInfo")).getText(); // checking if the name matches the one I used while signing up
        Assert.assertEquals(fullNameOnThisPage, fullNameExpected, "Names don't match! Expected name is " + fullNameExpected + " and Actual Name is " + fullNameOnThisPage + ".");

        Thread.sleep(700);

        WebElement LogOut = driver.findElement(By.id("rafael")); // finding the LogOut button
        LogOut.click(); // clicking

        Thread.sleep(500);

        String loggedOutURL = driver.getCurrentUrl(); // checking is it took me to the right URL after LogOut
        Assert.assertEquals(loggedOutURL, "http://duotify.us-east-2.elasticbeanstalk.com/register.php", "The URLs don't match!");

        WebElement logInUser = driver.findElement(By.id("loginUsername")); // input of credentials
        logInUser.sendKeys(usrName);
        WebElement logInPass = driver.findElement(By.id("loginPassword"));
        logInPass.sendKeys(pass);
        WebElement LogIn = driver.findElement(By.name("loginButton")); // finding the LogIn button
        LogIn.click(); // clicking

        Thread.sleep(700);

        String pageSource =  driver.getPageSource(); // getting page Source code
        driver.getPageSource();
        String text = "You Might Also Like"; // checking if the Source code contains the needed text
        Assert.assertTrue(pageSource.contains(text), "The needed text was not found, this may be a wrong page.");

        Thread.sleep(500);

        driver.findElement(By.id("nameFirstAndLast")).click(); // finding the name-link to proceed to LogOut Button and clicking

        Thread.sleep(500);

        WebElement logOutButton = driver.findElement(By.id("rafael")); // finding the LogOut button
        logOutButton.click(); // clicking

        Thread.sleep(500);

        driver.getCurrentUrl(); // checking is it took me to the right URL after LogOut
        Assert.assertEquals(loggedOutURL, "http://duotify.us-east-2.elasticbeanstalk.com/register.php", "The URLs don't match!");

        Thread.sleep(500);

        driver.close();

    }



}
