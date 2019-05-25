package com.paf.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.paf.dao.RegistrationDao;
import com.paf.model.Login;
import com.paf.model.Registration;

@Controller
public class RegistrationController {

	@Autowired
	public RegistrationDao dao;

	@RequestMapping(value = "/addRegistration", method = RequestMethod.GET)
	public ModelAndView addRegistration(ModelAndView model) {
		Registration registration = new Registration();
		model.addObject("registration", registration);
		model.setViewName("addRegistration");
		return model;
	}
	
	@RequestMapping(value = "/editRegistration", method = RequestMethod.GET)
	public ModelAndView editRegistration(HttpServletRequest request) {
		
		int userID = Integer.valueOf(request.getParameter("userID"));
		
		Registration registration = dao.getRegistration(userID);
		ModelAndView model = new ModelAndView("editRegistration");
        model.addObject("registration", registration);
		return model;
	}
	
	@RequestMapping(value = "/viewAllRegistration")
	public ModelAndView viewAllCategory(ModelAndView model) {
		List<Registration> listRegistration = dao.getAllRegistration();
		model.addObject("listRegistration", listRegistration);
		model.setViewName("home");

		return model;
	}
	
	@RequestMapping(value = "/searchRegistration")
	public ModelAndView searchRegistration(HttpServletRequest request, ModelAndView model) {
		String val = request.getParameter("searchVal");
		List<Registration> listRegistration = dao.searchRegistration(val);
		model.addObject("listRegistration", listRegistration);
		model.setViewName("home");

		return model;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView model) {
		Registration registration = new Registration();
		List<Registration> listRegistration = dao.getAllRegistration();
		model.addObject("listRegistration", listRegistration);
		model.setViewName("home");
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", new Login());

		return mav;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request, ModelAndView model, HttpSession session) {
		
			session.invalidate();
			return new ModelAndView("redirect:/login");

		

	}

	@RequestMapping(value = "/loginProceed", method = RequestMethod.POST)
	public ModelAndView loginProceed(HttpServletRequest request, ModelAndView model, HttpSession session) {
		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		
		int x = dao.getCount(un, pw);
		//System.out.println(un + pw + x);

		if (x == 1) {
			JOptionPane.showMessageDialog(null, "Login Successfull!", "Successfull", JOptionPane.INFORMATION_MESSAGE);

			session.invalidate();
			HttpSession newSession = request.getSession();
			newSession.setAttribute("emailUser", un);
			return new ModelAndView("redirect:/home");

		}

		else {
			JOptionPane.showMessageDialog(null, "Incorrect Credentials!", "Failed", JOptionPane.ERROR_MESSAGE);
			return new ModelAndView("redirect:/login");
		}

	}

	@RequestMapping(value = "/saveRegistration", method = RequestMethod.POST)
	public ModelAndView saveRegistration(@ModelAttribute Registration registration) {
		int x = dao.addRegistration(registration);
		if (x == 1) {
			JOptionPane.showMessageDialog(null, "Registration added Sucessfully!", "Successfull",
					JOptionPane.INFORMATION_MESSAGE);
			return new ModelAndView("redirect:/login");
		}

		else {
			JOptionPane.showMessageDialog(null, "Registration not added Sucessfully!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return new ModelAndView("redirect:/saveRegistration");
		}
		
	}
	
	@RequestMapping(value = "/editSaveRegistration", method = RequestMethod.POST)
	public ModelAndView editSaveRegistration(@ModelAttribute Registration registration) {
		int x = dao.editRegistration(registration);
		
		if(x == 1) {
			JOptionPane.showMessageDialog(null, "User edited Sucessfully!", "Error",
					JOptionPane.INFORMATION_MESSAGE);
			return new ModelAndView("redirect:/home");
		}
		
		return new ModelAndView("redirect:/home");
	}
	
	@RequestMapping(value = "/setStatus", method = RequestMethod.GET)
	public ModelAndView setStatus(HttpServletRequest request) {
	
		int userID = Integer.parseInt(request.getParameter("userID"));
		String command = request.getParameter("command");
		
		
		if(dao.setStatus(userID, command) == 1 && command.equals("active")) {
			JOptionPane.showMessageDialog(null, "User activated Sucessfully!", "Error",
					JOptionPane.INFORMATION_MESSAGE);
			return new ModelAndView("redirect:/home");
		}
		
		else if(dao.setStatus(userID, command) == 1 && command.equals("inactive")) {
			JOptionPane.showMessageDialog(null, "User activated Sucessfully!", "Error",
					JOptionPane.INFORMATION_MESSAGE);
			return new ModelAndView("redirect:/home");
		}
		
		else {
			return new ModelAndView("redirect:/home");
		}
		
	}

}
