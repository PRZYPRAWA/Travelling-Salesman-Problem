import java.util.*;

public class Individual {
    private int[] nodes;
    private int fitness;

    public Individual(int numberOfNodes, int startingNode){
        this.nodes = new int[numberOfNodes];
        this.nodes[0] = startingNode;
        int nextNode;

        Set<Integer> set = new HashSet<>();
        set.add(startingNode);
        for(int i = 1; i < numberOfNodes; i++){
            nextNode = (int)(Math.random()*numberOfNodes);
            while(set.contains(nextNode)){
                nextNode = (int)(Math.random()*numberOfNodes);
            }
            set.add(nextNode);
            this.nodes[i] = nextNode;
        }
    }

    public Individual(Individual individual){
        this.nodes = new int[individual.getNodesLength()];

        for(int i = 0; i < this.nodes.length; i++){
            this.nodes[i] = individual.getNodes()[i];
        }
    }

    public int[] getNodes() {
        return nodes;
    }

    public void setNodes(int[] nodes) {
        this.nodes = nodes;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int getNodesLength(){
        return this.nodes.length;
    }

    public String toString(){
        String str = "";
        for(Integer i : this.nodes){
            str += " " + (char)(i+97);
        }
        return str;
    }

    public void setNodes(int index, int number){
        this.nodes[index] = number;
    }

}
