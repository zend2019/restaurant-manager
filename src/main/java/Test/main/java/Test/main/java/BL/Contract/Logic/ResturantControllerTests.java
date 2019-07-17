package main.java.Test.main.java.BL.Contract.Logic;

import main.java.BL.Contract.Logic.ResturantController;
import main.java.database.RestaurantRepository;
import org.junit.*;

public class ResturantControllerTests {

    private static ResturantController resturantController;
    private static double budget;
    private static final int RESTURANT_ID = 1;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        resturantController = new ResturantController();
    }

    @Before
    public void testInitalize() {
        budget = 0;
    }

    @After
    public void cleanUo() {
        if (budget != 0) {
            RestaurantRepository.updateBudget(RESTURANT_ID, budget, false);
        }
    }


    @Test
    public void AddBudget_positiveBudget_true() {
        //Assert
        String budget = "100";
        Double currectBudget = RestaurantRepository.getRestaurant().getBudget();

        //Action
        boolean actualResult = resturantController.AddBudget(budget);
        ResturantControllerTests.budget = currectBudget;
        //Assert
        Double actualBudget = RestaurantRepository.getRestaurant().getBudget();
        Assert.assertTrue(actualResult);
        Assert.assertEquals(Double.parseDouble(budget) + currectBudget, actualBudget);
    }

    @Test
    public void AddBudget_zeroBudget_true() {
        //Assert
        String budget = "0";
        Double currectBudget = RestaurantRepository.getRestaurant().getBudget();

        //Action
        boolean actualResult = resturantController.AddBudget(budget);
        //Assert
        Double actualBudget = RestaurantRepository.getRestaurant().getBudget();
        Assert.assertTrue(actualResult);
        Assert.assertEquals(currectBudget, actualBudget);
    }

    @Test
    public void AddBudget_notDoubleBudget_false() {
        //Assert
        String budget = "sdfsd";
        Double currectBudget = RestaurantRepository.getRestaurant().getBudget();

        //Action
        boolean actualResult = resturantController.AddBudget(budget);
        //Assert
        Double actualBudget = RestaurantRepository.getRestaurant().getBudget();
        Assert.assertFalse(actualResult);
        Assert.assertEquals(currectBudget, actualBudget);
    }
}
