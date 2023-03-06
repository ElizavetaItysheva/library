package com.skypro.library.controller;
import com.skypro.library.dao.BookDAO;
import com.skypro.library.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

@Controller
@RequestMapping("/skypro")
public class SpringMVCController implements WebMvcConfigurer {
@Autowired
    private BookDAO bookDAO;
    @RequestMapping("/web")
    public String getView(Model model){
        List<Book> books = bookDAO.getAllOfBooks();
        model.addAttribute("books", books);
        return "dashboard";

    }
}
