package abstractgame;


import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class GameView
{
    GameViewListener   listener;
    Component          comp;
    protected GameView next;

    GameIcons          icons;
    Icon               icon;
    private boolean    visible = true;
    private boolean    changed;
    private int        posX;
    private int        posY;

    public GameView(GameViewListener listener, GameIcons icons) {
        this.listener = listener;
        this.icons = icons;
        // icon=icons.getIcon(Icons.EGG_0);
    }

    protected void setChanged(boolean c) {
        changed = c;

        if (changed ) {//&& visible
            if (icon != null && comp != null && icon != null)
                comp.repaint(posX, posY, icon.getIconWidth(),
                                icon.getIconHeight());
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean v) {
        boolean old = visible;
        visible = v;
        if (old != visible) setChanged(true);
    }

    public void setIcon(int index) {
        icon = icons.getIcon(index);
        setChanged(true);
    }

    public boolean requestPaint() {
        return visible ? changed : false;
    }

    public void begin(int state) {
    }

    public void update() {
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public void setPos(int x, int y) {
        int oldX = posX, oldY = posY;
        if (isVisible() && comp != null && icon != null)
            comp.repaint(oldX, oldY, icon.getIconWidth(), icon.getIconHeight());
        posX = x;
        posY = y;
        setChanged(true);
    }

    public void fireEvent(String reason) {
        if (listener != null)
            listener.onViewEvent(new GameEvent(this, reason));
    }

    public void paint(Component c, Graphics g) {
        if (!isVisible()) return;

        setChanged(false);
        if (icon == null) return;
        icon.paintIcon(c, g, posX, posY);
    }
}
