package com.selfdot;

import java.util.ArrayList;
import java.util.List;

public class NamedComponent {

    private final String name;
    private final Component component;
    private final List<String> inputNames = new ArrayList<>();

    public NamedComponent(String name, Component component) {
        this.name = name;
        this.component = component;
    }

    public void addInputName(String inputName) {
        inputNames.add(inputName);
    }

    public String getName() {
        return name;
    }

    public List<String> getInputNames() {
        return inputNames;
    }

    public Component getComponent() {
        return component;
    }
}
