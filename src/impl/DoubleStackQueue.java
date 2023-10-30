package impl;

import common.QueueEmptyException;
import common.QueueFullException;
import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IDoubleStack;
import interfaces.IQueue;
import interfaces.IStack;

public class DoubleStackQueue implements IQueue {

    private IStack input;
    private IStack output;
    private int maxSize;

    DoubleStackQueue(int maxSize) {
        IDoubleStack init = new DoubleStack(maxSize);
        input = init.getFirstStack();
        output = init.getSecondStack();
        this.maxSize = maxSize;

    }

    @Override
    public void enqueue(Object element) throws QueueFullException {
        if (input.size() == (int) Math.floor(maxSize / 2)) {
            throw new QueueFullException();
        }
        try {
            input.push(element);
        } catch (StackOverflowException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object dequeue() throws QueueEmptyException {

        if (input.isEmpty()) {
            throw new QueueEmptyException();
        }
        try {
            if (output.isEmpty()) {
                int size = input.size();
                for (int i = 0; i < size; i++) {
                    output.push(input.pop());

                }
            }

            Object x = output.pop();
            return x;
        } catch (StackEmptyException e) {
            e.printStackTrace();
        } catch (StackOverflowException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int size() {
        return input.size() + output.size();
    }

    @Override
    public boolean isEmpty() {
        if (input.isEmpty() && output.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        input.clear();
        output.clear();
    }
}
