package com.redhat.knapsackoptaplanner.domain;

import java.util.List;

import com.redhat.knapsackoptaplanner.solver.KnapsackConstraintConfiguration;

import org.optaplanner.core.api.domain.constraintweight.ConstraintConfigurationProvider;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactProperty;
import org.optaplanner.core.api.domain.valuerange.CountableValueRange;
import org.optaplanner.core.api.domain.valuerange.ValueRangeFactory;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class KnapsackSolution {

    @PlanningEntityCollectionProperty
    private List<Ingot> ingots;

    @ProblemFactProperty
    private Knapsack knapsack;

    @ValueRangeProvider(id = "selected")
    public CountableValueRange<Boolean> getSelected() {
         return ValueRangeFactory.createBooleanValueRange();
    }

    @ConstraintConfigurationProvider
    private KnapsackConstraintConfiguration constraintConfiguration;

    @PlanningScore
    private HardSoftScore score;

    public KnapsackSolution() {
    }

    public List<Ingot> getIngots() {
        return ingots;
    }

    public void setIngots(List<Ingot> ingots) {
        this.ingots = ingots;
    }

    public Knapsack getKnapsack() {
        return knapsack;
    }

    public void setKnapsack(Knapsack knapsack) {
        this.knapsack = knapsack;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

/*
    public KnapsackConstraintConfiguration getConstraintConfiguration() {
        return constraintConfiguration;
    }

    public void setConstraintConfiguration(KnapsackConstraintConfiguration constraintConfiguration) {
        this.constraintConfiguration = constraintConfiguration;
    }
*/
    

}