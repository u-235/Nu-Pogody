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

import java.awt.event.KeyEvent;
import java.util.Properties;
import java.util.Random;

import abstractgame.GameCanvas;
import abstractgame.GameEvent;
import abstractgame.GameIcons;
import abstractgame.GameView;
import abstractgame.GameViewListener;

public class Canvas extends GameCanvas
{
    private static final long serialVersionUID = -7307800376027346758L;
    Random                    delayRandom      = new Random();
    int                       eggDelay;
    int                       rubbitDelay;
    Egg[]                     egges            = new Egg[6];
    GameView                  rubbit;
    Wolf                      wolf;
    GameView[]                eggLive          = new GameView[3];
    Digit[]                   digits           = new Digit[4];
    private int               score;
    private int               level            = -1;
    private int               live             = 6;
    private boolean           over;
    private boolean           liveState;
    Properties                prop;

    public Canvas(Properties p) {
        GameView e;

        GameIcons icons = new Icons();

        setIcon(icons.getIcon(Icons.BACKGRAUND));

        wolf = new Wolf(null, icons);

        doLive(icons);

        doDigits(icons);

        rubbit = new GameView(null, icons);
        rubbit.setIcon(Icons.RUBBIT);
        rubbit.setPos(62, 14);
        addView(rubbit);

        e = new GameView(null, icons);
        e.setIcon(Icons.LEFT);
        e.setPos(0, 0);
        addView(e);

        e = new GameView(null, icons);
        e.setIcon(Icons.RIGHT);
        e.setPos(374, 80);
        addView(e);

        doEgges(icons);
        addView(wolf);

        prop = p;
        setScore(Integer.valueOf(prop.getProperty("score", "0")));
    }

    void doDigits(GameIcons icons) {
        for (int i = 0; i < digits.length; i++) {
            digits[i] = new Digit(null, icons);
            digits[i].setVisible(true);
            digits[i].setPos(200 + 55 * i, 10);
            addView(digits[i]);
        }
    }

    void doLive(GameIcons icons) {
        for (int i = 0; i < eggLive.length; i++) {
            eggLive[i] = new GameView(null, icons);
            eggLive[i].setIcon(Icons.LIVE);
            eggLive[i].setPos(270 + 55 * i, 120);
            addView(eggLive[i]);
        }
    }

    void doEgges(GameIcons icons) {

        for (int i = 0; i < egges.length; i++) {
            egges[i] = new Egg(onEgg, icons);
            egges[i].setVisible(false);
            addView(egges[i]);
        }
    }

    void gameNew() {
        over = false;

        setScore(1000);
        setScore(0);

        for (Egg e : egges) {
            e.setVisible(false);
        }

        start();
    }

    void gameOver() {
        over = true;
        stop();
        update();
        SoundEffect.GAME_OVER.play();
    }

    void checkLevel(int score) {
        int s = score / 100;
        if (s == level) return;

        if (score % 100 == 0) {
            setLive(6);
            SoundEffect.RESET.play();
        }

        level = s;

        switch (level % 10) {
        case 0:
            setPeriod(700);
            break;
        case 1:
            setPeriod(660);
            break;
        case 2:
            setPeriod(620);
            break;
        case 3:
            setPeriod(580);
            break;
        case 4:
            setPeriod(540);
            break;
        case 5:
            setPeriod(500);
            break;
        case 6:
            setPeriod(460);
            break;
        case 7:
            setPeriod(420);
            break;
        case 8:
            setPeriod(380);
            break;
        case 9:
            setPeriod(340);
            break;
        }
    }

    void updateLive() {
        boolean v0, v1, v2;
        liveState = !liveState;

        switch (live) {
        default:
            v0 = false;
            v1 = false;
            v2 = false;
            break;
        case 1:
            v0 = false;
            v1 = false;
            v2 = liveState;
            break;
        case 2:
            v0 = false;
            v1 = false;
            v2 = true;
            break;
        case 3:
            v0 = false;
            v1 = liveState;
            v2 = true;
            break;
        case 4:
            v0 = false;
            v1 = true;
            v2 = true;
            break;
        case 5:
            v0 = liveState;
            v1 = true;
            v2 = true;
            break;
        case 6:
            v0 = true;
            v1 = true;
            v2 = true;
            break;
        }

        eggLive[0].setVisible(v0);
        eggLive[1].setVisible(v1);
        eggLive[2].setVisible(v2);
    }

    void setLive(int l) {
        live = l;

        updateLive();
        if (live < 1) gameOver();
    }

    void setScore(int score) {
        boolean v = false;
        int s = score, t;

        this.score = score;

        checkLevel(score);

        int d = 1000;
        for (int i = 0; i < 4; i++) {
            t = s / d;
            s = s - t * d;
            d = d / 10;
            if (t > 0) v = true;
            if (i == 3) v = true;
            digits[i].begin(t);
            digits[i].setVisible(v);
        }
    }

    GameViewListener onEgg = new GameViewListener() {
                               @Override
                               public void onViewEvent(GameEvent e) {
                                   String reason = e.getReason();
                                   Egg egg = (Egg) e.getSource();

                                   if (reason == "catch") {
                                       if (egg.getSide() == wolf.getSide()) {
                                           egg.setVisible(false);
                                           setScore(score + 1);
                                           SoundEffect.CATCH.play();
                                       }
                                       else {
                                           SoundEffect.CRASH.play();
                                           if (rubbit.isVisible()) setLive(live - 1);
                                           else setLive(live - 2);
                                       }
                                   }
                                   if (reason == "crash") return;
                               }
                           };

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_SPACE:
            if (over) {
                gameNew();
            }
            else {
                if (isRunning()) stop();
                else start();
            }
            break;
        case KeyEvent.VK_G:
        case KeyEvent.VK_F:
            wolf.begin(Wolf.SIDE_TOP_LEFT);
            break;
        case KeyEvent.VK_K:
        case KeyEvent.VK_J:
            wolf.begin(Wolf.SIDE_TOP_RIGHT);
            break;
        case KeyEvent.VK_C:
        case KeyEvent.VK_V:
            wolf.begin(Wolf.SIDE_BOTTOM_LEFT);
            break;
        case KeyEvent.VK_M:
        case KeyEvent.VK_N:
            wolf.begin(Wolf.SIDE_BOTTOM_RIGHT);
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    void checkEgg() {
        if (eggDelay == 0) {
            for (Egg e : egges) {
                if (e.isVisible()) continue;
                e.begin(java.lang.Math.abs(delayRandom.nextInt()) % 4 * 100);
                eggDelay = java.lang.Math.abs(delayRandom.nextInt()) % 5 + 1;

                switch (e.getSide()) {
                case 0:
                    SoundEffect.MOVE_0.play();
                    break;
                case 1:
                    SoundEffect.MOVE_1.play();
                    break;
                case 2:
                    SoundEffect.MOVE_2.play();
                    break;
                case 3:
                    SoundEffect.MOVE_3.play();
                }
                break;
            }
        }
        else eggDelay--;

        if (rubbitDelay == 0) {
            rubbit.setVisible(!rubbit.isVisible());
            rubbitDelay = 14 + java.lang.Math.abs(delayRandom.nextInt()) % 37;
        }
        else rubbitDelay--;
    }

    @Override
    public void onTimer() {
        updateLive();
        update();
        checkEgg();
    }

    public void onClose() {
        prop.setProperty("score", Integer.toString(score));
    }

}
