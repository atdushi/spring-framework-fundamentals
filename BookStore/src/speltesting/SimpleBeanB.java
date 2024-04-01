package speltesting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SimpleBeanB {
    @Value("#{simpleBeanA.getSimpleValue()}")
    private String secondValue;
    @Value("#{(T(java.lang.Math).random() * 10) + 1}")
    private int randomValue;

    public String getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    public int getRandomValue() {
        return randomValue;
    }

    public void setRandomValue(int randomValue) {
        this.randomValue = randomValue;
    }
}
