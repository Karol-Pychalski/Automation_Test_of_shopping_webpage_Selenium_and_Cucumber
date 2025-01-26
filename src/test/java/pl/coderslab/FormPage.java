package pl.coderslab;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FormPage {
 private WebDriver driver;
 String email = "nbaaaiwjekdpkqppub@hthlm.com";
 String password = "0Z1m9A2P!";

 @Given("I'm on shop main page")
    public void openBrowserSearch() {
     System.setProperty("webdriver.chrome.driver",
             "C:\\webdrivers\\chromedriver.exe");
     driver = new ChromeDriver();
     driver.manage().window().maximize();
     driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
     driver.get("https://mystore-testlab.coderslab.pl");
 }

    @When("I'm loggin to my account")
    public void loggingToAccount() {
        driver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
        WebElement emailField = driver.findElement(By.id("field-email"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(By.id("field-password"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        driver.findElement(By.id("submit-login")).click();
    }

    @And("I click 'Addresses' link")
    public void addressesPage() {
        driver.findElement(By.xpath("//a[@title='Addresses']")).click();
    }

    @And("I click to {string}")
    public void iClickTo(String element) {
        driver.findElement(By.xpath("//span[contains(text(),'" + element + "')]")).click();
    }

    @And("I enter alias {string}, address {string}, city {string}, postal code {string}, country {string} and phone {string}")
    public void enterData(String alias, String address, String city, String postalCode, String country, String phone) {
     driver.findElement(By.id("field-alias")).sendKeys(alias);
     driver.findElement(By.id("field-address1")).sendKeys(address);
     driver.findElement(By.id("field-city")).sendKeys(city);
     driver.findElement(By.id("field-postcode")).sendKeys(postalCode);
     driver.findElement(By.id("field-id_country")).sendKeys(country);
     driver.findElement(By.id("field-phone")).sendKeys(phone);
     driver.findElement(By.className("form-control-submit")).click();
    }

    @Then("I can see success message {string}")
    public void successMessage(String expectedText) {
     WebElement alertMessage = driver.findElement(By.className("alert-success"));
     Assert.assertTrue("Alert message should be displayed", alertMessage.isDisplayed());
     Assert.assertEquals(expectedText, alertMessage.getText());
    }

    @And("I click {string} button")
    public void iClickDeleteButton(String element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        List<WebElement> deleteButtons = driver.findElements(By.xpath("//span[contains(text(),'" + element + "')]"));
        int index = 1;
        if (deleteButtons.size() > index) {
           WebElement deleteButton = deleteButtons.get(index);
           wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
           deleteButton.click();
        } else {
            System.out.println("Button 'Delete' was not found");
        }
    }

    @Then("I see success message {string}")
    public void iSeeSuccessMessage(String expectedDeleteText) {
     WebElement deleteMessage = driver.findElement(By.className("alert-success"));
     Assert.assertTrue("Delete success message should be displayed", deleteMessage.isDisplayed());
     Assert.assertEquals(expectedDeleteText, deleteMessage.getText());
    }

    @And("I close the browser")
    public void closeTheBrowser() {
        driver.quit();
    }
}