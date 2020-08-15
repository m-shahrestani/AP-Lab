import ir.huri.jcal.JalaliCalendar;
import java.util.*;

/**
 * A class to make a Voting.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Voting {
    // A type of voting.
    private int type;
    // A question of voting.
    private String question;
    // An ArrayList for storing the  voters.
    private ArrayList<Person> voters;
    // A HashMap for storing list of votes to choices.
    private HashMap<String, HashSet<Vote>> listOfVotesToChoices;

    /**
     * Create a new voting with a given type and question.
     * Create a voters collection and listOfVotesToChoices and choices collection.
     *
     * @param type type of voting.
     * @param question question of voting.
     */
    public Voting(int type, String question) {
        this.type = type;
        this.question = question;
        voters = new ArrayList<>();
        listOfVotesToChoices = new HashMap<>();
    }

    /**
     * get The question.
     * @return question field.
     */
    public String getQuestion() {
        return question;
    }

    public void createChoice(String choice) {
        listOfVotesToChoices.put(choice, new HashSet<>());
    }

    /**
     * get The type of voting.
     * @return type field.
     */
    public int getType() {
        return type;
    }

    /**
     * Operate a voting.
     *
     * @param person person of vote.
     * @param choices choices of vote.
     */
    public void vote(Person person, ArrayList<String> choices) {
        JalaliCalendar jalaliDate = new JalaliCalendar();
        Vote vote = new Vote(person, jalaliDate.toString());
        if (!voters.contains(person)) {
            if (type == 0) {
                voters.add(person);
                listOfVotesToChoices.get(choices.get(0)).add(vote);
                System.out.println("vote submitted in " + vote.getDate() + ".");
            } else if (type == 1) {
                voters.add(person);
                for (String temp : choices) {
                    if (listOfVotesToChoices.containsKey(temp)) {
                        listOfVotesToChoices.get(temp).add(vote);
                    }
                    System.out.println("votes submitted in " + vote.getDate() + ".");
                }
            } else {
                System.out.println("Type isn't valid.");
            }
        }
        else {
            System.out.println("This person voted later.");
        }
    }

    /**
     * Print voters list.
     */
    public void getVoters() {
        for(Person temp : voters) {
            System.out.println(temp.toString());
        }
    }

    /**
     * Print vote result.
     */
    public void printVotes() {
        int count = 1;
        for ( String temp : listOfVotesToChoices.keySet()) {
            System.out.println(count + "-" + temp + ": " + listOfVotesToChoices.get(temp).size() + " votes");
            for(Vote temp2 : listOfVotesToChoices.get(temp)) {
                System.out.print(" " + temp2.getPerson().toString() + ",");
            }
            System.out.println();
            count++;
        }
    }

    /**
     * get The voting choice collection
     * @return choices
     */
    public ArrayList<String> getChoices() {
        return (new ArrayList<>(listOfVotesToChoices.keySet()));
    }

    /**
     * random vote
     * @param person person
     */
    public void RandomChoice(Person person)
    {
        Random random = new Random();
        String randChoice = getChoices().get(random.nextInt(getChoices().size()));
        ArrayList<String>Choice = new ArrayList<>();
        Choice.add(randChoice);
        vote(person,Choice);
    }
}
