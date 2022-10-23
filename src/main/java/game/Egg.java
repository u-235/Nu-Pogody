/*
 * Copyright 2013 © Nick Egorrov, nicegorov@yandex.ru.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package game;

import abstractgame.GameIcons;
import abstractgame.GameView;
import abstractgame.GameViewListener;

public class Egg extends GameView implements Side
{
    final static int        DELTA_X            = 120;
    final static int        DELTA_Y            = 60;

    public final static int BEGIN_TOP_LEFT     = 0;
    public final static int BEGIN_TOP_RIGHT    = 100;
    public final static int BEGIN_BOTTOM_LEFT  = 200;
    public final static int BEGIN_BOTTOM_RIGHT = 300;
    int                     rotate;
    int                     side               = 3;
    int                     step;

    public Egg(GameViewListener listener, GameIcons icons) {
        super(listener, icons);
    }

    void rotateEgg() {
        if (side == SIDE_TOP_LEFT || side == SIDE_BOTTOM_LEFT) rotate++;
        else rotate--;

        if (rotate > 3) rotate = 0;
        if (rotate < 0) rotate = 3;

        switch (rotate) {
        case 0:
            setIcon(Icons.EGG_0);
            break;
        case 1:
            setIcon(Icons.EGG_1);
            break;
        case 2:
            setIcon(Icons.EGG_2);
            break;
        default:
        case 3:
            setIcon(Icons.EGG_3);
        }

    }

    void setStep(int step) {
        this.step = step;

        if (step >= 99) setVisible(false);
        else if (step < 5) {
            int x, y, dx = DELTA_X;

            switch (side) {
            case SIDE_TOP_LEFT:
                x = 33;
                y = 137;
                break;
            case SIDE_BOTTOM_LEFT:
                x = 33;
                y = 253;
                break;
            case SIDE_TOP_RIGHT:
                x = 437;
                y = 137;
                dx = -dx;
                break;
            default:
            case SIDE_BOTTOM_RIGHT:
                x = 437;
                y = 253;
                dx = -dx;
                break;
            }

            setPos(x + dx * step/5, y + DELTA_Y * step/5);
            rotateEgg();
        }
        else if (step == 5) {
            fireEvent("catch");
        }
        else if (step == 6) {
            // Яйцо разбилось
            if (side == SIDE_BOTTOM_LEFT || side == SIDE_TOP_LEFT) {
                setPos(112, 357);
            }
            else {
                setPos(364, 357);
            }
            
            setIcon(Icons.CRASH);
        }
        else {
            // Цыплёнок побежал
            int x = getX();
            if (x < 0 || x > 470) setVisible(false);

            rotate++;
            if (rotate > 3) rotate = 0;

            if (side == SIDE_BOTTOM_LEFT || side == SIDE_TOP_LEFT) {
                setPos(x - 12, getY());

                switch (rotate) {
                case 0:
                    setIcon(Icons.STEP_L0);
                    break;
                case 1:
                    setIcon(Icons.STEP_L1);
                    break;
                case 2:
                    setIcon(Icons.STEP_L2);
                    break;
                case 3:
                    setIcon(Icons.STEP_L3);
                    break;
                }
            }
            else {
                setPos(x + 12, getY());

                switch (rotate) {
                case 0:
                    setIcon(Icons.STEP_R0);
                    break;
                case 1:
                    setIcon(Icons.STEP_R1);
                    break;
                case 2:
                    setIcon(Icons.STEP_R2);
                    break;
                case 3:
                    setIcon(Icons.STEP_R3);
                    break;
                }
            }
        }
    }

    @Override
    public void begin(int state) {
        side = state / 100;
        setStep(state % 100);
        setVisible(true);
    }

    @Override
    public void update() {
        if (!isVisible()) return;
        setStep(step + 1);
    }

    public int getSide() {
        // TODO Auto-generated method stub
        return side;
    }

}
