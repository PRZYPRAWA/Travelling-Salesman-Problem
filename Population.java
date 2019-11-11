import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Population {
    private Individual[] population;
    private double averagePopulationFitness = -1;
    private int startingNodeInt;

    public Population(int populationSize, char startingNode, int numberOfNodes){
        this.startingNodeInt = startingNode - 97;
        population = new Individual[populationSize];

        for(int i = 0; i < populationSize; i++){
            Individual individual = new Individual(numberOfNodes ,this.startingNodeInt);
            population[i] = individual;
        }
    }

    public Population(int populationSize){
        this.population = new Individual[populationSize];
    }

    public Individual getFittest(){
        Arrays.sort(this.population, new Comparator<Individual>() {
                @Override
                public int compare(Individual o1, Individual o2) {
                    if (o1.getFitness() < o2.getFitness()) {
                        return -1;
                    } else if (o1.getFitness() > o2.getFitness()) {
                        return 1;
                    }
                    return 0;
                }
        });
        return this.population[0];
    }

    public Individual[] getPopulation(){
        return this.population;
    }

    public void setAveragePopulationFitness(double fitness){
        this.averagePopulationFitness = fitness;
    }

    public double getAveragePopulationFitness(){
        return this.averagePopulationFitness;
    }

    public int size(){
        return this.population.length;
    }

    public void setIndividual(int index, Individual individual){
        this.population[index] = individual;
    }

    public Individual getIndividual(int index){
        return this.population[index];
    }
}
