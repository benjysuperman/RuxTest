package com.cybridz.ruxspotify.components;

import com.leitianpai.robotsdk.commandlib.Light;

public class Andromeda extends AnimCharacter {
    public Andromeda() {
        super("Andromeda", Light.PURPLE);
        addAction(new int[] {28, 3, 1});
        addAction(new int[] {22, 3, 1});
        addAction(new int[] {21, 3, 1});
    }
}
