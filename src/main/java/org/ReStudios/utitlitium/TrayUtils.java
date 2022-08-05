package org.ReStudios.utitlitium;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
@SuppressWarnings("unused")
public class TrayUtils {
    public static boolean isSupported(){
        return SystemTray.isSupported();
    }
    public TrayIcon trayIcon;

    public TrayUtils(String title){
        if (!isSupported()) return;
        SystemTray tray = SystemTray.getSystemTray();

        trayIcon = new TrayIcon(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB));

        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(title);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
    public void notification(String title, String message, TrayIcon.MessageType msgType){
        trayIcon.displayMessage(title, message, msgType);
    }
    public void setIcon(Image image){
        trayIcon.setImageAutoSize(true);
        trayIcon.setImage(image);
    }
    public void setTooltipText(String ttt){
        trayIcon.setToolTip(ttt);
    }
    public void setPopupMenu(PopupMenu menu){
        trayIcon.setPopupMenu(menu);
    }
    public void addDoubleClickListener(ActionListener lis){
        trayIcon.addActionListener(lis);
    }

}