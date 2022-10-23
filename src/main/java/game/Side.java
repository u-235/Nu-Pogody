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

public interface Side
{
    public final static int        SIDE_TOP_LEFT      = 0;
    public final static int        SIDE_TOP_RIGHT     = 1;
    public final static int        SIDE_BOTTOM_LEFT   = 2;
    public final static int        SIDE_BOTTOM_RIGHT  = 3;
    public int getSide();
}
