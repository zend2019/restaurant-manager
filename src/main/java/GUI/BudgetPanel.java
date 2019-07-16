package main.java.GUI;

import main.java.BL.Contract.Employee;
import main.java.BL.Contract.Logic.IResturantManaging;
import main.java.BL.Contract.Logic.ResturantController;
import main.java.common.constants.GUIConstants;
import main.java.dataAccess.IRestaurantRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BudgetPanel extends JPanel {

    private JPanel addPanel;
    private JLabel budgetLable;
    private JTextField budgetToAdd;
    private JButton addBtn;
    private JLabel budgetAdded;
    private JLabel budgetNotAdded;
    private JLabel allRequired;
    private IResturantManaging restaurantRepository;

    public BudgetPanel() {
        budgetLable = new JLabel("Budget to add");
        addPanel = new JPanel();
        budgetToAdd = new JTextField();
        addBtn = new JButton("Add");
        budgetAdded = new JLabel("Budget added");
        budgetNotAdded = new JLabel("Failed to add budget");
        allRequired = new JLabel("Budget Requried");
        restaurantRepository = new ResturantController();

        setMainLayout();
        setAddPanellLayout();
        setAddBudgetListener();
        setValidationLabelsVisibility(false);
    }

    protected void setMainLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gcMainPanel = new GridBagConstraints();
        gcMainPanel.fill = GridBagConstraints.HORIZONTAL;

        gcMainPanel.ipady = 5;
        gcMainPanel.gridy = 0;
        gcMainPanel.weightx = 0.5;
        gcMainPanel.weighty = 0.1;

        gcMainPanel.gridx = 0;
        gcMainPanel.anchor = GridBagConstraints.PAGE_START;
        add(addPanel, gcMainPanel);
    }

    protected void setAddPanellLayout() {
        addPanel.setBorder(BorderFactory.createTitledBorder("Budget"));
        addPanel.setLayout(new GridBagLayout());
        GridBagConstraints gcSearchPanel = new GridBagConstraints();
        gcSearchPanel.fill = GridBagConstraints.CENTER;
        gcSearchPanel.insets = new Insets(5, 5, 5, 5);

        /////// First row ///////
        gcSearchPanel.gridy = 0;
        gcSearchPanel.weightx = 1;
        gcSearchPanel.weighty = 0.1;
        gcSearchPanel.gridx = 0;

        /////// Next row //////
        gcSearchPanel.gridy++;
        gcSearchPanel.weightx = 0.5;
        gcSearchPanel.weighty = 0.1;
        gcSearchPanel.gridx = 0;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        addPanel.add(budgetLable, gcSearchPanel);

        gcSearchPanel.gridx = 1;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        addPanel.add(budgetToAdd, gcSearchPanel);

        gcSearchPanel.gridx = 2;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        addPanel.add(addBtn, gcSearchPanel);

        //Validation labels
        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;

        allRequired.setForeground(Color.red);
        allRequired.setVisible(false);
        addPanel.add(allRequired, gcSearchPanel);

        budgetNotAdded.setForeground(Color.red);
        budgetNotAdded.setVisible(false);
        addPanel.add(budgetNotAdded, gcSearchPanel);


        budgetAdded.setForeground(Color.blue);
        budgetAdded.setVisible(false);
        addPanel.add(budgetAdded, gcSearchPanel);

        alignFieldSizes();
    }

    protected void alignFieldSizes() {
        Dimension fieldSize = budgetLable.getPreferredSize();
        budgetToAdd.setPreferredSize(fieldSize);

    }

    private void setAddBudgetListener() {
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (budgetLable.getText().equals(GUIConstants.EMPTY_FIELD)) {
                    setValidationLabelsVisibility(false);
                    allRequired.setVisible(true);
                } else {
                    setValidationLabelsVisibility(false);
                    boolean success = restaurantRepository.AddBudget(budgetToAdd.getText());

                    if (success)
                        budgetAdded.setVisible(true);
                    else {
                        budgetNotAdded.setVisible(true);
                    }
                }
            }
        });
    }

    public void setValidationLabelsVisibility(boolean visibility) {
        allRequired.setVisible(visibility);
        budgetAdded.setVisible(visibility);
        budgetNotAdded.setVisible(visibility);
    }

}
