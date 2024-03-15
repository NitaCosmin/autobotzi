package autobotzi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project-manager")
@RequiredArgsConstructor
public class ProjectManagerController {

    @GetMapping
    public ResponseEntity<String> getProjectManager() {
        return ResponseEntity.ok("Hello Project Manager");
    }
}

