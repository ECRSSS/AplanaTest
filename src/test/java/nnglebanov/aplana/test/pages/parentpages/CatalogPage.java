package nnglebanov.aplana.test.pages.parentpages;

import nnglebanov.aplana.base.BasePage;
import nnglebanov.aplana.utils.DriverWaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;

public abstract class CatalogPage extends BasePage {


    protected abstract String getBaseItemLocator();

    @FindBy(css = "div.n-pager.i-bem.n-pager_js_inited button")
    protected WebElement numOfElementsOnPageButton;

    @FindBy(css = "#glpricefrom")
    protected WebElement priceFromField;

    @FindBy(css = "#header-search")
    protected WebElement searchField;

    @FindBy(css = "h1.title")
    protected WebElement h1title;

    protected void waitForItemsRefreshed() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {

                ArrayList<WebElement> elements = getItemsOnPage();
                if (elements.size() == 12) {
                    return Boolean.TRUE;
                }
                return null;
            }
        });

    }
    public void waitForH1TitleContains(String title)
    {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {

                if (h1title.getText().contains(title)) {
                    return Boolean.TRUE;
                }
                return null;
            }
        });
    }

    public ArrayList<WebElement> getItemsOnPage() {
        return (ArrayList<WebElement>) driver.findElements(By.cssSelector(getBaseItemLocator()));

    }

    public WebElement getFirstItemOnPage() {
        return driver.findElement(By.cssSelector(getBaseItemLocator() + ":first-child"));
    }

    /**
     * @param item Товар на странице, только для елементов BASE_ITEM_LOCATOR
     * @return название товара
     */
    public String getTitleOfItem(WebElement item) throws Exception {
        By by=By.cssSelector(getBaseItemLocator()+">a");
        DriverWaitUtils.waitFor(by,10);
        return item.findElement(by).getAttribute("title");
    }

    public String getDescOfFirstItemAfterFiltering() {
        waitForItemsRefreshed();
        return driver.findElement(By.cssSelector("div.n-snippet-cell2:first-child div.n-snippet-cell2__title>a")).getAttribute("title");
    }

    public void switchNumOfElementsOnPageTo12() throws Exception {
        numOfElementsOnPageButton.click();
        By elements12 = By.cssSelector("div.popup div.select__list>div:first-child");
        DriverWaitUtils.waitForClickable(elements12, 5);
        WebElement element = driver.findElement(elements12);
        element.click();
        waitForItemsRefreshed();
    }


    public void sendKeysToPriceFromFilter(String keys) {
        priceFromField.sendKeys(keys);
    }

    public void sendKeysToSearchField(String keys) {
        searchField.sendKeys(keys);
    }
}
