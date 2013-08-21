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
