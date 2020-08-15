
/**
 * A class to hold Vote details.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Vote {
    //the person field
    private Person person;
    //the date of vote
    private String date;

    /**
     * Create a new vote with a given person and date.
     *
     * @param person person field.
     * @param date date of vote.
     */
    public Vote(Person person, String date) {
        this.person = person;
        this.date = date;
    }

    /**
     * get The person's voter.
     * @return person field.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * get The date of vote.
     * @return date field.
     */
    public String getDate() {
        return date;
    }

    /**
     * Determine whether the given object equals a vote
     * @param o vote
     * @return true or false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote)) return false;

        Vote vote = (Vote) o;

        if (!getPerson().equals(vote.getPerson())) return false;
        return getDate().equals(vote.getDate());
    }

    /**
     * @return a integer
     */
    @Override
    public int hashCode() {
        int result = getPerson().hashCode();
        result = 31 * result + getDate().hashCode();
        return result;
    }
}
