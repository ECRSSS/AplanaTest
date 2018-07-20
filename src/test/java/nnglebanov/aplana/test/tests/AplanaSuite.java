package nnglebanov.aplana.test.tests;

import nnglebanov.aplana.driverfactory.DriverFactory;
import nnglebanov.aplana.driverfactory.enums.Browsers;
import nnglebanov.aplana.driverfactory.enums.Environment;
import nnglebanov.aplana.test.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class AplanaSuite {
    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) throws Exception {
        DriverFactory factory = DriverFactory.getInstance();
        factory.setDriver(Browsers.fromString(browser), "Windows 10", Environment.LOCAL);
    }

    @AfterMethod
    public void endTest() {
        DriverFactory.getInstance().closeDriver();
    }


    @Test
    public void aplanaScenario1() throws Exception {
        //работа со страницей яндекса
        YandexPage yandexPage = new YandexPage();
        yandexPage.open();
        yandexPage.moveToMarket();

        //работа с главной страницей маркета
        MarketMainPage marketMainPage = new MarketMainPage();
        marketMainPage.moveToElectronics();

        //работа со страницей раздела электроники
        MarketElectronicsPage marketElectronicsPage = new MarketElectronicsPage();
        marketElectronicsPage.moveToTelevisions();

        //работа со страницей раздела телевизоров
        MarketTelevisionsPage marketTelevisionsPage = new MarketTelevisionsPage();
        marketTelevisionsPage.switchNumOfElementsOnPage(0);

        marketTelevisionsPage.sendKeysToPriceFromFilter("20000");
        marketTelevisionsPage.lifeIsGoodFilterCheckbox.click();
        marketTelevisionsPage.samsungFilterCheckbox.click();
        Assert.assertEquals(marketTelevisionsPage.getItemsOnPage().size(), 12); //проверка, что количество элементов=12
        String nameOfFirstItemOnPage = marketTelevisionsPage
                .getTitleOfItem(marketTelevisionsPage.getFirstItemOnPage());
        System.out.println(nameOfFirstItemOnPage);
        marketTelevisionsPage.sendKeysToSearchField(nameOfFirstItemOnPage + Keys.ENTER);

        //работа со страницей раздела продукта
        MarketItemPage marketItemPage = new MarketItemPage();
        Assert.assertEquals(marketItemPage.getTitle(), nameOfFirstItemOnPage); //проверка, что названия эквивалентны
    }

    @Test
    public void aplanaScenario2() throws Exception {
        //работа со страницей яндекса
        YandexPage yandexPage = new YandexPage();
        yandexPage.open();
        yandexPage.moveToMarket();

        //работа с главной страницей маркета
        MarketMainPage marketMainPage = new MarketMainPage();
        marketMainPage.moveToElectronics();

        //работа со страницей раздела электроники
        MarketElectronicsPage marketElectronicsPage = new MarketElectronicsPage();
        marketElectronicsPage.moveToHeadphones();

        //работа со страницей раздела наушников
        MarketHeadPhonesPage marketHeadphonesPage = new MarketHeadPhonesPage();
        marketHeadphonesPage.switchNumOfElementsOnPage(0);
        marketHeadphonesPage.beatsFilterCheckbox.click();
        marketHeadphonesPage.waitForH1TitleContains("Beats");
        marketHeadphonesPage.sendKeysToPriceFromFilter("5000");
        Assert.assertEquals(marketHeadphonesPage.getItemsOnPage().size(), 12); //проверка, что количество элементов=12
        String nameOfFirstItemOnPage = marketHeadphonesPage
                .getTitleOfItem(marketHeadphonesPage.getFirstItemOnPage());
        System.out.println(nameOfFirstItemOnPage);
        marketHeadphonesPage.sendKeysToSearchField(nameOfFirstItemOnPage);

        WebElement firstItem = marketHeadphonesPage.getFirstItemOnPage();
        String nameOfFirstItemAfterFilter=marketHeadphonesPage.getDescOfFirstItemAfterFiltering();
        Assert.assertEquals(nameOfFirstItemAfterFilter,nameOfFirstItemOnPage);
    }
}
