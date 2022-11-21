package de.holube.fakestudy.ui;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Window {

    private final JFrame jFrame = new JFrame();

    private final JProgressBar jProgressBar = new JProgressBar();
    AtomicInteger progress = new AtomicInteger(0);

    public Window() {
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                LOG.info("Window closed. Exiting...");
                System.exit(0);
            }
        });
        jFrame.setMinimumSize(new Dimension(400, 200));

        jProgressBar.setMinimum(0);
        jFrame.add(jProgressBar);

        jFrame.setVisible(true);
    }

    public void setAmountOfStudies(int amount) {
        SwingUtilities.invokeLater(() -> jProgressBar.setMaximum(amount));
    }

    public void studyComplete() {
        SwingUtilities.invokeLater(() -> jProgressBar.setValue(progress.incrementAndGet()));
    }

    public void close() {
        jFrame.dispose();
    }

}
