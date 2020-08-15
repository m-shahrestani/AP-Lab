import java.util.ArrayList;

/**
 * A class to drive program.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {

        //create a voting system
        VotingSystem votingSystem = new VotingSystem();

        //create voting choices
        ArrayList<String> options = new ArrayList<>();
        ArrayList<String> days = new ArrayList<>();
        options.add("yes");
        options.add("no");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");

        //single vote mode (index 0)
        votingSystem.createVoting("Do you come?", 0, options);

        //multiple vote mode (index 1)
        votingSystem.createVoting("Which day?", 1, days);

        //print voting questions.
        System.out.println("print voting questions.");
        votingSystem.printVotingQuestions();
        System.out.println();

        //print voting
        System.out.println("print voting.");
        votingSystem.printVoting(0);
        System.out.println();
        votingSystem.printVoting(1);
        System.out.println();

        //print result
        System.out.println("print result.");
        votingSystem.printResult(0);
        System.out.println();
        votingSystem.printResult(1);
        System.out.println();

        //delete voting.
        System.out.println("delete voting.");
        votingSystem.deleteVoting(1);
        votingSystem.printVotingQuestions();
        votingSystem.createVoting("Which day?", 1, days);
        votingSystem.printVotingQuestions();
        System.out.println();

        //create person
        Person p1 = new Person("Ali", "Razeghi");
        Person p2 = new Person("Mohammad", "Honarjoo");
        Person p3 = new Person("Gholi", "Rezayi");
        Person p4 = new Person("Hamed", "Ahmadi");
        Person p5 = new Person("Reza", "Ahmadi");
        Person p6 = new Person("Mahdi", "Mohammadi");
        Person p7 = new Person("Reza", "Rezayi" );


        //random vote.
        System.out.println("random vote.");
        votingSystem.voteRandom(0,p5);
        votingSystem.voteRandom(0,p6);
        votingSystem.voteRandom(0,p7);
        System.out.println();
        votingSystem.printResult(0);
        System.out.println();

        //create vote
        ArrayList<String> vote1 = new ArrayList<>();
        ArrayList<String> vote2 = new ArrayList<>();
        ArrayList<String> vote3 = new ArrayList<>();
        ArrayList<String> vote4 = new ArrayList<>();
        vote1.add("no");
        vote2.add("no");
        vote3.add("yes");
        vote3.add("Thursday");
        vote3.add("Friday");
        vote3.add("Saturday");
        vote4.add("yes");
        vote4.add("Saturday");


        //voting 0:
        System.out.println("voting 0:");
        votingSystem.vote(0,p1,vote1);
        votingSystem.vote(0, p1,vote1);
        votingSystem.vote(0, p2,vote2);
        votingSystem.vote(0, p3,vote3);
        votingSystem.vote(0, p4,vote4);
        System.out.println();
        votingSystem.printResult(0);
        System.out.println();

        //voting 1:
        System.out.println("voting 1");
        votingSystem.vote(1, p3, vote3);
        votingSystem.vote(1, p4, vote4);
        System.out.println();
        votingSystem.printResult(1);
    }
}
