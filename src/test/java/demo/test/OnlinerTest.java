package demo.test;

import demo.test.forms.CatalogForm;
import demo.test.forms.MainForm;
import demo.test.forms.SearchForm;
import webdriver.BaseTest;

/**
 * Created by Mariya on 18.04.2016.
 */
public class OnlinerTest extends BaseTest {



    public void runTest() throws InterruptedException {


        logger.step(1-3);
        MainForm mainForm = new MainForm();
        mainForm.assertLogo();
        mainForm.searchCatalogLabel(insert);
        mainForm.assertCatalogOpen();


        logger.step(4-5);
        CatalogForm catalogForm = new CatalogForm();
        catalogForm.assertLogo();
        catalogForm.checkCatalog(catalog);

        logger.step(6-7);
        catalogForm.checkSection(section);
        catalogForm.testingSection(section);


        logger.step(8-9);
        SearchForm searchForm = new SearchForm();
        searchForm.assertLogo();
        searchForm.checkManufacturer(manufacturer);

        logger.step(10);
        searchForm.inputPrice(price);

        logger.step(11);
        searchForm.inputDate(date);

        logger.step(12);
        searchForm.selectDiagonalFrom(diagonalFrom);

        logger.step(13);
        searchForm.selectDiagonalTo(diagonalTo);

        logger.step(14);
        searchForm.checkResult(manufacturer, date, diagonalFrom, diagonalTo, price);



    };
}
