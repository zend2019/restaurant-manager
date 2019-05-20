package main.java.GUI;

import javax.swing.*;

public abstract class IWorkPanel extends JPanel{
    protected abstract void initialization();
    protected abstract void setMainLayout();
    protected abstract void setTableLayout();
    protected abstract void setSearchPanelLayout();
    protected abstract void setActionListeners();
    protected abstract void setValidationLabelsVisibility(boolean visibility);
}
