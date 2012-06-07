package ru.ifmo.ctddev.komarov.taskrunner;

public class SimpleTask implements Task<String, Integer> {
    @Override
    public String run(Integer value) {
        return "" + (value * value);
    }
}
