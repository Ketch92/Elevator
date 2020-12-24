package emulation;

import building.Building;
import building.Floor;
import elevator.AbstractElevator.Direction;
import elevator.Elevator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class ElevatorSimulation extends javax.swing.JFrame {
    private Building building;
    private Elevator elevator;
    private JButton nextBtn;
    private JButton startOverBtn;
    private JTextField elevatorContainmentTxt;
    private JTextArea buildingLevelsText;
    
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
            throw new RuntimeException("Something went wrong with setting UI", e);
        }
        EventQueue.invokeLater(ElevatorSimulation::run);
    }
    
    private void initializeComponents() {
        building = new Building.BuildingConstructor().build();
        elevator = new Elevator();
    
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
    
        JPanel centralPanel = new JPanel();
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
        buildingLevelsText.setEditable(false);
        buildingLevelsText.setVisible(true);
        updateBuildingText();
        
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
    
    private void update() {
        updateElevatorTextField();
        updateBuildingText();
    }
    
    private void updateBuildingText() {
        buildingLevelsText.setText("");
        Floor[] buildingLevels = building.getBuildingLevels();
        for (int i = buildingLevels.length - 1; i >= 0; i--) {
            Floor floor = buildingLevels[i];
            buildingLevelsText.append(floor.getName() + " ");
            buildingLevelsText.append(floor.getQueue().toString() + "\n");
        }
    }
    
    private void updateElevatorTextField() {
        elevatorContainmentTxt.setText("Level " + elevator.getFloorPosition()
                + "  " + elevator.getContainment().toString());
    }
    
    private void startActionPerformed(ActionEvent e) {
        building = new Building.BuildingConstructor().build();
        elevator = new Elevator();
        int upperFloorToRun = 0;
        int lowerFloorToRun = 0;
        update();
    }
    
    private void nextButtonActionPerformed(ActionEvent e) {
        runElevator();
    }
    
    private void runElevator() {
        release();
        pickUp();
        moveElevator();
    }
    
    private void moveElevator() {
        if (elevator.getFloorPosition() == building.getBuildingLevels().length - 1) {
            elevator.setDirection(Direction.DOWN);
        }
        if (elevator.getFloorPosition() == 0) {
            elevator.setDirection(Direction.UP);
        }
        
        if (elevator.getDirection().equals(Direction.UP)) {
            elevator.moveUp();
            return;
        }
        elevator.moveDown();
        update();
    }
    
    private void release() {
        if (elevator.getContainment().isEmpty()) {
            return;
        }
        Floor floor = building.getBuildingLevels()[elevator.getFloorPosition()];
        
        List<Integer> liftedList = elevator.lifted();
        if (!liftedList.isEmpty()) {
            for (Integer person : liftedList) {
                floor.getQueue().add(person);
            }
        }
        update();
    }
    
    private void pickUp() {
        Floor floor = building.getBuildingLevels()[elevator.getFloorPosition()];
        if (floor.isEmptyQueue() || !elevator.hasSpace()) {
            return;
        }
        Integer person = floor.getQueue().peek();
        if (person == elevator.getFloorPosition()) {
            return;
        }
        while (elevator.hasSpace() && !floor.isEmptyQueue()) {
            elevator.pickUp(floor.pollPerson());
        }
        update();
    }
}
