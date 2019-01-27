package com.georgia.firstaid.model;

public class Page {
    private int textId;
    private Choice choice1;
    private Choice choice2;
    private boolean singleButton = false;


    public Page(int textId) {
        this.textId = textId;
        singleButton = true;
    }

    public Page(int textId, Choice choice2) {
        this.textId = textId;
        this.choice2 = choice2;
        singleButton = true;
    }

    public Page(int textId, Choice choice1, Choice choice2) {
        this.textId = textId;
        this.choice1 = choice1;
        this.choice2 = choice2;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public Choice getChoice1() {
        return choice1;
    }

    public void setChoice1(Choice choice1) {
        this.choice1 = choice1;
    }

    public Choice getChoice2() {
        return choice2;
    }

    public void setChoice2(Choice choice2) {
        this.choice2 = choice2;
    }

    public boolean isSingleButton() {
        return singleButton;
    }

    public void setSingleButton(boolean singleButton) {
        this.singleButton = singleButton;
    }

}
