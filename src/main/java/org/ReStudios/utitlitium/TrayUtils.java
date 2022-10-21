package org.ReStudios.utitlitium;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
@SuppressWarnings("unused")
public class TrayUtils {
    /**
     * Check if system tray supported
     * @return true if tray supported
     */
    public static boolean isSupported(){
        return SystemTray.isSupported();
    }
    public TrayIcon trayIcon;

    /**
     * Constructor
     * @param title Title of application in tray
     */
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

    /**
     * Constructor with application image
     * @param title Title of application
     * @param icon Icon of application
     */
    public TrayUtils(String title, Image icon){
        this(title);
        setIcon(icon);
    }

    /**
     * Creates system tray notification
     * @param title Notification's title
     * @param message Notification's body
     * @param msgType Notification type
     */
    public void notification(String title, String message, TrayIcon.MessageType msgType){
        trayIcon.displayMessage(title, message, msgType);
    }

    /**
     * Change icon at runtime
     * @param image new icon
     */
    public void setIcon(Image image){
        trayIcon.setImageAutoSize(true);
        trayIcon.setImage(image);
    }

    /**
     * Change title at runtime
     * @param ttt New title
     */
    public void setTooltipText(String ttt){
        trayIcon.setToolTip(ttt);
    }

    /**
     * Custom right click menu
     * @param menu Menu
     */
    public void setPopupMenu(PopupMenu menu){
        trayIcon.setPopupMenu(menu);
    }

    /**
     * Double click listener ¯\_(ツ)_/¯
     * @param lis Action listener
     */
    public void addDoubleClickListener(ActionListener lis){
        trayIcon.addActionListener(lis);
    }

}