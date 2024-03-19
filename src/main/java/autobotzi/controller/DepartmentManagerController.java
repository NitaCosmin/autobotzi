package autobotzi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department-manager")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"
        ,"http://atc-2024-autobotzi-fe-linux-web-app.azurewebsites.net/"
        ,"https://frontend-jf48yfydc-eduard-ionel-eduards-projects.vercel.app/"
        ,"https://front-autobotzi-c55123365842.herokuapp.com/"})
public class DepartmentManagerController {

    @GetMapping
    public ResponseEntity<String> getDepartmentManager() {
        return ResponseEntity.ok("Hello Department Manager");
    }
}
