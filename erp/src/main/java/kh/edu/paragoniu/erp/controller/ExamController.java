package kh.edu.paragoniu.erp.controller;

import kh.edu.paragoniu.erp.model.Exam;
import kh.edu.paragoniu.erp.service.ExamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ExamController {
    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/add_exam")
    public String addExam(Model model) {
        model.addAttribute("exam", new Exam());
        return "add_exam";
    }

    @PostMapping("/add_exam")
    public String addExam(Exam exam) {
        examService.addExam(exam);
        return "redirect:/all_exams";
    }

    @GetMapping("/all_exams")
    public String allExams(Model model) {
        model.addAttribute("exams", examService.getAllExams());
        return "all_exams";
    }

    @GetMapping("/all_exams_json")
    @ResponseBody
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    @GetMapping("/exam/{id}")
    public String showExam(@PathVariable("id") Integer id, Model model) {
        Exam exam = examService.getExamById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        String formattedDate = exam.getDate().format(formatter);
        model.addAttribute("exam", exam);
        model.addAttribute("formattedDate", formattedDate);
        return "show_exam";
    }

    @GetMapping("/edit_exam/{id}")
    public String editExam(@PathVariable ("id") Integer id, Model model) {
        Exam exam = examService.getExamById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = exam.getDate().format(formatter);
        model.addAttribute("exam", exam);
        model.addAttribute("formattedDate", formattedDate);
        return "edit_exam";
    }

    @PostMapping("/edit_exam/{id}")
    public String editExam(@PathVariable("id") Integer id, Exam exam) {
        examService.updateExam(id, exam);
        return "redirect:/all_exams";
    }

    @PostMapping("/delete_exam/{id}")
    public String deleteExam(@PathVariable Integer id) {
        examService.deleteExam(id);
        return "redirect:/all_exams";
    }
}
