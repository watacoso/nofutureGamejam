package com.github.nofutureball.entity.dialog;

import java.util.ArrayList;

import com.github.nofutureball.entity.Sprite;
import com.github.nofutureball.states.GameLevel;

/**
 * Manages all the Dialogs
 * provides simple "showMessage" function to make a new dialog show up
 * 
 * @author hollowspecter
 *
 */

public class DialogManager {
    public enum Type {
        PLAIN_MESSAGE, PLAIN_PIC_MSG;
    };
    
    private static ArrayList<Dialog> dialogs = new ArrayList<Dialog>();
    
    public static void showMessage(String title, String msg, Type type)
    {
        switch(type)
        {
            case PLAIN_MESSAGE:
                addDialog(new Dialog(title, msg));
                break;
            default:
                break;
        }
    }
    
    public static void showMessage(String title, String msg, Type type, Sprite sprite)
    {
        switch(type)
        {
            case PLAIN_PIC_MSG:
                addDialog(new PicDialog(title,msg,sprite));
                break;
            case PLAIN_MESSAGE:
                showMessage(title, msg, type);
            default:
                break;
        }
    }
    
    /**
     * Use this function to make a new dialog
     * @param d
     */
    public static void addDialog(Dialog d) {
        GameLevel.ui.add(d);
        dialogs.add(d);
    }
    
    /**
     * Use this function to remove a dialog/make it disappear
     * @param d
     */
    public static void removeDialog(Dialog d) {
        GameLevel.ui.remove(d);
        dialogs.remove(d);
    }
}
