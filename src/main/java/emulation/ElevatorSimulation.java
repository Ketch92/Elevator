package emulation;

import building.Building;
import building.Floor;
import elevator.Elevator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;

public class ElevatorSimulation extends javax.swing.JFrame {
    private Building building;
    private Elevator elevator;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel centralPanel;
    private JButton nextBtn;
    private JButton startOverBtn;
    private JTextField elevatorContainmentTxt;
    private JTextArea buildingLevelsText;
    private int nextBtnCycle;
    private int maxLevelToRun;
    
    public ElevatorSimulation() {
        initializeComponents();
    }
    
    private static void run() {
        new ElevatorSimulation().setVisible(true);
    }
    
    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            new RuntimeException("Something went wrong with setting UI", e);
        }
        
        EventQueue.invokeLater(ElevatorSimulation::run);
    }
    
    private void initializeComponents() {
        building = new Building.BuildingConstructor().build();
        elevator = new Elevator(building);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        buttonPanel = new JPanel();
        
        centralPanel = new JPanel();
        centralPanel.setLayout(new BorderLayout());
        
        nextBtn = new JButton("Next step");
        nextBtn.setMargin(new Insets(0, 25, 0, 25));
        nextBtn.addActionListener(this::nextButtonActionPerformed);
        startOverBtn = new JButton("Start again");
        startOverBtn.setMargin(new Insets(0, 25, 0, 25));
        startOverBtn.addActionListener(this::startActionPerformed);
        
        elevatorContainmentTxt = new JTextField();
        elevatorContainmentTxt.setEditable(false);
        elevatorContainmentTxt.setToolTipText("This text shows the people" +
                " that elevator currently carrying");
        updateElevatorTextField();
        
        buildingLevelsText = new JTextArea();
        updateText(buildingLevelsText);
        buildingLevelsText.setEditable(false);
        buildingLevelsText.setVisible(true);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Elevator");
        this.setPreferredSize(new Dimension(300, 500));
        
        buttonPanel.add(startOverBtn);
        buttonPanel.add(nextBtn);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(centralPanel, BorderLayout.CENTER);
        centralPanel.add(elevatorContainmentTxt, BorderLayout.NORTH);
        centralPanel.add(buildingLevelsText, BorderLayout.CENTER);
        this.getContentPane().add(mainPanel);
        
        pack();
    }
    
    private void updateText(JTextArea jTextArea) {
        jTextArea.setText("");
        Floor[] buildingLevels = building.getBuildingLevels();
        for (int i = buildingLevels.length - 1; i >= 0; i--) {
            Floor floor = buildingLevels[i];
            jTextArea.append(floor.getName() + " ");
            jTextArea.append(floor.getQueue().toString() + "\n");
        }
    }
    
    private void updateElevatorTextField() {
        elevatorContainmentTxt.setText("Level " + elevator.getFloorPosition()
                + "  " + elevator.getContainment().toString());
    }
    
    private void startOverButtonPerformed() {
        building = new Building.BuildingConstructor().build();
        elevator = new Elevator(building);
        updateElevatorTextField();
        updateText(buildingLevelsText);
        nextBtnCycle = 0;
    }
    
    private void startActionPerformed(ActionEvent e) {
        startOverButtonPerformed();
    }
    
    private void nextButtonActionPerformed(ActionEvent e) {
        switch (nextBtnCycle) {
            case 0:
                pickUp();
                break;
        }
    }
    
    private void pickUp() {
        List<Integer> queue = building.getBuildingLevels()[elevator.getFloorPosition()].getQueue();
        elevator.pickUp(queue.toArray(Integer[]::new));
        
        updateElevatorTextField();
    }
    
    
}