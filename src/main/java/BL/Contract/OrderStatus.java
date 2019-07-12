package main.java.BL.Contract;

public enum OrderStatus {
    inProcess(1),
    Delivered(2),
    Completed(3);

    private int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
