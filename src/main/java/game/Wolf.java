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

public class Wolf extends GameView implements Side
{
    int side;

    public Wolf(GameViewListener listener, GameIcons icons) {
        super(listener, icons);
        begin(SIDE_TOP_LEFT);
    }

    @Override
    public void begin(int state) {
        switch (state) {
        case SIDE_TOP_LEFT:
            setIcon(Icons.WOLF_TOP_LEFT);
            side = SIDE_TOP_LEFT;
            setPos(121, 174);
            break;
        case SIDE_TOP_RIGHT:
            setIcon(Icons.WOLF_TOP_RIGHT);
            side = SIDE_TOP_RIGHT;
            setPos(181, 174);
            break;
        case SIDE_BOTTOM_RIGHT:
            setIcon(Icons.WOLF_BOTTOM_RIGHT);
            side = SIDE_BOTTOM_RIGHT;
            setPos(181, 196);
            break;
        default:
        case SIDE_BOTTOM_LEFT:
            setIcon(Icons.WOLF_BOTTOM_LEFT);
            side = SIDE_BOTTOM_LEFT;
            setPos(121, 196);
            break;
        }
        setVisible(true);
    }

    @Override
    public void update() {
        setVisible(true);
    }

    @Override
    public int getSide() {
        // TODO Auto-generated method stub
        return side;
    }

}
