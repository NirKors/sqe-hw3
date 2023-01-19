package hellocucumber;

import io.cucumber.java.en.*;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

class PrestaTest {
    private String ADMIN_USERNAME = "danik107@gmail.com";
    private String ADMIN_PASSWORD = "IHaveNoEnergy";
    private PrestaShopAcutator prestaShop;
    private String webDriver = "webdriver.chrome.driver";
    private String path = "C:\\Users\\danik\\Downloads\\chromedriver.exe";


    public void prestaShopInit() {
        System.out.println("--------------- INITIALIZING PRESTASHOP TEST - OPENING WEBPAGE ---------------");
        prestaShop = new PrestaShopAcutator();
        prestaShop.initSession(webDriver, path);
    }

    public void prestaShopDashboardInit() {
        System.out.println("--------------- INITIALIZING PRESTASHOP TEST - OPENING WEBPAGE ---------------");
        prestaShop = new PrestaShopAcutator();
        prestaShop.initAdminSession(webDriver, path);
        prestaShop.login(ADMIN_USERNAME, ADMIN_PASSWORD);
    }

    public void adminChoosesProduct() {
        prestaShop.adminChooseProduct();
    }
    public void userChooseProduct(){
        prestaShop.chooseProduct();
    }

    public void adminConfirmDeletion(){
        prestaShop.confirmDelete();
    }

    public void adminEntersProductPage() {
        prestaShop.adminEntersProductPage();
    }

    public void checkShareButtons() {
        prestaShop.shareButton();
    }


    public void checkIfTabsOpenUS1() {
        prestaShop.aNewSharingTabOpened();
    }

    public void checkIfTabsOpenUS3() {
        prestaShop.twoTabOpened();
    }


    public void productDeleteSuccess() { prestaShop.productDeleteSuccess(); }
}

class PrestaShopAcutator {
    private WebDriver driver;
    private WebDriverWait wait;

    public void initSession(String webDriver, String path){
        webDriver = "webdriver.chrome.driver";
        path = "C:\\Users\\danik\\Desktop\\QA-3\\Selenium\\chromedriver.exe";
        System.setProperty(webDriver, path);

        // new chrome driver object
        this.driver = new ChromeDriver();

        // new web driver wait -> waits until element are loaded (40 sec max)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // launch website -> localhost
        driver.get("http://localhost:8080/");

        // maximize the window - some web apps look different in different sizes
        driver.manage().window().maximize();


        System.out.println("Driver setup finished for - " + driver.getTitle());
    }
    public void initAdminSession(String webDriver, String path){
        webDriver = "webdriver.chrome.driver";
        path = "C:\\Users\\danik\\Desktop\\QA-3\\Selenium\\chromedriver.exe";
        System.setProperty(webDriver, path);
        // new chrome driver object
        this.driver = new ChromeDriver();
        // new web driver wait -> waits until element are loaded (40 sec max)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // launch website -> localhost
        driver.get("http://localhost:8080/adminQA/");

        // maximize the window - some web apps look different in different sizes
        driver.manage().window().maximize();

        System.out.println("Driver setup finished for - " + driver.getTitle());
    }

    public void login(String username, String password){
        // locate the username input box and enter username
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"email\"]"))).sendKeys(username);

        // locate the password input box and enter password
        driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys(password);

        // locate Log in button and press
        // $x("//*[@id='loginbtn']")
        driver.findElement(By.id("submit_login")).click();
    }

    public void chooseProduct(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/section[3]/div/div[1]/article/div/div[2]/h3/a"))).click();
    }

    public void adminChooseProduct(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"product_catalog_list\"]/div/table/tbody/tr[1]/td[11]/div/div/button"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"product_catalog_list\"]/div/table/tbody/tr[1]/td[11]/div/div/div/a[3]"))).click();
    }
    public void terminateDriver(){
        // close the driver window
        // another option - to close a browser and all other windows associated with the driver: driver.quit();
        driver.close();

    }

    public void confirmDelete() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"catalog_deletion_modal\"]/div/div/div[3]/button[2]"))).click();
    }


    public void adminEntersProductPage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"subtab-AdminCatalog\"]/a"))).click();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"subtab-AdminProducts\"]/a"))).click();

    }

    public void shareButton() {
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[3]/div/ul/li[1]/a")).click();
    }

    public void aNewSharingTabOpened() {
        int amountOfTabs = driver.getWindowHandles().size();
        Assert.assertEquals(2,amountOfTabs); // Here we check that we only have one tab open, which is our original.
    }

    public void twoTabOpened() {
        int amountOfTabs = driver.getWindowHandles().size();
        Assert.assertNotEquals(2, amountOfTabs); // Here we check that we only have 2 tabs, the original and a new one
    }

    public void productDeleteSuccess() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"main-div\"]/div/div[3]")));
    }
}


public class StepDefinitions {

    static PrestaTest prestaUser = new PrestaTest();
    static PrestaTest prestaAdmin= new PrestaTest();
    // Driver Setup - this step creates new prestaShopAcutator instance, a new webDriver
    // instance and a new WebDriverWait instance, and launches the localhost.
    @Given("A user enters the landing page")
    public void userEntersLandingPage() {
        prestaUser.prestaShopInit();
    }

    // This step clicks on the first item once it detects it to enter its page.
    @Given("A user enters a product page")
    public void userEntersProductPage() {
        prestaUser.userChooseProduct();
    }

    // Creates a new PrestaShopAcutator, initializes the admin session and logs in using the admin credentials.
    @Given("An admin is in logged in")
    public void adminIsLoggedIn() {
        prestaAdmin.prestaShopDashboardInit();
    }

    // Selects `Catalog` -> `Products` on the page.
    @Given("An Admin entered the products page")
    public void adminEnteredProductPage() {
        prestaAdmin.adminEntersProductPage();
    }

    // Clicks the dropdown menu of the first item and selects delete.
    @Given("An admin selects the deletion of the item")
    public void adminSelectsItemForDeletion() {
        prestaAdmin.adminChoosesProduct();
    }

    // Confirms the deletion in the confirmation popup.
    @Given("An admin confirms deleting the item")
    public void adminConfirmsDelete() {
        prestaAdmin.adminConfirmDeletion();
    }

    // Here we check that we only have 2 tabs open, the original and a new Share tab.
    // Throws an exception if the check is TRUE.
    @Given("We return to an error")
    public void userReturnedToError() {
        prestaUser.checkIfTabsOpenUS3();
    }

    // Here we check that we only have 2 tabs open.
    // Throws an exception if the check is FALSE.
    @Given("A new tab is opened for the user")
    public void checkIfTabsOpenUS1(){
        prestaUser.checkIfTabsOpenUS1();
    }

    // Clicks on the first share button.
    @When("A user presses share button")
    public void aUserPressesShareButton() { prestaUser.checkShareButtons(); }

    // Checks if a "Product successfully deleted" message appears.
    // Throws an exception if it doesn't appear in 40 seconds.
    @Then("An admin receives an alert \"Product successfully deleted.\"")
    public void anAdminReceivesAnAlert() { prestaAdmin.productDeleteSuccess();}
}
