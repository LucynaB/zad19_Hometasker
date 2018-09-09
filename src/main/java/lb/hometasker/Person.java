package lb.hometasker;

public class Person implements Comparable<Person> {
    private String name;
    private int points;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
        this.points = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Person p) {
        return -Integer.compare(this.points, p.points);
    }

    public void addPoints(int points) {
        this.points += points;
    }

}
