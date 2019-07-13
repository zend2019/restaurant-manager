package main.java.GUI;

import javax.swing.*;

public abstract class IWorkPanel extends IBaseWorkPanel {
    protected abstract void setComboBoxes();
    protected abstract void setValidationLabelsVisibility(boolean visibility);
}
