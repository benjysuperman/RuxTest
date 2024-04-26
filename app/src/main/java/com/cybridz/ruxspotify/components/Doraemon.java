package com.cybridz.ruxspotify.components;

import com.leitianpai.robotsdk.commandlib.Light;

public class Doraemon extends AnimCharacter {
    public Doraemon() {
        super("Doraemon", Light.CYAN);
        addAction(new int[] {30, 3, 1});
        addAction(new int[] {31, 3, 1});
        addAction(new int[] {32, 3, 1});
        addAction(new int[] {33, 3, 1});
    }
}
