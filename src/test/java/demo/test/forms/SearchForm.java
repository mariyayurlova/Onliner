package demo.test.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.BaseForm;
import webdriver.elements.BaseElement;
import webdriver.elements.CheckBox;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mariya on 18.04.2016.
 */
public class SearchForm extends BaseForm {

    private String locManufacturer = "//li/label/span[text()='%s']";
    private CheckBox cbManufacturer;
    private Label lblDiagonalFrom = new Label(By.xpath("//div/select[@class='schema-filter-control__item']/option[@value='370']"), "DropDownFrom");
    private Label lblDiagonalTo = new Label(By.xpath("//div/following-sibling::div/select[@class='schema-filter-control__item']"), "DropDownTo");
    private TextBox txtPrice = new TextBox(By.xpath("//div/input[@placeholder='до']"), "Price");
    private TextBox txtDate = new TextBox(By.xpath("//div/input[@placeholder='2012']"), "Date");
    private String locDiagonalFrom = "//div/select[@class='schema-filter-control__item']/option[contains(text(),'%s')]";
    private Label lblDiagonalFromValue;
    private String locDiagonalTo = "//div/following-sibling::div/select[@class='schema-filter-control__item']/option[contains(text(),'%s')]";
    private Label lblDiagonalToValue;
    private Label lblIsPresent = new Label(By.className("schema-products"), "Logo");
    private Label lbLogo = new Label(By.xpath("//div/h1[@class='schema-header__title']"), "Logo");


    private BaseElement beList = new BaseElement(By.className("schema-header__title"), "Onliner.by") {
        @Override
        protected String getElementType() {
            return null;
        }
    };




    /**
     * Конструктор
     */
    public SearchForm() {
        super(By.className("schema-header__title"), "Onliner.by");
    }

    /**
     * Проверка на присутствие элемента
     */
    public void assertLogo(){

        assert(lbLogo.isPresent());
    }


    /**
     * Выбираем производителя
     * @param manufacturer производитель
     */
    public void checkManufacturer(String manufacturer){
        cbManufacturer = new CheckBox(By.xpath((String.format(locManufacturer,manufacturer))), "Catalog");
        cbManufacturer.click();
        browser.waitForPageToLoad();
    }


    /**
     * Вводим цену
     * @param price цена
     */
    public void inputPrice(String price){
        txtPrice.type(price);
    }

    /**
     * Вводим дату
     * @param date дата
     */
    public void inputDate(String date){
        txtDate.type(date);
    }

    /**
     * Выбираем диагональ от
     * @param diagonalFrom диагональ от
     */
    public void selectDiagonalFrom(String diagonalFrom){
        lblDiagonalFrom.click();
        lblDiagonalFromValue = new Label (By.xpath(String.format(locDiagonalFrom,diagonalFrom)), "DiagonalFrom");
        lblDiagonalFromValue.click();
        browser.webDriverWait("schema-product__title");

    }

    /**
     * Выбираем диагональ до
     * @param diagonalTo диагональ до
     */
    public void selectDiagonalTo(String diagonalTo){
        lblDiagonalTo.click();
        lblDiagonalToValue = new Label (By.xpath(String.format(locDiagonalTo,diagonalTo)), "DiagonalTo");
        lblDiagonalToValue.click();
        browser.webDriverWait("schema-product__title");

    }

    /**
     * Проверяем результаты введенной информации
     * @param manufacturer производитель
     * @param date дата
     * @param price цена
     * @param diagonalFrom диагональ от
     * @param diagonalTo диагональ до
     */
    public void checkResult(String manufacturer, String date, String diagonalFrom, String diagonalTo, String price) throws InterruptedException {
        browser.webDriverWait("schema-product__title");
        Label lblCheck = new Label(By.xpath("//div[@id='schema-filter']/following-sibling::div//span[contains(text(),'Найдено')]"),"");
        lblCheck.waitForIsElementPresent();
        List<WebElement> list = beList.findElements("//div[@class='schema-product__title']/a[@data-bind='attr: {href: product.html_url}']/span[@data-bind='html: product.full_name']");
        info(String.valueOf(list.size()));

        browser.webDriverWait("schema-product__title");
        for (int i=0; i<list.size(); i++) {
            ResultForm resultForm = new ResultForm();
            browser.webDriverWait("schema-product__title");
            list = beList.findElements("//div[@class='schema-product__title']/a[@data-bind='attr: {href: product.html_url}']/span[@data-bind='html: product.full_name']");
            list.get(i).click();
            try{

                if((resultForm.testName(manufacturer) == true) & (resultForm.testDiagonl(diagonalFrom,diagonalTo)==true) & (resultForm.testDate(date)==true) & (resultForm.testPrice(price)==true)){

                    browser.goBack();
                }
            }
            catch (NoSuchElementException e ){
                error("Item does not match");
            }




        }

        }



}
