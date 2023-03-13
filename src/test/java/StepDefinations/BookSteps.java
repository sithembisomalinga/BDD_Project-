package StepDefinations;

import TestComponets.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BookSteps extends BaseTest {
    ExtentReports extent;
    ExtentTest test;

    @And("a user fill in search form with details including {string} and {string}")
    public void aUserFillInSearchFormWithDetailsIncludingAnd(String inDate, String outDate) {

        //location
        WebElement locations = driver.findElement(By.id("location"));
        Select location = new Select(locations);
        location.selectByIndex(2);

        //hotel
        WebElement hotels = driver.findElement(By.id("hotels"));
        Select hotel = new Select(hotels);
        hotel.selectByIndex(2);

        //room type
        WebElement roomTypes = driver.findElement(By.id("room_type"));
        Select roomType = new Select(roomTypes);
        roomType.selectByIndex(2);

        //number of rooms
        WebElement numberOfRooms = driver.findElement(By.id("room_nos"));
        Select roomNo = new Select(numberOfRooms);
        roomNo.selectByValue("2");

        //check in date
        driver.findElement(By.id("datepick_in")).clear();
        driver.findElement(By.id("datepick_in")).sendKeys(inDate);

        //check out date
        driver.findElement(By.id("datepick_out")).clear();
        driver.findElement(By.id("datepick_out")).sendKeys(outDate);

        //Adult per room
        WebElement adultPerRoom = driver.findElement(By.id("adult_room"));
        Select adult = new Select(adultPerRoom);
        adult.selectByValue("2");

        //children per room
        WebElement childPerRoom = driver.findElement(By.id("child_room"));
        Select child = new Select(childPerRoom);
        child.selectByValue("1");


    }

    @And("a user clicks the search button")
    public void aUserClicksTheSearchButton() {
        driver.findElement(By.id("Submit")).click();
    }

    @And("a user select hotel")
    public void aUserSelectHotel() {
        driver.findElement(By.id("radiobutton_0")).click();
    }

    @And("a user clicks the continue button")
    public void aUserClicksTheContinueButton() {
        driver.findElement(By.id("continue")).click();
    }

    @And("a user enters Booking details{string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void aUserEntersBookingDetails(String firstName, String lastName, String address, String cardNumber, String cardType, String expiryMonth, String expiryYear, String cvvNumber) {

        //first name
        driver.findElement(By.id("first_name")).sendKeys(firstName);

        //last name
        driver.findElement(By.id("last_name")).sendKeys(lastName);

        //address
        driver.findElement(By.id("address")).sendKeys(address);

        //card number
        driver.findElement(By.id("cc_num")).sendKeys(cardNumber);

        if (cardNumber.length() < 16) {
            extent = getReportObject("\\reports\\unsuccessful_booking.html");
            test = extent.createTest("Booking error validation");
        } else {
            extent = getReportObject("\\reports\\successful_booking.html");
            test = extent.createTest("Booking");
        }

        //card type
        WebElement types = driver.findElement(By.id("cc_type"));
        Select type = new Select(types);
        type.selectByVisibleText(cardType);

        //expiryMonth
        WebElement months = driver.findElement(By.id("cc_exp_month"));
        Select month = new Select(months);
        month.selectByVisibleText(expiryMonth);

        //expiryYear
        WebElement years = driver.findElement(By.id("cc_exp_year"));
        Select year = new Select(years);
        year.selectByVisibleText(expiryYear);

        //cvvNumber
        driver.findElement(By.id("cc_cvv")).sendKeys(cvvNumber);
    }

    @And("a user clicks the book now button")
    public void aUserClicksTheBookNowButton() {
        driver.findElement(By.id("book_now")).click();
    }

    @Then("a user booked successful")
    public void aUserBookedSuccessful() {
        if (!driver.findElement(By.id("order_no")).isDisplayed()) {
            test.fail("Booking was unsuccessful");
            Assert.fail();
        } else {
            test.pass("Booking was successful");
        }
        closeDriver();
        extent.flush();
    }

    @Then("a user booking is unsuccessful")
    public void aUserBookingIsUnsuccessful() {

        if (!driver.findElement(By.id("cc_num_span")).isDisplayed()) {
            test.fail("Booking error validation failed").addScreenCaptureFromPath("screenshot.png");;
            Assert.fail();
        } else {
            test.pass("Booking error validation passed");
        }
        closeDriver();
        extent.flush();
    }

}
