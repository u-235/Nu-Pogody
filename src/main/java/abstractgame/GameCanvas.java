package abstractgame;

import javax.swing.Icon;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.Timer;

@SuppressWarnings("serial")
public abstract class GameCanvas extends JComponent implements KeyListener
{
    GameView     first;
    Timer        gameTimer;
    boolean      running;
    boolean      focused;
    private Icon backIcon;

    public GameCanvas() {
        this.setFocusable(true);
        addKeyListener(this);
        addFocusListener(new FocusListener() {

            public void focusLost(FocusEvent e) {
                focused = false;
                updateRun();
            }

            public void focusGained(FocusEvent e) {
                focused = true;
                updateRun();
            }
        });
        gameTimer = new Timer(700, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onTimer();
            }
        });
    }

    public boolean isFocused() {
        return focused;
    }

    void updateRun() {
        if (focused && running) gameTimer.start();
        else gameTimer.stop();
    }

    public boolean isRunning() {
        return gameTimer.isRunning();
    }

    public void stop() {
        running = false;
        updateRun();
    }

    public void start() {
        running = true;
        updateRun();
    }

    public void setPeriod(int period) {
        gameTimer.setDelay(period);
    }

    public void addView(GameView view) {
        GameView curr = first;
        GameView last = null;

        while (curr != null) {
            last = curr;
            curr = last.next;
        }

        if (last == null) first = view;
        else last.next = view;

        view.next = null;
        view.comp = this;
    }

    public void update() {
        GameView turn = first;

        while (turn != null) {
            turn.update();
            turn = turn.next;
        }
    }

    public abstract void onTimer();

    public abstract void keyTyped(KeyEvent e);

    public abstract void keyPressed(KeyEvent e);

    public abstract void keyReleased(KeyEvent e);

    public void paintComponent(Graphics g) {
        GameView turn = first;

        if (backIcon != null) backIcon.paintIcon(this, g, 0, 0);

        while (turn != null) {
            turn.paint(this, g);
            turn = turn.next;
        }
    }

    public Icon getIcon() {
        return backIcon;
    }

    public void setIcon(Icon backIcon) {
        this.backIcon = backIcon;
        update();
        repaint();
    }

}
