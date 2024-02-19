/*
 * Ashlee Gerard
 * COSC 525
 * Project 2: Conveyor Belt
 * 19 February 2024
 *
 * This program utilizes threads to simulate a production line where four workers produce, place, and retrieve widgets from conveyor belts.
 *
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConveyorBelt {
    private final Queue<Widget> widgets;
    private final int widgetCapacity;
    private ConveyorBeltSimulation simulation;

    public ConveyorBelt(int widgetCapacity) {
        this.widgets = new LinkedList<>();
        this.widgetCapacity = widgetCapacity;
    }

    public void setSimulation(ConveyorBeltSimulation simulation) {
        this.simulation = simulation;
    }

    public synchronized void place(Widget widget, String workerName) throws InterruptedException {
        while (widgets.size() >= widgetCapacity) {
            System.out.printf("Warning: Worker %s is waiting to place widget %s on the conveyor belt.\n", workerName, widget.hashCode());
            wait();
        }
        widgets.add(widget);
        System.out.printf("Worker %s is placing widget %s on the conveyor belt.\n", workerName, widget.hashCode());
        simulation.repaint();
        notifyAll();
    }

    public synchronized Widget retrieve(String workerName) throws InterruptedException {
        while (widgets.isEmpty()) {
            System.out.printf("Warning: Worker %s is idle.\n", workerName);
            wait();
        }
        Widget widget = widgets.remove();
        System.out.printf("Worker %s is retrieving widget %s from the conveyor belt.\n", workerName, widget.hashCode());
        simulation.repaint();
        notifyAll();
        return widget;
    }

    public synchronized List<Widget> getWidgets() {
        return new ArrayList<>(widgets);
    }
}

