package autobotzi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department-manager")
@RequiredArgsConstructor
public class DepartmentManagerController {

    @GetMapping
    public ResponseEntity<String> getDepartmentManager() {
        return ResponseEntity.ok("Hello Department Manager");
    }
}
