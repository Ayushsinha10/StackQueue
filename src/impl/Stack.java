package impl;

import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IStack;

public class Stack implements IStack {
    private int size;
    private Object[] array;
    private int top = -1;
    private int top2;
    private boolean flag;
    // true = first. false = second.

    Stack(Object[] arr, boolean flag) {
        this.size = arr.length;
        this.flag = flag;
        this.top2 = size;
        this.array = arr;

    }

    @Override
    public void push(Object element) throws StackOverflowException {
        if (element == null) {
            throw new NullPointerException();
        }
        if (flag) {
            if (!(top < Math.floor(size / 2) - 1))
                throw new StackOverflowException();
        }
        if (!flag) {
            if (!(top2 > Math.floor(size / 2))) {
                throw new StackOverflowException();
            }
        }
        if (flag) {
            this.array[++top] = element;
        }
        if (!flag) {
            this.array[--top2] = element;
        }

    }

    @Override
    public Object pop() throws StackEmptyException {
        if (flag) {
            if (top >= 0) {
                int x = top;
                Object y = array[x];
                array[x] = null;
                top--;
                return y;
            } else {
                throw new StackEmptyException();
            }
        }
        if (!flag) {
            if (top2 < size) {
                int x = top2;
                Object y = array[x];
                array[x] = null;
                top2++;
                return y;
            } else {
                throw new StackEmptyException();
            }
        }
        return null;

    }

    @Override
    public Object top() throws StackEmptyException {
        if (flag) {
            if (top >= 0) {
                int x = top;
                Object y = array[x];
                return y;
            } else {
                throw new StackEmptyException();
            }
        }
        if (!flag) {
            if (top2 < size) {
                int x = top2;
                Object y = array[x];

                return y;
            } else {
                throw new StackEmptyException();
            }
        }
        return null;
    }

    @Override
    public int size() {
        if (flag) {
            return (int) top + 1;

        }
        if (!flag) {
            return (int) size - top2;
        }
        return 0;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        if (flag) {
            for (int i = top; i > -1; i--) {
                array[i] = null;
            }
            top = -1;
        }
        if (!flag) {
            for (int i = top2; i < array.length; i++) {
                array[i] = null;
            }
            top2 = size;
        }
    }

}
