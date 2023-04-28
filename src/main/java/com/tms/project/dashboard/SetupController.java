package com.tms.project.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/setup")
public class SetupController {

	@GetMapping
	public String setup(Model model, @RequestParam(defaultValue = "0") int offset) {
		var view = View.SETUP;

		model.addAttribute("view", view);
		return view.getPageName();
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		var view = View.SETUP_CREATE;

		model.addAttribute("view", view);
		return view.getPageName();
	}

}
