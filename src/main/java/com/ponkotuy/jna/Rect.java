package com.ponkotuy.jna;

import com.sun.jna.Structure;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
* Created by yosuke on 2014/10/04.
*/
public class Rect extends Structure {

    public int left, top, right, bottom;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("left", "top", "right", "bottom");
    }

    public Rectangle toAwt() {
        return new Rectangle(left, top, right, bottom);
    }

    public java.awt.Point center() {
        return new java.awt.Point(left + right/2, top + bottom/2);
    }
}
