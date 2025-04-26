package kh.edu.paragoniu.erp.service;

import kh.edu.paragoniu.erp.model.Exam;
import kh.edu.paragoniu.erp.repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {
    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

//    Create
    public void addExam(Exam exam) {
        examRepository.save(exam);
    }

//    Read (ALl)
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

//    Read (Single)
    public Exam getExamById(int id) {
        Optional<Exam> exam = examRepository.findById(id);
        return exam.orElse(null);
    }

//    Update
    public void updateExam(Integer id, Exam exam) {
        Exam updatingExam = getExamById(id);
        updatingExam.setCourse(exam.getCourse());
        updatingExam.setDate(exam.getDate());
        updatingExam.setType(exam.getType());
        examRepository.save(updatingExam);
    }

//    Delete
    public void deleteExam(Integer id) {
        examRepository.deleteById(id);
    }
}
