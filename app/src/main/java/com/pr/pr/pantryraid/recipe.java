package com.pr.pr.pantryraid;

import android.graphics.Bitmap;

/**
 * Created by Kan on 2/22/18.
 */

public class recipe
{
    int id;
    String name;
    String url;

    public recipe(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public recipe(int id, String name, String url)
    {
        this.id = id;
        this.name = name;
        this.url = url;
    }

}
