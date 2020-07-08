package kz.iitu.csse.group34.controllers;

import kz.iitu.csse.group34.entities.Courses;
import kz.iitu.csse.group34.entities.Groups;
import kz.iitu.csse.group34.entities.Students;
import kz.iitu.csse.group34.repositories.CoursesRepository;
import kz.iitu.csse.group34.repositories.GroupsRepository;
import kz.iitu.csse.group34.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class MainController {

    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private StudentsRepository studentsRepository;

    @GetMapping(value = "/")
    public String index(ModelMap model){

        return "index";
    }
    @GetMapping(value = "/courses")
    public String courses(ModelMap model){
        List<Courses> courses = coursesRepository.findAll();
        model.addAttribute("courses", courses);
        return "courses";
    }
    @PostMapping(value = "/addCourses")
    public String addCourses(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "credits") int credits
    ){

        Courses courses = new Courses(null, name, credits);
        coursesRepository.save(courses);

        return "redirect:/courses";
    }
    @GetMapping(value = "/groups")
    public String groups(ModelMap model){
        List<Groups> groups = groupsRepository.findAll();
        model.addAttribute("groups", groups);
        return "groups";
    }
    @PostMapping(value = "/addGroups")
    public String addGroups(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "shortName") String shortName
    ){

        Groups groups = new Groups(null, name, shortName);
        groupsRepository.save(groups);

        return "redirect:/groups";
    }
    @GetMapping(value = "/students")
    public String students(ModelMap model){
        List<Students> students = studentsRepository.findAll();
        model.addAttribute("students", students);
        return "students";
    }
    @PostMapping(value = "/addStudents")
    public String addStudents(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "addmission") int addmission
    ){

        Students students = new Students(null, name, surname, addmission, null, null);
        studentsRepository.save(students);

        return "redirect:/students";
    }
    @GetMapping(path = "/details/{id}")
    public String details(ModelMap model, @PathVariable(name = "id") Long id){

        Optional<Groups> groups = groupsRepository.findById(id);


        model.addAttribute("groups", groups.orElse(new Groups(null, "No name", "kek")));
        return "details";
    }
    @GetMapping(path = "/editCourses/{id}")
    public String editCourses(ModelMap model, @PathVariable(name = "id") Long id){

        Optional<Courses> courses = coursesRepository.findById(id);


        model.addAttribute("courses", courses.orElse(new Courses(null, "No name", 0)));
        return "editCourse";
    }
    @PostMapping(value = "/editCourses")
    public String editCourses(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "credits") int credits

    ){
        Optional<Courses> courses = coursesRepository.findById(id);

        courses.get().setName(name);
        courses.get().setCredits(credits);

        coursesRepository.save(courses.get());
        return "redirect:/courses/";
    }

    @GetMapping(path = "/edit/{id}")
    public String edit(ModelMap model, @PathVariable(name = "id") Long id){
        List<Courses> courses = coursesRepository.findAll();
        List<Groups> groups = groupsRepository.findAll();
        Optional<Students> students = studentsRepository.findById(id);
        Set<Courses> course = students.get().getCourses();
        Set<Groups> group = students.get().getGroups();
        model.addAttribute("students", students.orElse(new Students(0L, "No Name", "No Name", 0, null, null)));
        model.addAttribute("course", course);
        model.addAttribute("courses", courses);
        model.addAttribute("group", group);
        model.addAttribute("groups", groups);
        return "edit";
    }
    @PostMapping(value = "/editStudents")
    public String editStudents(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "addmission") int addmission

    ){
        Optional<Students> students = studentsRepository.findById(id);

        students.get().setName(name);
        students.get().setSurname(surname);
        students.get().setYearOfAddmission(addmission);

        studentsRepository.save(students.get());
        return "redirect:/students/";
    }
    @PostMapping(value = "/addCoursesForStudent")
    public String addCoursesForStudent(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "courses_id") Long courses_id

    ){
        Optional<Courses> courses = coursesRepository.findById(courses_id);
        Optional<Students> students = studentsRepository.findById(id);
        students.get().getCourses().add(courses.get());


        studentsRepository.save(students.get());
        return "redirect:/edit/" + students.get().getId();
    }
    @PostMapping(value = "/addGroupsForStudent")
    public String addGroupsForStudent(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "groups_id") Long groups_id

    ){
        Optional<Groups> groups = groupsRepository.findById(groups_id);
        Optional<Students> students = studentsRepository.findById(id);
        students.get().getGroups().add(groups.get());


        studentsRepository.save(students.get());
        return "redirect:/edit/" + students.get().getId();
    }

    @PostMapping(value = "/removeCourses")
    public String removeCourses(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "courses_id") Long courses_id

    ){
        Optional<Courses> courses = coursesRepository.findById(courses_id);
        Optional<Students> students = studentsRepository.findById(id);
        students.get().getCourses().remove(courses.get());


        studentsRepository.save(students.get());
        return "redirect:/edit/" + students.get().getId();
    }
    @PostMapping(value = "/removeGroups")
    public String removeGroups(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "groups_id") Long groups_id

    ){
        Optional<Groups> groups = groupsRepository.findById(groups_id);
        Optional<Students> students = studentsRepository.findById(id);
        students.get().getGroups().remove(groups.get());


        studentsRepository.save(students.get());
        return "redirect:/edit/" + students.get().getId();
    }
}