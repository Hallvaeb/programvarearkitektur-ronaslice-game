package com.mygdx.game;

import java.util.List;

public interface FireBaseInterface {
    public void SomeFunction();


    public List<String> GetTopNames();
    public List<Float> GetTopScores();

    public void SetOnValueChangedListener();

    public void SetValueInDB(String target, float value);

}
