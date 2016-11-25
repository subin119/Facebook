package com.facebook.user.biz;

import java.util.List;

import com.facebook.user.schema.BookSchema;
import com.facebook.user.schema.Like;
import com.facebook.user.schema.MovieSchema;
import com.facebook.user.schema.UserSchema;

public interface UserBiz {

	public void signUp(UserSchema user);

	public UserSchema signIn(UserSchema user);

	public void addNewBook(List<String> books);

	public void likeBook(UserSchema user);

	public void addNewMovie(List<String> movies);

	public void likeMovie(UserSchema user);

	public List<Like> getUserLike(String userId);

	public List<BookSchema> getAllBook();

	public List<MovieSchema> getAllMovie();


}
