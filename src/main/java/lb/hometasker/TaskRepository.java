package lb.hometasker;


import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private List<Task> taskList;
    PersonRepository personRepository = new PersonRepository();//do usunięcia

    public TaskRepository() {
        taskList = new ArrayList<>();
        Task task1 = new Task("Mycie podłóg", 3, LocalDateTime.of(2018, 9, 30, 18, 0, 0), personRepository.findByName("Zosia"));
        Task task2 = new Task("Mycie okien", 3, LocalDateTime.of(2018, 9, 7, 18, 0, 0), personRepository.findByName("Anna"));
        Task task3 = new Task("Zmywanie naczyń", 2, LocalDateTime.of(2018, 9, 30, 18, 0, 0), personRepository.findByName("Jaś"));
        Task task4 = new Task("Malowanie", 2, LocalDateTime.of(2018, 9, 30, 18, 0, 0), personRepository.findByName("Piotr"));
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);

    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public List<Task> gestFinishedTasks() {
        List<Task> finishedTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getEndDate() != null) {
                finishedTasks.add(task);
            }
        }
        return finishedTasks;
    }


    public void addTask(Task task) {
        taskList.add(task);

    }

    public void removeTask(Task task) {
        taskList.remove(task);
    }


    public Task findByDescription(String description) {
        for (Task task : taskList) {
            if (task.getDescription().equals(description))
                return task;
        }
        return null;
    }
}
