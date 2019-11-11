import java.util.ArrayList;

public class Algorithm {
    private int populationSize;
    private int[][] matrix;

    public Algorithm(int populationSize){
        this.populationSize = populationSize;
        String[][] fromFile = ReadFromFile.read("z5data2.csv");
        matrix = new int[fromFile.length][fromFile[0].length];

        for(int i = 1; i < fromFile.length; i++){
            for(int j = 1; j < fromFile[0].length; j++){
                matrix[i-1][j-1] = Integer.parseInt(fromFile[i][j]);
            }
        }
    }

    public Population initPopulation(char startingNode){
        Population population = new Population(this.populationSize, startingNode, matrix.length-1);
        return population;
    }

    public int calcFitness(Individual individual){
        int path = 0;

        int startingNode = individual.getNodes()[0];
        int lastNode = 0;
        for(int i = 0; i < individual.getNodesLength() - 1; i++){
            int from = individual.getNodes()[i];
            int to = individual.getNodes()[i + 1];
            path += matrix[from][to];
            lastNode = to;
        }
        path += matrix[lastNode][startingNode];
        individual.setFitness(path);

        return path;
    }

    public void evalPopulation(Population population){
        double populationFitness = 0;

        for(Individual individual : population.getPopulation()){
            populationFitness += calcFitness(individual);
        }
        populationFitness /= populationSize;

        population.setAveragePopulationFitness(populationFitness);
    }

    public Population crossoverPopulation(Population population, double percentage){
        int offspring = (int)(populationSize * percentage / 100);
        population.getFittest();

        ArrayList<Individual> wheelOfFortune = new ArrayList<>();
        for(int i = 0; i < offspring; i++){

            double averageFitness =  population.getIndividual(i).getFitness() * 100 / population.getAveragePopulationFitness();

            if(i < 0.2*offspring) averageFitness += 0.8*averageFitness;
            else if(i < 0.5*offspring) averageFitness += 0.2*averageFitness;

            for(int j = 0; j < averageFitness; j++){
                wheelOfFortune.add(population.getIndividual(i));
            }
        }

        Population newPopulation = new Population(populationSize);
        for(int i = 0; i < offspring; i++){
            Individual individual = wheelOfFortune.get((int)((Math.random() * wheelOfFortune.size())));
            newPopulation.setIndividual(i, individual);
        }

        for(int i = offspring - 1; i < populationSize; i++){
            newPopulation.setIndividual(i, new Individual(matrix.length-1, population.getIndividual(0).getNodes()[0]));
        }

        return newPopulation;
    }

    public Population mutatePopulation(Population population){
        Population newPopulation = new Population(populationSize);

        for(int i = 0; i < populationSize; i++){
            newPopulation.setIndividual(i, mutate(population.getIndividual(i)));
        }

        return newPopulation;
    }

    public Individual mutate(Individual individual){
        Individual newIndividual = new Individual(individual);
        int firstNumber = (int)(Math.random()*(individual.getNodesLength()-1)+1);
        int secondNumber = (int)(Math.random()*(individual.getNodesLength()-1)+1);

        int first = individual.getNodes()[firstNumber];
        int second = individual.getNodes()[secondNumber];

        newIndividual.setNodes(firstNumber, second);
        newIndividual.setNodes(secondNumber, first);

        if(calcFitness(newIndividual) < calcFitness(individual)){
            return newIndividual;
        }

        return individual;
    }
}
