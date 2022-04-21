package com.mygdx.game;

import java.util.List;

/** Empty class so we can access the imported libraries from Firebase in Android */
public class CoreInterfaceClass implements FireBaseInterface{

    @Override
    public void SetTop10Lists() { }

    @Override
    public List<String> GetTopNames() {
        return null;
    }

    @Override
    public List<Float> GetTopScores() {
        return null;
    }

    @Override
    public void SetValueInDB(String target, float value) { }
}
