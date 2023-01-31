package com.utility;

public class Choice{
    private String description;
    private ChoiceExecutable choiceExecutable;

    public Choice(String s, ChoiceExecutable choiceExecutable){
        this.description = s;
        this.choiceExecutable = choiceExecutable;
    }

    public ChoiceExecutable getChoiceExecutable() {
        return choiceExecutable;
    }

    @Override
    public String toString() {
        return description;
    }
}

