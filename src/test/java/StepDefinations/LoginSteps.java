package StepDefinations;

import TestComponets.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

public class LoginSteps extends BaseTest {

    ExtentReports extent;
    ExtentTest test;

    @Given("a user is on the home page")
    public void aUserIsOnTheHomePage() {
        driver = initializeDriver();
        extent = getReportObject("\\reports\\login-report.html");
        test = extent.createTest("Logging in");
    }

    @When("a user navigates to the Login page using {string}")
    public void aUserNavigatesToTheLoginPageUsing(String url) {
        driver.get(url);
    }

    @And("a user enter {string} and {string}")
    public void aUserEnterAnd(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("a user clicks the login button")
    public void aUserClicksTheLoginButton() {
        driver.findElement(By.id("login")).click();
    }

    @Then("a user is logged in successfully")
    public void aUserIsLoggedInSuccessfully() {
        //By.id("location")
        //Boolean logged = driver.findElement(By.xpath("/html[1]/body[1]/table[2]/tbody[1]/tr[1]/td[1]")).getText().equalsIgnoreCase("Welcome to Adactin Group of Hotels");
        if (!driver.findElement(By.id("location")).isDisplayed()) {
            test.fail("Login unsuccessful").addScreenCaptureFromPath("screenshot.png");;
            Assert.fail();
        } else {
            //Assert.assertTrue(logged);
            test.pass("Login is successful");

        }
        closeDriver();
        extent.flush();
    }
}
