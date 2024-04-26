package com.cybridz.ruxspotify.components;

import com.leitianpai.robotsdk.commandlib.Light;

public class Dorami extends AnimCharacter {
    public Dorami() {
        super("Dorami", Light.ORANGE);
        this.emptyActions();
        addAction(new int[] {80, 3, 2});
        addAction(new int[] {80, 3, 2});
        addAction(new int[] {80, 3, 2});
        addAction(new int[] {80, 3, 2});
    }
}
