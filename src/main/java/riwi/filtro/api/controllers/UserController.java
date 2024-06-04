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
import riwi.filtro.api.dtos.reqest.UserRequest;
import riwi.filtro.api.dtos.response.UserResponse;
import riwi.filtro.infraestructure.abstractservices.IUserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {
    
    @Autowired
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAllUsers(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(this.userService.getAll(page - 1, size));
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> postMethodName(@RequestBody @Validated UserRequest request) {      
        UserResponse response = userService.create(request);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("update/{id}")
    public ResponseEntity<UserResponse> putMethodName(@PathVariable int id, @RequestBody @Validated UserRequest request){
        UserResponse response = userService.update(request, id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/getBy/{id}")
    public ResponseEntity<UserResponse> getMethodUsingId(@PathVariable int id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/getBy/{id}/submission")
    public ResponseEntity<UserResponse> getMethodIdLessons(@PathVariable int id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedUsingId(@PathVariable int id) {
        userService.delete(id);
      return ResponseEntity.ok("Eliminated correctly");
    }
    
}
