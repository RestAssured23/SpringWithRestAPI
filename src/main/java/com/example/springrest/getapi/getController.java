package com.example.springrest.getapi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("automation")
public class getController {
    private final GetDAO getDAO;

private GetBO getBO;
    public getController(GetDAO getDAO, GetBO getBO) {
        this.getDAO = getDAO;
        this.getBO = getBO;
    }

@GetMapping("/get/token")
    public String getToken(){
return getBO.getEmail();
    }

    @GetMapping("/get/test")
    public String test() {
        return getDAO.getCredtoken();
    }
    @GetMapping("/get/checkid")
    public String test1(@RequestParam String email, @RequestParam String password) {
        return getDAO.getTokenByemail(email,password);
    }
}


