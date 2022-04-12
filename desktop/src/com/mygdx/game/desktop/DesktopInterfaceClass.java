package com.mygdx.game.desktop;

import com.mygdx.game.FireBaseInterface;
import java.util.List;

public class DesktopInterfaceClass implements FireBaseInterface {
    @Override
    public void SomeFunction() {

    }

    @Override
    public List<String> GetTopNames() {
        return null;
    }

    @Override
    public List<Float> GetTopScores() {
        return null;
    }

    @Override
    public void SetOnValueChangedListener() {

    }

    @Override
    public void SetValueInDB(String target, float value) {

    }
}
