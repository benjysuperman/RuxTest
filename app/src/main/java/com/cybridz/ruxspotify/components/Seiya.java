package com.cybridz.ruxspotify.components;

import com.leitianpai.robotsdk.commandlib.Light;

public class Seiya extends AnimCharacter {
    public Seiya() {
        super("Seiya", Light.RED);
        addAction(new int[] {45, 3, 2});
        addAction(new int[] {44, 3, 2});
    }
}
