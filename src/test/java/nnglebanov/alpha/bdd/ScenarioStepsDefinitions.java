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

    @Then("^titles is equals$")
    public void checkTitles() {
        MarketItemPage marketItemPage = new MarketItemPage();
        Assert.assertEquals(marketItemPage.getTitle(), firstItemTitle); //проверка, что названия эквивалентны
    }

    @Then("^num of elements on page is (.+) now$")
    public void check12ItemsOnPage(String n) {
        int num = Integer.parseInt(n);
        MarketTelevisionsPage marketTelevisionsPage = new MarketTelevisionsPage();
        Assert.assertEquals(marketTelevisionsPage.getItemsOnPage().size(), 12); //проверка, что количество элементов=12
    }
}
