package com.jwen.expandrecycler.swipemenu;

/**
 * author: Jwen
 * date:2016-09-29.
 */
public class Menu {

    private String title;
    private int iconId = -1;
    private int drawableId = -1;

    public Menu(String title, int iconId, int drawableId) {
        this.title = title;
        this.iconId = iconId;
        this.drawableId = drawableId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }
}
