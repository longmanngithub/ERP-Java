package kh.edu.paragoniu.erp.controller;

import kh.edu.paragoniu.erp.model.Exam;
import kh.edu.paragoniu.erp.model.Result;
import kh.edu.paragoniu.erp.service.ExamService;
import kh.edu.paragoniu.erp.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResultController {
    private final ResultService resultService;
    @Autowired
    private ExamService examService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/add_result")
    public String addResult(Model model) {
        model.addAttribute("result", new Result());
        return "add_result";
    }

    @PostMapping("/add_result")
    public String addResult(@RequestParam String student,
                            @RequestParam String grade,
                            @RequestParam Integer score,
                            @RequestParam Integer exam,
                            Model model) {
        Exam examObj = examService.getExamById(exam);
        Result result = new Result();
        result.setStudent(student);
        result.setGrade(grade);
        result.setScore(score);
        result.setExam(examObj);
        resultService.addResult(result);
        return "redirect:/all_results";
    }

    @GetMapping("/all_results")
    public String allResults(Model model) {
        model.addAttribute("results", resultService.getAllResults());
        model.addAttribute("exams", examService.getAllExams());
        return "all_results";
    }

    @GetMapping("/result/{id}")
    public String showResult(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("result", resultService.getResultById(id));
        model.addAttribute("exams", examService.getExamById(id));
        return "show_result";
    }

    @GetMapping("/edit_result/{id}")
    public String editResult(@PathVariable ("id") Integer id, Model model) {
        model.addAttribute("result", resultService.getResultById(id));
        return "edit_result";
    }

    @PostMapping("/edit_result/{id}")
    public String editResult(@PathVariable("id") Integer id,
                             @RequestParam String student,
                             @RequestParam String grade,
                             @RequestParam Integer score,
                             @RequestParam Integer exam,
                             Model model) {
        Exam examObj = examService.getExamById(exam);
        Result updatedResult = new Result();
//        updatedResult.setId(id);
        updatedResult.setStudent(student);
        updatedResult.setGrade(grade);
        updatedResult.setScore(score);
        updatedResult.setExam(examObj);
        resultService.updateResult(id, updatedResult);
        return "redirect:/all_results";
    }

    @PostMapping("/delete_result/{id}")
    public String deleteResult(@PathVariable Integer id) {
        resultService.deleteResult(id);
        return "redirect:/all_results";
    }
}
