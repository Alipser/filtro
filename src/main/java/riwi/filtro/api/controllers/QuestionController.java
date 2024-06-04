package riwi.filtro.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import lombok.AllArgsConstructor;
import riwi.filtro.api.dtos.reqest.QuestionRequest;
import riwi.filtro.api.dtos.response.QuestionResponse;
import riwi.filtro.infraestructure.abstractservices.IQuestionService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping(path = "/Questions")
@AllArgsConstructor
public class QuestionController {
    
    @Autowired
    private final IQuestionService questionService;

    @GetMapping
    public ResponseEntity<Page<QuestionResponse>> getAllQuestions(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(this.questionService.getAll(page - 1, size));
    }

    @PostMapping
    public ResponseEntity<QuestionResponse> postMethodName(@RequestBody @Validated QuestionRequest request) {      
        QuestionResponse response = questionService.create(request);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponse> putMethodName(@PathVariable int id, @RequestBody @Validated QuestionRequest request){
        QuestionResponse response = questionService.update(request, id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> getMethodUsingId(@PathVariable int id) {
        return ResponseEntity.ok(questionService.getById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<QuestionResponse> patchMEthodUsingId(@PathVariable int id, @RequestBody String description) {
        return ResponseEntity.ok(questionService.patchById(id, description));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMappingUsingId(@PathVariable int id) {
        questionService.delete(id);
        return ResponseEntity.ok("Eliminated Succsefully");
    }


}
