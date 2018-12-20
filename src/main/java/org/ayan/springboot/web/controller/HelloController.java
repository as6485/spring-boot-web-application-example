package org.ayan.springboot.web.controller;

import java.util.List;

import org.ayan.springboot.web.model.Book;
import org.ayan.springboot.web.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

	@Autowired
	BookService bookService;

	/*
	 * @RequestMapping("/") public String index() { return "index"; }
	 * 
	 * @PostMapping("/hello") public String sayHello(@RequestParam("name") String
	 * name, Model model) { model.addAttribute("name", name); return "hello"; }
	 */

	@RequestMapping("/")
	public String home(Model m) {

		List<Book> list = bookService.findAll();
		System.out.println(list.toString());
		m.addAttribute("list", list);

		return "home";
	}

	@RequestMapping("/bookform")
	public String showform(Model m) {
		m.addAttribute("command", new Book());
		return "bookform";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("book") Book book) {
		bookService.persist(book);
		return "redirect:/";
	}

	@RequestMapping(value = "/editbook/{id}")
	public String edit(@PathVariable String id, Model m) {
		Book book = bookService.findById(id);
		m.addAttribute("command", book);
		return "bookeditform";
	}

	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("book") Book book) {
		bookService.update(book);
		return "redirect:/";
	}

	@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable String id) {
		bookService.delete(id);
		return "redirect:/";
	}

	/*
	 * @RequestMapping("/book") public String book(Model model) {
	 * model.addAttribute("book", "Spring MVC book"); return "book"; }
	 */
}
