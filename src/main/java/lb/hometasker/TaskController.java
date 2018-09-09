package lb.hometasker;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDateTime;
import java.util.List;


@Controller
public class TaskController {
    TaskRepository taskRepository;
    PersonRepository personRepository;

    public TaskController(TaskRepository taskRepository, PersonRepository personRepository) {
        this.taskRepository = taskRepository;
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public String taskList(Model model) {
        List<Task> taskList = taskRepository.getTaskList();
        List<Person> personList = personRepository.getPersonList();
        model.addAttribute("tasks", taskList);
        model.addAttribute("personList", personList);

        return "homepage";
    }

    @GetMapping("/archive")//archiwum
    public String taskArchive(Model model) {
        List<Task> finishedTasks = taskRepository.gestFinishedTasks();
        model.addAttribute("finishedTasks", finishedTasks);

        return "archive";
    }

    @GetMapping("/addTask")
    public String showAddForm(Model model) {
        List<Person> personList = personRepository.getPersonList();
        model.addAttribute("newTask", new Task());
        model.addAttribute("personList", personList);

        return "addTaskForm";
    }

    @PostMapping("/addTask")
    public String addTask(Task task) {
        taskRepository.addTask(task);
        return "redirect:/";
    }

    @GetMapping("/removeTask")
    public String removeTask(@RequestParam String description) {
        taskRepository.removeTask(taskRepository.findByDescription(description));
        return "redirect:/";
    }

    @GetMapping("/endTask")
    public String endTask(@RequestParam String description, @RequestParam String personName) {
        Task task=taskRepository.findByDescription(description);
        task.setEndDate(LocalDateTime.now());
        Person person = personRepository.findByName(personName);
        if(task.getEndDate().isBefore(task.getDueDate())){
            person.setPoints(person.getPoints()+task.getPoints());
        }
        return "redirect:/";
    }


    @GetMapping("/editTask")
    public String edit(Model model,
                       @RequestParam String description) {
        Task task = taskRepository.findByDescription(description);
        List<Person> personList = personRepository.getPersonList();
        model.addAttribute("task", task);
        model.addAttribute("personList", personList);
        return "editTask";
    }

    @PostMapping("/editTask")
    public String editPerson(Task task, @RequestParam String oldDescription) {
        taskRepository.findByDescription(oldDescription).setPoints(task.getPoints());
        taskRepository.findByDescription(oldDescription).setPerson(task.getPerson());
        taskRepository.findByDescription(oldDescription).setDueDate(task.getDueDate());
        taskRepository.findByDescription(oldDescription).setDescription(task.getDescription());


        return "redirect:/";
    }


}
