package com.redhat.knapsackoptaplanner.solver;

import org.optaplanner.core.api.domain.constraintweight.ConstraintConfiguration;
import org.optaplanner.core.api.domain.constraintweight.ConstraintWeight;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@ConstraintConfiguration(constraintPackage = "com.redhat.knapsackoptaplanner.solver")
public class KnapsackConstraintConfiguration {

    @ConstraintWeight("Max Weight")
    public HardSoftScore weight= HardSoftScore.ofHard(1);

    @ConstraintWeight("Max Value")
    private HardSoftScore maxValue = HardSoftScore.ofSoft(1);

    public HardSoftScore getWeight() {
        return weight;
    }

    public void setWeight(HardSoftScore weight) {
        this.weight = weight;
    }

    public HardSoftScore getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(HardSoftScore maxValue) {
        this.maxValue = maxValue;
    }
 
}