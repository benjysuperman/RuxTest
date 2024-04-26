package com.cybridz.ruxspotify.components;

import com.leitianpai.robotsdk.commandlib.Light;

public class Shiryu extends AnimCharacter {
    public Shiryu() {
        super("Shiryu", Light.GREEN);
        addAction(new int[] {45, 3, 1});
        addAction(new int[] {45, 3, 1});
        addAction(new int[] {44, 3, 1});
    }
}
