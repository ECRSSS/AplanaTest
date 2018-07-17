package nnglebanov.aplana.test.pages;

import nnglebanov.aplana.base.BasePage;
import nnglebanov.aplana.test.pages.parentpages.CatalogPage;
import nnglebanov.aplana.utils.DriverWaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class MarketTelevisionsPage extends CatalogPage {

    @Override
    public String getBaseItemLocator()
    {
        return "div.n-snippet-card2";
    }

    @Override
    public String getUrl() {
        return "https://market.yandex.ru/catalog/59601/";
    }

    @FindBy(xpath = "//input[@id='7893318_153074']/..")
    public WebElement lifeIsGoodFilterCheckbox;

    @FindBy(xpath = "//input[@id='7893318_153061']/..") //публичные фильтры по производителю
    public WebElement samsungFilterCheckbox;


}
