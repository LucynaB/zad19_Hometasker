package lb.hometasker;

import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;


public class Task implements Comparable<Task>{
   private int id;
    //private static int count;
    private String description;
    private int points;
    private Person person;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dueDate;//LocalDateTime, porównanie isBefore isAfter
    private LocalDateTime endDate;


    public Task() {
    }

    public Task(String description, int points, LocalDateTime dueDate, Person person) {
        //count++;
        //this.id = count;
        this.description = description;
        this.points = points;
        this.person = person;
        this.dueDate = dueDate;
        this.endDate = null;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public int compareTo(Task t) {
        return -this.endDate.compareTo(t.endDate);
    }
}
