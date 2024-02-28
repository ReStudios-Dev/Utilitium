package org.ReStudios.utitlitium;

import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings("unused")
public class Timer {
    long startTime;
    ArrayList<StepData> steps;

    public Timer() {
        startTime = 0;
        steps = new ArrayList<>();
    }
    public void run(){
        startTime = System.currentTimeMillis();
    }
    public StepData step(){
        StepData before = ArrayUtils.getLastItem(steps);
        StepData newData = new StepData(startTime,
                before == null ? startTime : before.finishTime,
                System.currentTimeMillis());
        steps.add(newData);
        return newData;
    }
    public long total(){
        return System.currentTimeMillis() - startTime;
    }
    public static class StepData {
        long startTime;
        long stepBefore;
        long finishTime;

        public StepData(long startTime, long stepBefore, long finishTime) {
            this.startTime = startTime;
            this.stepBefore = stepBefore;
            this.finishTime = finishTime;
        }

        public long relative(){
            return finishTime - stepBefore;
        }
        public long absolute(){
            return finishTime - startTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StepData stepData = (StepData) o;
            return startTime == stepData.startTime && stepBefore == stepData.stepBefore && finishTime == stepData.finishTime;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startTime, stepBefore, finishTime);
        }

        @Override
        public String toString() {
            return "StepData{relative="+relative()+"ms,absolute=" + absolute() +"ms}";
        }
    }
}
