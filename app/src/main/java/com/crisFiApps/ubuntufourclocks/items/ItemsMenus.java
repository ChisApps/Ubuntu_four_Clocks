package com.crisFiApps.ubuntufourclocks.items;

/**
 * Created by Jorgefc82 on 23/12/2015.
 */
public class ItemsMenus {
        private String name;
        private int iconId;

    //Constructor que establece icono y texto
    public ItemsMenus(String name, int iconId) {
        this.name = name;
        this.iconId = iconId;
    }


    //Métoddos
    public String getName() {
        return name;
    }


    public int getIconId() {
        return iconId;
    }
}
