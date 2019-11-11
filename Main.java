import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbe iteracji: ");
        int interval = scanner.nextInt();
        System.out.println("Z jakiego miasta zaczac: ");
        String startingCity = scanner.next();
        char city = startingCity.charAt(0);

        System.out.println("Podaj liczebnosc populacji: ");
        int popSize = scanner.nextInt();
        Algorithm algorithm = new Algorithm(popSize);
        Population population = algorithm.initPopulation(city);
        int generation = 1;



        algorithm.evalPopulation(population);
        int k = 1;
        while(k == 1) {
            for (int i = 0; i < interval; i++) {
                 if(i%10 == 0){
                     System.out.println("Best solution: " + population.getFittest().toString() + " " + population.getFittest().getFitness());
                     System.out.println("Generation " + generation);
                 }

                population = algorithm.crossoverPopulation(population, 60);
                population = algorithm.mutatePopulation(population);

                algorithm.evalPopulation(population);
                generation++;
            }
            System.out.println("Czy powtorzyc iteracje? (Tak = 1)");
            k = scanner.nextInt();
        }

        System.out.println("Best solution in " + generation + " generations");
        System.out.println("Best solution: " + population.getFittest().toString() + " " + population.getFittest().getFitness());
    }
}
