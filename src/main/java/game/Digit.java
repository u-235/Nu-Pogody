package game;

import abstractgame.GameIcons;
import abstractgame.GameView;
import abstractgame.GameViewListener;

public class Digit extends GameView
{
    int step;

    public Digit(GameViewListener listener, GameIcons icons) {
        super(listener, icons);
        setIcon(Icons.DIGIT_0);
    }

    @Override
    public void begin(int state) {
        switch (state) {
        default:
            setVisible(false);
            return;
        case 0:
            setIcon(Icons.DIGIT_0);
            break;
        case 1:
            setIcon(Icons.DIGIT_1);
            break;
        case 2:
            setIcon(Icons.DIGIT_2);
            break;
        case 3:
            setIcon(Icons.DIGIT_3);
            break;
        case 4:
            setIcon(Icons.DIGIT_4);
            break;
        case 5:
            setIcon(Icons.DIGIT_5);
            break;
        case 6:
            setIcon(Icons.DIGIT_6);
            break;
        case 7:
            setIcon(Icons.DIGIT_7);
            break;
        case 8:
            setIcon(Icons.DIGIT_8);
            break;
        case 9:
            setIcon(Icons.DIGIT_9);
            break;
        }
        setVisible(true);
    }

    @Override
    public void update() {
        if (!isVisible()) return;
    }
}
