package lb.hometasker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PersonController {
    PersonRepository personRepository;
    TaskRepository taskRepository;

    public PersonController(PersonRepository personRepository, TaskRepository taskRepository) {
        this.personRepository = personRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/rank")
    public String taskList(Model model) {
        List<Person> personList = personRepository.getPersonList();
        model.addAttribute("personList", personList);

        return "rank";
    }

    @GetMapping("/addPerson")
    public String showAddForm(Model model) {
        List<Person> personList = personRepository.getPersonList();
        model.addAttribute("newPerson", new Person());
        model.addAttribute("personList", personList);

        return "addPersonForm";
    }

    @PostMapping("/addPerson")
    public String addPerson(Person person) {
        personRepository.addPerson(person);
        return "redirect:/";
    }

    @GetMapping("/profil")
    public String showPerson(Model model, @RequestParam String name) {
        List<Task> taskList = taskRepository.getTaskList();
        Person person = personRepository.findByName(name);
        model.addAttribute("person", person);
        model.addAttribute("taskList", taskList);

        return "person";
    }

    @GetMapping("/removePerson")
    public String removeTask(@RequestParam String name) {
        personRepository.removePerson(personRepository.findByName(name));
        return "redirect:/rank";
    }

    @GetMapping("/editPerson")
    public String edit(Model model,
                       @RequestParam String name) {
        Person person = personRepository.findByName(name);
        model.addAttribute("person", person);
        return "editPerson";
    }

    @PostMapping("/editPerson")
    public String editPerson(Person person, @RequestParam String oldName) {
        personRepository.findByName(oldName).setPoints(person.getPoints());
        personRepository.findByName(oldName).setName(person.getName());


        return "redirect:/rank";
    }
}
