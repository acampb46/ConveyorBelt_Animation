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
import java.awt.*;
import java.util.List;

public class ConveyorBeltSimulation extends JPanel {
    private final List<ConveyorBelt> conveyorBelts;

    public ConveyorBeltSimulation(List<ConveyorBelt> conveyorBelts) {
        this.conveyorBelts = conveyorBelts;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;

        int beltY = 100;
        int beltHeight = 50;

        String[] beltLabels = {"Belt AB", "Belt BC", "Belt CD"};

        for (int i = 0; i < conveyorBelts.size(); i++) {
            ConveyorBelt belt = conveyorBelts.get(i);
            int beltX = 50;

            // Draw labels for the conveyor belts
            g2d.setColor(Color.BLACK);
            g2d.drawString(beltLabels[i], 10, beltY + beltHeight / 2);

            for (Widget widget : belt.getWidgets()) {
                g2d.setColor(widget.getColor());

                String hashCodeText = Integer.toString(widget.hashCode());

                int widgetWidth = g2d.getFontMetrics().stringWidth(hashCodeText) + 10;

                g2d.fillRect(beltX, beltY, widgetWidth, 20);

                g2d.setColor(Color.BLACK);
                g2d.drawString(hashCodeText, beltX + 5, beltY + 12);

                beltX += widgetWidth + 20;
            }
            beltY += beltHeight + 20;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
}
