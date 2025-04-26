package kh.edu.paragoniu.erp.service;

import kh.edu.paragoniu.erp.model.Exam;
import kh.edu.paragoniu.erp.model.Result;
import kh.edu.paragoniu.erp.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {
    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

//    Create
    public void addResult(Result result) {
        Exam exam = result.getExam();
        result.setCourse(exam.getCourse());
        result.setType(exam.getType());
        resultRepository.save(result);
    }

//    Read (All)
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

//    Read (Single)
    public Result getResultById(Integer id) {
        Optional<Result> result = resultRepository.findById(id);
        return result.orElse(null);
    }

//    Update
    public Result updateResult(Integer id, Result updatedResult) {
        return resultRepository.findById(id)
                .map(result -> {
                    result.setStudent(updatedResult.getStudent());
                    result.setGrade(updatedResult.getGrade());
                    result.setScore(updatedResult.getScore());
                    Exam exam = updatedResult.getExam();
                    result.setExam(exam);
                    result.setCourse(exam.getCourse());
                    result.setType(exam.getType());
                    return resultRepository.save(result);
                }).orElseGet(() -> {
//                    updatedResult.setId(id);
                    updatedResult.setCourse(updatedResult.getExam().getCourse());
                    updatedResult.setType(updatedResult.getExam().getType());
                    return resultRepository.save(updatedResult);
                });
    }

//    Delete
    public void deleteResult(Integer id) {
        resultRepository.deleteById(id);
    }
}
