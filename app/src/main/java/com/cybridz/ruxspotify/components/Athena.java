package com.cybridz.ruxspotify.components;

import com.leitianpai.robotsdk.commandlib.Light;

public class Athena extends AnimCharacter {

    public Athena() {
        super("Athena", Light.PURPLE);
        this.emptyActions();
        addAction(new int[] {55, 3, 1});
        addAction(new int[] {56, 3, 1});
        addAction(new int[] {55, 3, 1});
        addAction(new int[] {56, 3, 1});
        addAction(new int[] {55, 3, 1});
        addAction(new int[] {56, 3, 1});
    }

}
