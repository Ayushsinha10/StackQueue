package impl;

import interfaces.IDoubleStack;
import interfaces.IStack;

public class DoubleStack implements IDoubleStack {
    private IStack[] arr = new Stack[2];

    DoubleStack(int maxSize) {
        Object[] shareArr = new Object[maxSize];
        IStack first = new Stack(shareArr, true);
        IStack second = new Stack(shareArr, false);
        arr[0] = first;
        arr[1] = second;

    }

    @Override
    public IStack getFirstStack() {
        return arr[0];

    }

    @Override
    public IStack getSecondStack() {
        return arr[1];

    }

}
