package main.java.Test.main.java.BL.Contract.Logic;

import main.java.BL.Contract.Category;
import main.java.BL.Contract.Logic.ProductController;
import main.java.BL.Contract.Product;
import main.java.database.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class ProductContollerTests {

    private static ProductController productController;
    private static String productId;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        productController = new ProductController();
    }

    @Before
    public void testInitalize() {
        productId = "0";
    }

    @After
    public void cleanUo() {
        if (productId != "-1") {
            ProductRepository.deleteProduct(productId);
        }
    }

    @Test
    public void addProduct_currectAmount_success() {
        //Arrange
        Product product = createProduct(10);

        //Action
        String addedproductId = productController.addProduct(product);
        productId = addedproductId;

        //Assertion
        assertNotSame(-1, addedproductId);
    }

    @Test
    public void addProduct_inCurrectAmount_success() {
        //Arrange
        Product product = createProduct(0);

        //Action
        String addedproductId = productController.addProduct(product);
        productId = addedproductId;

        //Assertion
        assertEquals("-1", addedproductId);
    }


    private Product createProduct(int requireAmount) {
        Product product = new Product();
        product.setProductName("test");
        product.setRequiredAmount(requireAmount);
        product.setPrice("10");
        product.setProviderId("1");
        product.setCategory(Category.Alcohol);
        product.setExpirationDate(new Date());
        product.setCurrentProductAmount(1);
        return product;
    }
}
