package com.mygdx.game;

import java.util.List;

/**
 * Interface with Firebase methods.
 * Since the AndroidInterfaceClass implements this Interface located in Core,
 * we access the imported libraries from Firebase in Android
 */
public interface FireBaseInterface {
    public void SetTop10Lists();

    public List<String> GetTopNames();
    public List<Float> GetTopScores();

    public void SetValueInDB(String target, float value);

}
