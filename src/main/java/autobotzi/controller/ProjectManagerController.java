package autobotzi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project-manager")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"
        ,"http://atc-2024-autobotzi-fe-linux-web-app.azurewebsites.net/"
        ,"https://frontend-jf48yfydc-eduard-ionel-eduards-projects.vercel.app/"
        ,"https://front-autobotzi-c55123365842.herokuapp.com/"})
public class ProjectManagerController {

    @GetMapping
    public ResponseEntity<String> getProjectManager() {
        return ResponseEntity.ok("Hello Project Manager");
    }
}

