package com.pr.pr.pantryraid;

import java.util.ArrayList;
import java.util.List;

public class ingredient
{
    String name;
    String description;
    int photoID;

    ingredient(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    ingredient(String name, String description, int photoID)
    {
        this.name = name;
        this.description = description;
        this.photoID = photoID;
    }
}