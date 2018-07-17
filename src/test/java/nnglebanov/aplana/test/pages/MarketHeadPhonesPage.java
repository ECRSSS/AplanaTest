package nnglebanov.aplana.test.pages;

import nnglebanov.aplana.test.pages.parentpages.CatalogPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MarketHeadPhonesPage extends CatalogPage {

    @Override
    public String getBaseItemLocator()
    {
        return "div.n-snippet-cell2";
    }

    @Override
    public String getUrl()
    {
        return "https://market.yandex.ru/catalog/56179";
    }

    @FindBy(xpath ="//input[@id='7893318_8455647']/..")
    public WebElement beatsFilterCheckbox;

}
