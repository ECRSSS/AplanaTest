package nnglebanov.aplana.test.pages;

import nnglebanov.aplana.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MarketItemPage extends BasePage {

    @Override
    protected String getUrl() {
        return "https://market.yandex.ru/product";
    }

    @FindBy(css = "h1.title")
    private WebElement title;

    public String getTitle() {
        return title.getText();
    }
}
