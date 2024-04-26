package com.cybridz.ruxspotify.components;

import java.util.ArrayList;
import java.util.List;

public abstract class AnimCharacter {
    private String name;

    private int light;

    private String description;

    private List<int[]> actions = new ArrayList() {{
        add(new int[] {79, 1, 1});
    }};

    protected void emptyActions(){
        actions.clear();
    }

    public AnimCharacter(String name, int light) {
        this.name = name;
        this.light = light;
        this.description = "";
    }

    @Override
    public String toString() {
        return "AnimCharacter{" +
                "name='" + name + '\'' +
                ", light=" + light +
                ", description='" + description + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getLight() {
        return light;
    }

    public AnimCharacter setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AnimCharacter addAction(int[] action) {
        this.actions.add(action);
        return this;
    }

    public List<int[]> getActions() {
        return actions;
    }
}
