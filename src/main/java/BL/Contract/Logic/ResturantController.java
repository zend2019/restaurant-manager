package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Restaurant;
import main.java.database.RestaurantRepository;

public class ResturantController implements IResturantManaging {
    @Override
    public boolean AddBudget(String budget) {
        double b;
        try {
            b = Double.parseDouble(budget);
        } catch (NumberFormatException e) {
            return false;
        }

        if (b == 0)
            return true;
        Restaurant restaurant = RestaurantRepository.getRestaurant();
        RestaurantRepository.updateBudget(restaurant.getId(), b, true);
        return true;
    }
}
