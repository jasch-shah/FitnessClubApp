package com.example.android.fitnessclubapp;

/**
 * Created by jashshah on 20/03/18.
 */

public class Exercise {

    String name;
    String type;
    String[] benefits;
    String[] steps;
    String YoutubeId;
    int drawableId;

    public Exercise(String name, String type, String []benefits, String []steps, String youtubeId, int drawableId) {
        this.name = name;
        this.type = type;
        this.benefits = benefits;
        this.steps = steps;
        YoutubeId = youtubeId;
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String[] getBenefits() {
        return benefits;
    }

    public String[] getSteps() {
        return steps;
    }

    public String getYoutubeId() {
        return YoutubeId;
    }

    public int getDrawableId() {
        return drawableId;
    }
}
