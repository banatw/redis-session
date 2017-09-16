package org.bana.redissession;

import javax.servlet.http.HttpServletRequest;

import org.bana.model.FormModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@SpringBootApplication
public class RedisSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisSessionApplication.class, args);
	}
	
	@EnableRedisHttpSession
	public class HttpSesionConfig {
		
	}
	
	@Controller
	public class MyController {
		
		@GetMapping("")
		public String showForm() {
			return "form";
		}
		
		@PostMapping("/simpan")
		public String simpan(@ModelAttribute FormModel formModel,HttpServletRequest req) {
			req.getSession().setAttribute("nama", formModel.getNama());
			req.getSession().setAttribute("alamat", formModel.getAlamat());
			return "redirect:/hasil";
		}
		
		@GetMapping("/hasil")
		public String showHasil(Model model,HttpServletRequest req) {
			FormModel formModel = new FormModel();
			formModel.setNama((String) req.getSession().getAttribute("nama"));
			formModel.setAlamat((String) req.getSession().getAttribute("alamat"));
			model.addAttribute("form", formModel);
			return "hasil";
		}
	}
}
