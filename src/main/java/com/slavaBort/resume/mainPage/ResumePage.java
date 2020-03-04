package com.slavaBort.resume.mainPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: vaceslavalekseevic
 * @date :   02/03/2020
 */

@Controller
public class ResumePage {

    @GetMapping("/")
    public String startPage() {

        return "resume/index";
    }

}
