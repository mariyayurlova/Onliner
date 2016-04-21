package demo.test.forms;


import org.openqa.selenium.By;
import org.testng.Assert;
import webdriver.BaseForm;
import webdriver.elements.Label;

/**
 * Created by Mariya on 18.04.2016.
 */
public class MainForm extends BaseForm {

    private String locatorLabel = "//li/a/span[text()='%s']";
    private Label label;
    private Label lbLogo = new Label(By.xpath("//a/img[@class='onliner_logo retina-off']"), "Logo");
    private Label lblTesting = new Label(By.xpath("//span[@class='catalog-navigation-classifier__item-title-wrapper']"), "Item");


    /**
     * Конструктор
     */

    public MainForm() {
        super(By.className("fast-search__input"), "Onliner.by");
    }

    /**
     * Проверка на присутствие элемента
     */
    public void assertLogo(){

        assert(lbLogo.isPresent());
    }

    /**
     * Находим и выбираем каталог
     * @param insert каталог
     */
    public void searchCatalogLabel(String insert){
        label = new Label (By.xpath((String.format(locatorLabel,insert))), "Catalog");
        label.click();
        browser.waitForPageToLoad();
    }

    /**
     * Проверяем загрузился ли каталог
     */
    public void assertCatalogOpen(){
        lblTesting.waitForIsElementPresent();
        Assert.assertTrue(lblTesting.isPresent());
    }







}
