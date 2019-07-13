package main.java.GUI;

import javax.swing.*;

public abstract class IBaseWorkPanel extends JPanel {
    protected abstract void initialization();
    protected abstract void setMainLayout();
    protected abstract void setTableLayout();
    protected abstract void setSearchPanelLayout();
    protected abstract void alignFieldSizes();
    protected abstract void setActionListeners();

}
