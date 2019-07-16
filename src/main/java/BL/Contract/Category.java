package main.java.BL.Contract;

public enum Category {
    Dairy(1),
    Meat(2),
    Fruit(3),
    Vegetables(4),
    Bakery(5),
    Alcohol(6);

    private int value;

    Category(int value) {
        this.value = value;
    }

    public int getValue(){
       return value;
    }
}

//TODO: decide if to keep this enum, or use just the DB table