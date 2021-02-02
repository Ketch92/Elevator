package emulation;

import building.Building;
import building.Floor;
import elevator.Elevator;
import elevator.abstractelevator.Direction;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ElevatorSimulation extends javax.swing.JFrame {
    private Building building;
    private Elevator elevator;
    private JTextField elevatorContainmentTxt;
    private JTextArea buildingLevelsText;
    private JButton nextBtn;
    private JButton startOverBtn;
    private JButton autoPlay;
    private boolean isAutoplayOn;
    private int upperFloorToRun;
    private int lowerFloorToRun;
    
    private ElevatorSimulation() {
        initializeComponents();
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
    
    private static void run() {
        new ElevatorSimulation().setVisible(true);
    }
    
    private void initializeComponents() {
        building = new Building.BuildingConstructor().build();
        elevator = new Elevator();
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BorderLayout());
        
        nextBtn = new JButton("Next step");
        nextBtn.setMargin(new Insets(0, 25, 0, 25));
        nextBtn.addActionListener(this::nextButtonActionPerformed);
        startOverBtn = new JButton("Start again");
        startOverBtn.setMargin(new Insets(0, 25, 0, 25));
        startOverBtn.addActionListener(this::startActionPerformed);
        autoPlay = new JButton("Auto");
        autoPlay.addActionListener(this::autoPlayActionPerformed);
        autoPlay.setMargin(new Insets(0, 25, 0, 25));
        
        elevatorContainmentTxt = new JTextField();
        elevatorContainmentTxt.setEditable(false);
        elevatorContainmentTxt.setToolTipText("This text shows the people"
                + " that elevator currently carrying");
        updateElevatorTextField();
        
        buildingLevelsText = new JTextArea();
        buildingLevelsText.setEditable(false);
        buildingLevelsText.setVisible(true);
        updateBuildingText();
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Elevator");
        this.setPreferredSize(new Dimension(350, 500));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startOverBtn);
        buttonPanel.add(nextBtn);
        buttonPanel.add(autoPlay);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(centralPanel, BorderLayout.CENTER);
        centralPanel.add(elevatorContainmentTxt, BorderLayout.NORTH);
        centralPanel.add(buildingLevelsText, BorderLayout.CENTER);
        this.getContentPane().add(mainPanel);
        
        pack();
    }
    
    private void autoPlayActionPerformed(ActionEvent actionEvent) {
        if (isAutoplayOn) {
            isAutoplayOn = false;
            startOverBtn.setEnabled(true);
            nextBtn.setEnabled(true);
            
        } else {
            isAutoplayOn = true;
            startOverBtn.setEnabled(false);
            nextBtn.setEnabled(false);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runElevator();
                }
            }, 50, 2000);
        }
    }
    
    private void startActionPerformed(ActionEvent e) {
        building = new Building.BuildingConstructor().build();
        elevator = new Elevator();
        upperFloorToRun = 0;
        lowerFloorToRun = 0;
        update();
    }
    
    private void nextButtonActionPerformed(ActionEvent e) {
        runElevator();
    }
    
    private void update() {
        updateElevatorTextField();
        updateBuildingText();
    }
    
    private void updateBuildingText() {
        buildingLevelsText.setText("");
        Floor[] buildingLevels = building.getLevels();
        Floor floor;
        for (int i = buildingLevels.length - 1; i >= 0; i--) {
            floor = buildingLevels[i];
            buildingLevelsText.append("Level " + floor.getLevel() + " ");
            buildingLevelsText.append(floor.getQueue().toString() + "\n");
        }
    }
    
    private void updateElevatorTextField() {
        elevatorContainmentTxt.setText("Level " + elevator.getFloorPosition()
                + "  " + elevator.getContainment().toString());
    }
    
    private void runElevator() {
        if (!building.areQueuesEmpty() || !elevator.getContainment().isEmpty()) {
            release();
            update();
            pickUp();
            update();
            moveElevator();
            update();
            setLevelsToRun();
        }
    }
    
    private void moveElevator() {
        if (elevator.getDirection().equals(Direction.UP)) {
            elevator.moveUp();
            return;
        }
        elevator.moveDown();
    }
    
    private void release() {
        if (elevator.getContainment().isEmpty()) {
            return;
        }
        Floor floor = building.getLevels()[elevator.getFloorPosition()];
        
        List<Integer> liftedList = elevator.lift();
        if (!liftedList.isEmpty()) {
            for (Integer person : liftedList) {
                floor.getQueue().add(person);
            }
        }
    }
    
    private void pickUp() {
        Floor floor = building.getLevels()[elevator.getFloorPosition()];
        Integer person = floor.getQueue().peek();
        if (floor.isEmptyQueue(elevator.getDirection())
                || !elevator.hasSpace()
                || person == elevator.getFloorPosition()) {
            return;
        }
        while (elevator.hasSpace()
                && !floor.isEmptyQueue(elevator.getDirection())) {
            elevator.pickUp(floor.pollPerson(elevator.getDirection()));
        }
    }
    
    private void setLevelsToRun() {
        lowerFloorToRun = elevator.getOccupancy() != 0
                ? elevator.getContainment().stream().min(Integer::compareTo).get()
                : 0;
        upperFloorToRun = elevator.getOccupancy() != 0
                ? elevator.getContainment().stream().max(Integer::compareTo).get()
                : building.getLevels().length - 1;
        if (elevator.getFloorPosition() == building.getLevels().length - 1
                || (elevator.getDirection().equals(Direction.UP)
                && elevator.getFloorPosition() == upperFloorToRun)) {
            elevator.setDirection(Direction.DOWN);
            return;
        }
        if (elevator.getFloorPosition() == 0
                || (elevator.getDirection().equals(Direction.DOWN)
                && elevator.getFloorPosition() == lowerFloorToRun)) {
            elevator.setDirection(Direction.UP);
        }
    }
}
