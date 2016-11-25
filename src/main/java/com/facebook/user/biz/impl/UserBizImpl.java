package com.facebook.user.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.facebook.common.util.SHA256Util;
import com.facebook.common.util.StringUtil;
import com.facebook.user.biz.UserBiz;
import com.facebook.user.dao.UserDao;
import com.facebook.user.schema.BookSchema;
import com.facebook.user.schema.Like;
import com.facebook.user.schema.MovieSchema;
import com.facebook.user.schema.UserSchema;

@Component("userBiz")
public class UserBizImpl implements UserBiz {

	@Autowired
	private UserDao userDao;

	@Override
	public void signUp(UserSchema user) {

		String salt = SHA256Util.generateSalt();
		user.setUserSalt(salt);

		String password = user.getUserPassword();
		password = SHA256Util.getEncrypt(password, salt);

		user.setUserPassword(password);
		userDao.signUp(user);
	}

	@Override
	public UserSchema signIn(UserSchema user) {
		String salt = userDao.getSalt(user);
		user.setUserSalt(salt);

		String password = user.getUserPassword();
		password = SHA256Util.getEncrypt(password, salt);

		user.setUserPassword(password);

		return userDao.signIn(user);
	}

	@Override
	public void addNewBook(List<String> books) {
		
		BookSchema book = null;
		
		for (String bookName : books) {
			if(StringUtil.isNotEmpty(bookName)) {
				book = new BookSchema();
				book.setBookName(bookName);
				userDao.addNewBook(book);
			}
		}
	}

	@Override
	public void likeBook(UserSchema user) {
		userDao.addlike(user);
	}

	@Override
	public void addNewMovie(List<String> movies) {
		MovieSchema movie = null;
		
		for (String movieName : movies) {
			if(StringUtil.isNotEmpty(movieName)) {
				movie = new MovieSchema();
				movie.setMovieName(movieName);
				userDao.addNewMovie(movie);
			}
		}
	}

	@Override
	public void likeMovie(UserSchema user) {
		userDao.addlike(user);
	}

	@Override
	public List<Like> getUserLike(String userId) {
		return userDao.getUserLike(userId);
	}

	@Override
	public List<BookSchema> getAllBook() {
		return userDao.getAllBook();
	}

	@Override
	public List<MovieSchema> getAllMovie() {
		return userDao.getAllMovie();
	}

}
