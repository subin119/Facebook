package com.facebook.user.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.facebook.user.schema.BookSchema;
import com.facebook.user.schema.Like;
import com.facebook.user.schema.MovieSchema;
import com.facebook.user.schema.UserSchema;
import com.facebook.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("")
	public ModelAndView viewIndexPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("user/index");
		return view;
	}

	@RequestMapping("/signUp")
	public ModelAndView viewSignUpPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("user/signUp");
		return view;
	}

	@RequestMapping("/doSignUp")
	public String doSignUpAction(UserSchema user) {

		user.setSignUpdate(new Date());

		userService.signUp(user);

		return "redirect:/user/signIn";
	}

	@RequestMapping("/signIn")
	public ModelAndView viewSignInpage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("user/signIn");
		return view;
	}

	@RequestMapping("/doSignIn")
	public String doSignInAction(UserSchema user, HttpSession session) {

		boolean signInResult = userService.signIn(user, session);
		logger.info("로그인 여부 " + signInResult);

		return "redirect:/feed";
	}

	@RequestMapping("/signOut")
	public String doSignOutAction(HttpSession session) {
		session.invalidate();
		return "redirect:/user/signIn";
	}

	@RequestMapping("/like/book")
	public String doRegistLikeBook(@RequestParam List<String> books, HttpSession session) {
		userService.addLikeBook(books, session);
		return "redirect:/user/like";
	}

	@RequestMapping("/like/movie")
	public String doRegistLikeMovie(@RequestParam List<String> movies, HttpSession session) {
		userService.addLikeMovie(movies, session);
		return "redirect:/user/like";
	}

	@RequestMapping("like")
	public ModelAndView viewUserLikePage(HttpSession session) {
		ModelAndView view = new ModelAndView();

		List<Like> userLikes = userService.getUserLike(session);
		List<BookSchema> books = userService.getAllBook();
		List<MovieSchema> movies = userService.getAllMovie();

		if (userLikes != null) {
			for (Like like : userLikes) {
				if (like instanceof BookSchema) {
					for (BookSchema book : books) {
						if (((BookSchema) like).getBookName().equals(book.getBookName())) {
							book.setBookName("");
						}
					}
				} 
				else if (like instanceof MovieSchema) {
					for (MovieSchema movie : movies) {
						if (((MovieSchema) like).getMovieName().equals(movie.getMovieName())) {
							movie.setMovieName("");
						}
					}
				}
			}
		}

		view.setViewName("user/like");
		view.addObject("userLikes", userLikes);
		view.addObject("books", books);
		view.addObject("movies", movies);
		return view;
	}

}
