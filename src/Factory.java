/*
 * Ashlee Gerard
 * COSC 525
 * Project 2: Conveyor Belt
 * 19 February 2024
 *
 * This program utilizes threads to simulate a production line where four workers produce, place, and retrieve widgets from conveyor belts.
 *
 */

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Factory extends JFrame {

    public Factory() {
        //Create conveyor belt queues
        ConveyorBelt beltAB = new ConveyorBelt(3);
        ConveyorBelt beltBC = new ConveyorBelt(3);
        ConveyorBelt beltCD = new ConveyorBelt(3);

        //Create conveyor belt list
        List<ConveyorBelt> conveyorBelts = new ArrayList<>();
        conveyorBelts.add(beltAB);
        conveyorBelts.add(beltBC);
        conveyorBelts.add(beltCD);

        //Set up simulation panel
        ConveyorBeltSimulation conveyorBeltSimulation = new ConveyorBeltSimulation(conveyorBelts);
        add(conveyorBeltSimulation);

        // Associate each conveyor belt with the simulation panel
        beltAB.setSimulation(conveyorBeltSimulation);
        beltBC.setSimulation(conveyorBeltSimulation);
        beltCD.setSimulation(conveyorBeltSimulation);


        //Initialize JFrame
        setTitle("Factory Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(conveyorBeltSimulation);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        //Create threads for each worker
        Thread workerA = new Thread(new Worker("A", null, beltAB, Math.random() * 10000));
        Thread workerB = new Thread(new Worker("B", beltAB, beltBC, Math.random() * 20000));
        Thread workerC = new Thread(new Worker("C", beltBC, beltCD, Math.random() * 30000));
        Thread workerD = new Thread(new Worker("D", beltCD, null, Math.random() * 40000));

        //Start worker threads
        workerA.start();
        workerB.start();
        workerC.start();
        workerD.start();


        //Wait for everything to finish
        try {
            workerA.join();
            workerB.join();
            workerC.join();
            workerD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Production has ceased.\n");
        dispose();
    }

    public static void main(String[] args) {
        new Factory();
    }
}