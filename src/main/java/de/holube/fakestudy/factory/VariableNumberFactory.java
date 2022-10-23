package de.holube.fakestudy.factory;

import de.holube.fakestudy.study.util.VariableNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VariableNumberFactory {

    private double base;
    private double diff;

    public VariableNumber create() {
        return new VariableNumber(base, diff);
    }

}
