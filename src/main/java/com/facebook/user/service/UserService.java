package com.facebook.user.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.facebook.user.schema.BookSchema;
import com.facebook.user.schema.Like;
import com.facebook.user.schema.MovieSchema;
import com.facebook.user.schema.UserSchema;

public interface UserService {

	public void signUp(UserSchema user);

	public boolean signIn(UserSchema user, HttpSession session);

	public void addLikeBook(List<String> books, HttpSession session);

	public void addLikeMovie(List<String> movies, HttpSession session);

	public List<Like> getUserLike(HttpSession session);

	public List<BookSchema> getAllBook();

	public List<MovieSchema> getAllMovie();

}
