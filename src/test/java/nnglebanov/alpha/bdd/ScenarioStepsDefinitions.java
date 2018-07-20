package nnglebanov.alpha.bdd;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nnglebanov.aplana.driverfactory.DriverFactory;
import nnglebanov.aplana.driverfactory.enums.Browsers;
import nnglebanov.aplana.driverfactory.enums.Environment;
import nnglebanov.aplana.test.pages.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ScenarioStepsDefinitions {

    private String firstItemTitle;

    @Before
    public void init() throws Exception {
        DriverFactory factory = DriverFactory.getInstance();
        factory.setDriver(Browsers.fromString("chrome"), "Windows 10", Environment.LOCAL);
    }

    @After
    public void stop() {
        DriverFactory factory = DriverFactory.getInstance();
        factory.closeDriver();
    }

    @Given("^moved to market$")
    public void moveToYandex() throws Exception {
        //работа со страницей яндекса
        YandexPage yandexPage = new YandexPage();
        yandexPage.open();
        yandexPage.moveToMarket();
    }

    @When("^i move to electronics$")
    public void moveToElecronicsFromYandex() {
        //работа с главной страницей маркета
        MarketMainPage marketMainPage = new MarketMainPage();
        marketMainPage.moveToElectronics();
    }

    @When("^i move to TV$")
    public void moveToTelevisionFromElectronics() throws Exception {
        //работа со страницей раздела электроники
        MarketElectronicsPage marketElectronicsPage = new MarketElectronicsPage();
        marketElectronicsPage.moveToTelevisions();
    }

    @When("^i move to headphones$")
    public void moveToHeadphones() throws Exception {
        MarketElectronicsPage marketElectronicsPage = new MarketElectronicsPage();
        marketElectronicsPage.moveToHeadphones();
    }

    @When("^i click on Beats filter$")
    public void clickOnBeats() {
        MarketHeadPhonesPage marketHeadPhonesPage = new MarketHeadPhonesPage();
        marketHeadPhonesPage.beatsFilterCheckbox.click();
    }

    @When("^i change num of items on page to (.+)$")
    public void changeItemsNumOnPage(String n) throws Exception {
        MarketTelevisionsPage marketTelevisionsPage = new MarketTelevisionsPage();
        int num = Integer.parseInt(n);
        if (num == 12) {
            marketTelevisionsPage.switchNumOfElementsOnPage(0);
        } else if (num == 48) {
            marketTelevisionsPage.switchNumOfElementsOnPage(1);
        } else {
            throw new Exception();
        }
    }

    @When("^i send keys (.+) to min price filter$")
    public void sendKeys(String keys) {
        MarketTelevisionsPage marketTelevisionsPage = new MarketTelevisionsPage();
        marketTelevisionsPage.sendKeysToPriceFromFilter(keys);
    }

    @When("^i click on LG and Samsung filters$")
    public void clickOnSamsungAndLg() {
        MarketTelevisionsPage marketTelevisionsPage = new MarketTelevisionsPage();
        marketTelevisionsPage.lifeIsGoodFilterCheckbox.click();
        marketTelevisionsPage.samsungFilterCheckbox.click();
    }

    @When("^i get first item title$")
    public void getFirstItemOnPageTitle() {
        MarketTelevisionsPage marketTelevisionsPage = new MarketTelevisionsPage();
        firstItemTitle = marketTelevisionsPage
                .getTitleOfItem(marketTelevisionsPage.getFirstItemOnPage());
    }

    @When("^i search first item in search field$")
    public void searchFirstTitleItem() {
        MarketTelevisionsPage marketTelevisionsPage = new MarketTelevisionsPage();
        marketTelevisionsPage.sendKeysToSearchField(firstItemTitle + Keys.ENTER);
    }

    @When("^i get name of first headphones item and send it to search field$")
    public void getNameOfFirstItem() {
        MarketHeadPhonesPage marketHeadPhonesPage = new MarketHeadPhonesPage();
        firstItemTitle = marketHeadPhonesPage
                .getTitleOfItem(marketHeadPhonesPage.getFirstItemOnPage());
        marketHeadPhonesPage.sendKeysToSearchField(firstItemTitle);
    }

    @Then("^titles is equals$")
    public void checkTitles() {
        MarketItemPage marketItemPage = new MarketItemPage();
        Assert.assertEquals(marketItemPage.getTitle(), firstItemTitle); //проверка, что названия эквивалентны
    }

    @Then("^num of elements on tv page is (.+) now$")
    public void waitBeforeItemsOnTVPageEquals12(String n) {
        int num = Integer.parseInt(n);
        MarketTelevisionsPage marketTelevisionsPage = new MarketTelevisionsPage();
        Assert.assertEquals(marketTelevisionsPage.getItemsOnPage().size(), num);
    }

    @Then("^wait before is h1 on page contains (.+)$")
    public void waitForH1equals(String title) {
        MarketHeadPhonesPage marketHeadPhonesPage = new MarketHeadPhonesPage();
        marketHeadPhonesPage.waitForH1TitleContains(title);
    }

    @Then("^num of elements on headphones page is (.+) now$")
    public void waitBeforeItemsOnHeadphonesPageEquals12(String n) {
        int num = Integer.parseInt(n);
        MarketHeadPhonesPage marketHeadPhonesPage = new MarketHeadPhonesPage();
        Assert.assertEquals(marketHeadPhonesPage.getItemsOnPage().size(), num);
    }

    @Then("^check first item name$")
    public void checkFirstItemNames() {
        MarketHeadPhonesPage marketHeadphonesPage=new MarketHeadPhonesPage();
        WebElement firstItem = marketHeadphonesPage.getFirstItemOnPage();
        String nameOfFirstItemAfterFilter=marketHeadphonesPage.getDescOfFirstItemAfterFiltering();
        Assert.assertEquals(nameOfFirstItemAfterFilter,firstItemTitle);
    }
}
