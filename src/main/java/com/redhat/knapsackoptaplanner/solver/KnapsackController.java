package com.redhat.knapsackoptaplanner.solver;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import com.redhat.knapsackoptaplanner.domain.KnapsackSolution;

import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/knapsack")
public class KnapsackController {

    @Autowired
    private SolverManager<KnapsackSolution, UUID> solverManager;

    @PostMapping("/solve")
    public KnapsackSolution solve(@RequestBody KnapsackSolution problem) {
        UUID problemId = UUID.randomUUID();
        // Submit the problem to start solving
        SolverJob<KnapsackSolution, UUID> solverJob = solverManager.solve(problemId, problem);
        KnapsackSolution solution;
        try {
            // Wait until the solving ends
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Solving failed.", e);
        }
        return solution;
    }
}