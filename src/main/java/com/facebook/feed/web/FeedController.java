package com.facebook.feed.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.facebook.feed.schema.FeedSchema;
import com.facebook.feed.service.FeedService;

@Controller
@RequestMapping("/feed")
public class FeedController {

	@Autowired
	private FeedService feedService;
	
	@RequestMapping("")
	public ModelAndView viewFeedPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("feed/feed");
		return view;
		
	}
	
	@RequestMapping("/post")
	public String doPostAction(FeedSchema feed, HttpSession session){
			feedService.post(feed,session);
			return"redirect:/feed";
		
	}
	
	@RequestMapping("/{pageNo}")
	@ResponseBody
	public	List<FeedSchema> loadFeedPage(@PathVariable int pageNo){
		
		List<FeedSchema> feeds = feedService.feed(pageNo);
		return feeds;
		
	}
	
	@RequestMapping("/like/{id}")
	@ResponseBody
	public FeedSchema doLikeAction(@PathVariable String id, HttpSession session){
		return feedService.like(id,session);
	}
	
	@RequestMapping("/reply/{id}")
	@ResponseBody
	public FeedSchema doReplyAction(@PathVariable String id, @RequestParam String reply, HttpSession session){
		return feedService.reply(id, reply, session);
	}
	
}
