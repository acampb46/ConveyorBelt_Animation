/*
 * Ashlee Gerard
 * COSC 525
 * Project 2: Conveyor Belt
 * 19 February 2024
 *
 * This program utilizes threads to simulate a production line where four workers produce, place, and retrieve widgets from conveyor belts.
 *
 */
import java.awt.*;
import java.util.Objects;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Worker implements Runnable {
    private final String workerName;
    private final ConveyorBelt in;
    private final ConveyorBelt out;
    private final double waiting;
    private final Random random = new Random();


    public Worker(String workerName, ConveyorBelt in, ConveyorBelt out, double waiting) {
        this.workerName = workerName;
        this.in = in;
        this.out = out;
        this.waiting = waiting;
    }

    @Override
    public void run() {
        int widgetBProduced = 0, widgetCProduced = 0, widgetDProduced = 0;
        int widgetProduction = 24;
        boolean bool = true;
        try {
            do {
                //Worker A
                if (Objects.equals(workerName, "A")) {
                    for (int i = 1; i <= widgetProduction; i++) {
                        Widget widget = new Widget(0, generateRandomColor());
                        out.place(widget, "A");
                        sleep((long) waiting);
                    }
                    bool = false;
                }
                //Worker B
                else if (Objects.equals(workerName, "B")) {
                    if (widgetBProduced < widgetProduction) {
                        sleep((long) waiting);
                        Widget widget = in.retrieve(workerName);
                        sleep((long) waiting);
                        widgetWork(widget);
                        sleep((long) waiting);
                        out.place(widget, workerName);
                    } else {
                        bool = false;
                    }
                    ++widgetBProduced;
                }
                //Worker C
                else if (Objects.equals(workerName, "C")) {
                    if (widgetCProduced < widgetProduction) {
                        sleep((long) waiting);
                        Widget widget = in.retrieve(workerName);
                        sleep((long) waiting);
                        widgetWork(widget);
                        sleep((long) waiting);
                        out.place(widget, workerName);
                    } else {
                        bool = false;
                    }
                    ++widgetCProduced;
                }
                //Worker D
                else {
                    if (widgetDProduced < widgetProduction) {
                        sleep((long) waiting);
                        Widget widget = in.retrieve(workerName);
                        sleep((long) waiting);
                        widgetWork(widget);
                        sleep((long) waiting);
                    } else bool = false;
                    ++widgetDProduced;
                }
            } while (bool);
        } catch (InterruptedException e) {
            System.out.printf(workerName + " interrupt\n");
            Thread.currentThread().interrupt();
        }
    }

    private void widgetWork(Widget widget) throws InterruptedException {
        System.out.printf("Worker %s is working on %s.\n", workerName, widget.hashCode());
        widget.workUpon();
    }

    private Color generateRandomColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
