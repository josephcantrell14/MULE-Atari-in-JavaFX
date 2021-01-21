package controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ChangeListener;

import java.util.List;
import java.util.ArrayList;

public class SimpleObservableInteger implements ObservableIntegerValue {

    private int value;
    private List<ChangeListener> listeners;

    public SimpleObservableInteger() {
        listeners = new ArrayList<ChangeListener>();
    }

    public int intValue() {
        return value;
    }

    public long longValue() {
        return (long) value;
    }

    public float floatValue() {
        return (float) value;
    }

    public double doubleValue() {
        return (double) value;
    }

    @Override
    public void addListener(ChangeListener<? super Number> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ChangeListener<? super Number> listener) {
        listeners.remove(listener);
    }

    @Override
    public Number getValue() {
        return value;
    }

    @Override
    public int get() {
        return value;
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }

    public void setValue(int newValue) {
        int oldValue = value;
        value = newValue;
        for (ChangeListener l: listeners) {
            l.changed(this, oldValue, value);
        }
    }
}

