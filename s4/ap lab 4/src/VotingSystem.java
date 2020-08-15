import java.util.ArrayList;

/**
 * A class to make a Voting System.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class VotingSystem {
    // An ArrayList for storing the voting.
    private ArrayList<Voting> votingList;

    /**
     * Create a new voting system.
     *
     */
    public VotingSystem() {
        votingList = new ArrayList<>();
    }

    /**
     * create a new Voting
     * @param question question of voting
     * @param type type of voting
     * @param choices choices of voting
     */
    public void createVoting(String question, int type, ArrayList<String> choices ) {
        Voting voting = new Voting(type, question);
        for (String temp: choices) {
            voting.createChoice(temp);
        }
        votingList.add(voting);
        System.out.println("voting created.");
    }

    /**
     * delete a Voting.
     * @param index index of voting for delete.
     */
    public void deleteVoting (int index) {
        if(validIndex(index)) {
            votingList.remove(index);
            System.out.println("This voting has been removed.");
        }
    }

    public void vote(int index, Person person, ArrayList<String> votes) {
        if(validIndex(index)) {
            votingList.get(index).vote(person, votes);
        }
    }

    /**
     * print Voting questions.
     */
    public void printVotingQuestions() {
        for (Voting temp: votingList) {
            System.out.println(temp.getQuestion());
        }
    }

    /**
     * print Voting.
     * @param index index of voting.
     */
    public void printVoting(int index) {
        if (validIndex(index))
        {
            System.out.println("Question: "+ votingList.get(index).getQuestion());
            ArrayList<String> temp;
            temp = votingList.get(index).getChoices();
            for (String move: temp)
            {
                System.out.println("\t"+move);
            }
        }
    }

    /**
     * Print result a voting.
     * @param index index of voting.
     */
    public void printResult(int index) {
        if(validIndex(index)) {
            System.out.println("print result:");
            System.out.println('\t' + "voters:");
            votingList.get(index).getVoters();
            System.out.println('\t' + "result:");
            votingList.get(index).printVotes();
        }
    }

    /**
     * Vote Random
     * @param index index of voting.
     * @param person voter.
     */
    public void voteRandom(int index,Person person)
    {
        if (validIndex(index) && votingList.get(index).getType() == 0)
        {
            votingList.get(index).RandomChoice(person);
        }
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean validIndex(int index)
    {
        if(index >= 0 && index < votingList.size()) {
            return true;
        }
        else {
            System.out.println("Index isn't valid.");
            return false;
        }
    }

}
