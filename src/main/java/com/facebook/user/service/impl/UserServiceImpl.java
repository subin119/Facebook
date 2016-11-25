package com.facebook.user.service.impl;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facebook.common.constants.Session;
import com.facebook.user.biz.UserBiz;
import com.facebook.user.schema.BookSchema;
import com.facebook.user.schema.Like;
import com.facebook.user.schema.MovieSchema;
import com.facebook.user.schema.UserSchema;
import com.facebook.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserBiz userBiz;

	@Override
	public void signUp(UserSchema user) {
		userBiz.signUp(user);
	}

	@Override
	public boolean signIn(UserSchema user, HttpSession session) {

		UserSchema signedUser = userBiz.signIn(user);
		if (signedUser != null) {
			session.setAttribute(Session.USER, signedUser);
			return true;
		}

		return false;
	}

	@Override
	public void addLikeBook(List<String> books, HttpSession session) {
		UserSchema user = (UserSchema) session.getAttribute(Session.USER);
		List<Like> likes = new ArrayList<Like>();
		
		BookSchema book = null;
		for (String bookName : books) {
			book = new BookSchema();
			book.setBookName(bookName);
			likes.add(book);
		}
		
		user.setLikes(likes);
		
		userBiz.addNewBook(books);
		userBiz.likeBook(user);
	}

	@Override
	public void addLikeMovie(List<String> movies, HttpSession session) {
		UserSchema user = (UserSchema) session.getAttribute(Session.USER);
		List<Like> likes = new ArrayList<Like>();

		MovieSchema movie = null;
		for (String movieName : movies) {
			movie = new MovieSchema();
			movie.setMovieName(movieName);
			likes.add(movie);
		}
		
		user.setLikes(likes);
		
		userBiz.addNewMovie(movies);
		userBiz.likeMovie(user);
	}

	@Override
	public List<Like> getUserLike(HttpSession session) {
		UserSchema user = (UserSchema) session.getAttribute(Session.USER);
		return userBiz.getUserLike(user.getUserId());
	}

	@Override
	public List<BookSchema> getAllBook() {
		return userBiz.getAllBook();
	}

	@Override
	public List<MovieSchema> getAllMovie() {
		return userBiz.getAllMovie();
	}
}
