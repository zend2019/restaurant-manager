package main.java.BL.Contract.Logic;

import main.java.BL.Contract.Inventory;
import main.java.BL.Contract.Product;
import main.java.BL.Contract.Report;

import java.util.List;

public interface IReportMaker {
    Report dailyReportMaker(List<Product> products);
}
