package riwi.filtro.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import lombok.AllArgsConstructor;
import riwi.filtro.api.dtos.reqest.SurveyRequest;
import riwi.filtro.api.dtos.reqest.SurveyRequest;
import riwi.filtro.api.dtos.response.SurveyResponse;
import riwi.filtro.api.dtos.response.SurveyResponse;
import riwi.filtro.infraestructure.abstractservices.ISurveyService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping(path = "/surveys")
@AllArgsConstructor
public class SurveyController {
    
    @Autowired
    private final ISurveyService surveSerService;

    @GetMapping
    public ResponseEntity<Page<SurveyResponse>> getAllSurveys(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(this.surveSerService.getAll(page - 1, size));
    }

    @PostMapping
    public ResponseEntity<SurveyResponse> postMethodName(@RequestBody @Validated SurveyRequest request) {      
        SurveyResponse response = surveSerService.create(request);
        return ResponseEntity.ok(response);
    }  


    @GetMapping("/{id}")
    public ResponseEntity<SurveyResponse> getMethodUsingId(@PathVariable int id) {
        return ResponseEntity.ok(surveSerService.getById(id));
    }

}
