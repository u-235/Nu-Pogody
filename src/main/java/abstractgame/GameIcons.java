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

package abstractgame;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public abstract class GameIcons
{    
    private Icon[] icons;
    Class<?>       imgClass=this.getClass();
    
    public GameIcons() {
        icons=loadIcons();
    }

    public ImageIcon getIcon(String name) {
        URL url;
        if (name == null) return null;
        url = imgClass.getClassLoader().getResource(name);
        if (url == null) return null;
        return new ImageIcon(url);
    }

    public abstract Icon[] loadIcons();
    
    public Icon getIcon(int index){
        return icons[index];
    }
}
