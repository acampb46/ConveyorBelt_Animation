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

import static java.lang.Thread.sleep;

public class Widget {
    private int widgetCounter;

    private final Color color;

    public Widget(int widgetCounter, Color color) {
        this.widgetCounter = widgetCounter;
        this.color = color;
    }

    public synchronized void workUpon() throws InterruptedException {
        ++widgetCounter;
        System.out.printf("Widget %s has been worked on %d time(s).\n", this.hashCode(), widgetCounter);
        sleep(2000);
    }

    public Color getColor() {
        return color;
    }
}
