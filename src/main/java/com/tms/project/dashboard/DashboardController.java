package com.tms.project.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

	@GetMapping(value = "/")
	public String dashboard() {
		return "redirect:/setup";
	}

	@GetMapping(value = "/projects")
	public String projects(Model model) {
		var view = View.PROJECT_LIST;
		model.addAttribute("view", view);
		return view.getPageName();
	}
}
