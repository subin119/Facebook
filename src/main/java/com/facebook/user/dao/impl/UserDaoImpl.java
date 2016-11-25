package com.facebook.user.dao.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.facebook.common.support.mongo.MongoTemplateSupport;import com.facebook.common.util.StringUtil;
import com.facebook.user.dao.UserDao;
import com.facebook.user.schema.BookSchema;
import com.facebook.user.schema.Like;
import com.facebook.user.schema.MovieSchema;
import com.facebook.user.schema.UserSchema;

@Repository
public class UserDaoImpl extends MongoTemplateSupport implements UserDao {

	@Override
	public void signUp(UserSchema user) {
		
		//save 와 insert의 차이 .. 세이브는 있으면 업데이트 없으면 새로 넣기
		getMongo().save(user);
	}

	@Override
	public String getSalt(UserSchema user) {
		Criteria criteria = new Criteria("userId");
		criteria.is(user.getUserId());
		
		Query query = new Query(criteria);
		query.fields().include("userSalt");

		return getMongo().findOne(query, UserSchema.class).getUserSalt();
	}

	@Override
	public UserSchema signIn(UserSchema user) {
		Criteria criteria = new Criteria("userId");
		criteria.is(user.getUserId());
		criteria = criteria.and("userPassword");
		criteria.is(user.getUserPassword());
		
		Query query = new Query(criteria);
		
		return getMongo().findOne(query, UserSchema.class);
	}

	@Override
	public void addlike(UserSchema user) {
		Criteria criteria = new Criteria("userId");
		criteria.is(user.getUserId());
		
		Query query = new Query(criteria);
		
		for(Like like : user.getLikes()) {
			if(like instanceof BookSchema) {
				if(StringUtil.isEmpty(((BookSchema) like).getBookName())) {
					continue;
				}
			}
			else if(like instanceof MovieSchema) {
				if(StringUtil.isEmpty(((MovieSchema) like).getMovieName())) {
					continue;
				}
			}
			
			Update update = new Update();
			update.push("likes", like);
			
			getMongo().updateFirst(query, update, "users");
		}
	}

	@Override
	public void addNewBook(BookSchema book) {
		Criteria criteria = new Criteria("bookName");
		criteria.is(book.getBookName());
		
		Query query = new Query(criteria);
		
		BookSchema tempBook = getMongo().findOne(query, BookSchema.class);
		
		if(tempBook == null) {
			getMongo().insert(book);
		}
	}

	@Override
	public void addNewMovie(MovieSchema movie) {
		Criteria criteria = new Criteria("movieName");
		criteria.is(movie.getMovieName());
		
		Query query = new Query(criteria);
		
		MovieSchema tempMovie = getMongo().findOne(query, MovieSchema.class);
		
		if(tempMovie == null) {
			getMongo().insert(movie);
		}
	}

	@Override
	public List<Like> getUserLike(String userId) {
		Criteria criteria = new Criteria("userId");
		criteria.is(userId);
		
		Query query = new Query(criteria);
		query.fields().include("likes");

		UserSchema user = getMongo().findOne(query, UserSchema.class);
		return user.getLikes();
	}

	@Override
	public List<BookSchema> getAllBook() {
		return getMongo().findAll(BookSchema.class);
	}

	@Override
	public List<MovieSchema> getAllMovie() {
		return getMongo().findAll(MovieSchema.class);
	}

}
