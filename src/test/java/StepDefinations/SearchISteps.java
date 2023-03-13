package StepDefinations;

import TestComponets.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;

public class SearchISteps extends BaseTest {

    ExtentReports extent;
    ExtentTest test;

    @And("a user navigate to the Booked Itinerary")
    public void aUserNavigateToTheBookedItinerary() throws InterruptedException {
        extent = getReportObject("\\reports\\search.html");
        test = extent.createTest("Order Search");
        driver.findElement(By.xpath("//a[@href='BookedItinerary.php']")).click();
    }

    @And("a user search for Itinerary using orderId")
    public void aUserSearchForItineraryUsingOrderId() {
        String orderId = "MRAVGM06CF";
        driver.findElement(By.id("order_id_text")).sendKeys(orderId);
        driver.findElement(By.id("search_hotel_id")).click();
    }

    @Then("order is displayed successfully")
    public void orderIsDisplayedSuccessfully() {
        if (!driver.findElement(By.id("search_result_error")).isDisplayed()) {
            test.fail("Itinerary searched failed").addScreenCaptureFromPath("screenshot.png");;
            Assert.fail();
        } else {
            test.pass("Itinerary search passed");
        }
        closeDriver();
        extent.flush();
    }

}