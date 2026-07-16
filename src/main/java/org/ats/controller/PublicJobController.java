package org.ats.controller;

import lombok.RequiredArgsConstructor;
import org.ats.dto.JobCriteria;
import org.ats.dto.JobResponse;
import org.ats.services.JobService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/public/jobs")
@RequiredArgsConstructor
public class PublicJobController {
    private final JobService jobService;

}
