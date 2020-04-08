# Knapsack Problem

The knapsack problem is a problem in combinatoral optimization: given a knapsack with a given weight, and a set of items with a certain weight and value, determine the combination of items to include in the knapsack in such a way that maximizes the value.

[<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/fd/Knapsack.svg/500px-Knapsack.svg.png">](Knapsack problem)

(image source: https://commons.wikimedia.org/wiki/File:Knapsack.svg , license: https://creativecommons.org/licenses/by-sa/2.5/deed.en)



## Solving Knapsack with OptaPlanner

[OptaPlanner](https://www.optaplanner.org) is an A.I. Constraint Satisfaction Solver that provides a highly scalable platform to find optimal solutions to NP-Complete and NP-Hard problems. OptaPlanner allows you to write these solutions in plain Java, making this technology available to a large group of software developers.

This repository contains a working solution for the knapsack problem in OptaPlanner. We'll quickly explain the different components:

- `Ingot`: This class is our `@PlanningEntity`, it represents the class that OptaPlanner needs to plan. Our `Ingot` has a `weight` and a `value`. Finally, it has a `Boolean` attribute which defines whether this `Ingot` instance has been selected in the knapsack. This `Boolean` is our `@PlanningVariable`.
- `Knapsack`: Defines the maximum weight of the knapsack in our problem.
- `KnapsackSolution`: Our @PlanningSolution. It's used to represent both the problem (i.e. the _uninitialized solution_,), the _working solution_, and the _best solution_, which is returned by OptaPlanner when solving is ended.
- `KnapsackConstraintConfiguration`: Configuration class for our constriants that allows us to set the constraint weight. This is useful when a solution has multiple constraints that need to be weighed against each other.
- `KnapsackController`: The Spring Boot REST controller exposing our RESTful endpoint.
- `constraints.drl`: A [Drools](https://www.drools.org) rules representation of our constraints.
- `solverConfig.xml`: Configuration file for the OptaPlanner `Solver`. In this example, we configure the `DRL` file that contains our constraints, and we configure a number of `MoveSelectors` for our _local search_ phase. The latter configuration is simply _power-tweaking_ of our solution by add a `SubPillarSwapMoveSelector`.
- `application.properties`: Spring Boot configuration file in which, in this case, we set the maximum time the OptaPlanner `Solver` will run to 10 minutes.

## Running the application
The project is a simple Maven project, you can build it executing the following command in the project's root directory: `mvn clean install`.

This will compile and package the project and run a number of simple tests. It will create a runnable JAR file in the `target` directory with the name: `knapsack-optaplanner-springboot-0.0.1-SNAPSHOT.jar`

You can now start the application with the command: `java -jar target/knapsack-optaplanner-springboot-0.0.1-SNAPSHOT.jar`

## Sending a request.

The application exposes a REST API at `http://localhost:8080/knapsack/solve`

The POST request you send to this endpoint needs to contain a `knapsack` definition, and a collection of `ingots`. An example JSON request could look like this:

```
{
	"knapsack": {
		"maxWeight": 10
	},
	"ingots" : [
		{
			"weight": 4,
			"value": 15
		},
		{
			"weight": 4,
			"value": 15
		},
		{
			"weight": 3,
			"value": 12
		},
		{
			"weight": 3,
			"value": 12
		},
		{
			"weight": 3,
			"value": 12
		},
		{
			"weight": 2,
			"value": 7
		},
		{
			"weight": 2,
			"value": 7
		},
		{
			"weight": 2,
			"value": 7
		},
		{
			"weight": 2,
			"value": 7
		},
		{
			"weight": 2,
			"value": 7
		}
	]
}
```

You can send such a RESTful request to the application using your REST client of choice, for example Postman or cURL.

An example cURL command would look like this:

`curl --location --request POST 'http://localhost:8080/knapsack/solve' --header 'Accept: application/json' --header 'Content-Type: application/json' --header 'Content-Type: text/plain' --header 'Cookie: JSESSIONID=0C6C24091A814A4A0431ED5E32CE6B45' --data-raw '{"knapsack": { " maxWeight": 10	}, "ingots" : [ { "weight": 4, "value": 15 }, { "weight": 4, "value": 15 }, { "weight": 3, "value": 12}, { "weight": 3, "value": 12 }, { "weight": 3, "value": 12 }, { "weight": 2, "value": 7 }, { "weight": 2, "value": 7 }, { "weight": 2, "value": 7 }, { "weight": 2, "value": 7 }, { "weight": 2, "value": 7} ]}'`

The response will return which _ingots_ have been selected in the knapsack, and the total score of the solution, in this case:

```
{
    "ingots": [
        {
            "weight": 4,
            "value": 15,
            "selected": true
        },
        {
            "weight": 4,
            "value": 15,
            "selected": true
        },
        {
            "weight": 3,
            "value": 12,
            "selected": false
        },
        {
            "weight": 3,
            "value": 12,
            "selected": false
        },
        {
            "weight": 3,
            "value": 12,
            "selected": false
        },
        {
            "weight": 2,
            "value": 7,
            "selected": true
        },
        {
            "weight": 2,
            "value": 7,
            "selected": false
        },
        {
            "weight": 2,
            "value": 7,
            "selected": false
        },
        {
            "weight": 2,
            "value": 7,
            "selected": false
        },
        {
            "weight": 2,
            "value": 7,
            "selected": false
        }
    ],
    "knapsack": {
        "maxWeight": 10
    },
    "constraintConfiguration": {
        "weight": "1hard/0soft",
        "maxValue": "0hard/1soft"
    },
    "score": "0hard/37soft",
    "selected": {
        "size": 2,
        "empty": false
    }
}
```

## Conclusion
OptaPlanner is an extremely powerful tool to find the best solution to NP-Complete and NP-Hard problems in a given amount of time. The knapsack problem is a fairly simple problem when it comes to implementation in OptaPlanner. The OptaPlanner distribution which can be downloaded from the [OptaPlanner website](https://www.optaplanner.org) contains more examples, including sophisticated examples like _Shift Assignment_, _Vehicle Routing_ and _Course Scheduling_.