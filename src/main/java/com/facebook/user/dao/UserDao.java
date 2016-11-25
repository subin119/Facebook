package com.facebook.user.dao;

import java.util.List;

import com.facebook.user.schema.BookSchema;
import com.facebook.user.schema.Like;
import com.facebook.user.schema.MovieSchema;
import com.facebook.user.schema.UserSchema;

public interface UserDao {

	public void signUp(UserSchema user);

	public String getSalt(UserSchema user);

	public UserSchema signIn(UserSchema user);

	public void addlike(UserSchema user);

	public void addNewBook(BookSchema book);

	public void addNewMovie(MovieSchema movie);

	public List<Like> getUserLike(String userId);

	public List<BookSchema> getAllBook();

	public List<MovieSchema> getAllMovie();


}
