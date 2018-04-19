package com.pr.pr.pantryraid;

import java.io.Serializable;

/**
 * Created by Benjamin on 3/22/2018.
 */

public class items implements Serializable{
    String itemName;
    int    CartQuantity=0;

    public items(String itemName) {
        this.itemName = itemName;
    }
}
