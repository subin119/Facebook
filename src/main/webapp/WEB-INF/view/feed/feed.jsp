<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>"></script>
<script type="text/javascript">
	$().ready(function() {
		
		var pageNo = 0;
		getFeeds(pageNo);
		
		$(window).scroll(function() {
			var scrollTop = $(window).scrollTop();
			var scrollEndPoint = 
						$(document).height() - $(window).height();
			
			if ( scrollEndPoint - scrollTop <= 1 ) {
				pageNo++;
				getFeeds(pageNo);
			}
		});
		
		$("#feeds").on("click", ".feed > .replyBtn", function() {
			var id = $(this).closest(".feed").data("id");
			var reply = $(this).closest(".feed").find("input.reply").val();
			var post = $(this).closest(".feed");
			
			$.post("<c:url value="/feed/reply/"/>" + id
					, {"reply" : reply}
					, function(data) {
						console.log(data);
						post.find(".replyCount").text(data.replies.length);
						post.find(".replyContent").html("");
						for(var i in data.replies) {
							if(data.replies[i] != null) {
								post.find(".replyContent")
									.append($("<div>" + data.replies[i].user.userName + "</div>"));
								post.find(".replyContent")
									.append($("<div>" + data.replies[i].message + "</div>"));
							}
						}
					}			
			);
		});
		
		$("#feeds").on("click", ".feed > .like", function() {
			
			var id = $(this).closest(".feed").data("id");
			var post = $(this).closest(".feed");
			
			console.log(id);
			$.post("<c:url value="/feed/like/"/>" + id
					, {}
					, function(data) {
						post.find(".likes").text(data.likeCount);
						post.find(".likePersons").html("");
						for(var i in data.like) {
							if(data.like[i] != null) {
								post.find(".likePersons")
									.append($("<span>" + data.like[i].userName + ", </span>"));
							}
						}
					}
			);
			
		});
		
	});
	
	
	function getFeeds(pageNo) {
		$.post("<c:url value="/feed/"/>" + pageNo, 
				{}, 
				function(data) {
					console.log(data);
					for ( var i in data ) {
						var feed = $("<div class='feed' data-id='" + data[i].id + "'></div>");
						feed.append( $("<p>" + data[i].user.userName + "</p>") );
						feed.append( $("<p>" + data[i].message + "</p>") );
						feed.append( $("<p>" + data[i].postedDate + "</p>") );
						feed.append( $("<span class='like'>좋아요</span> <span class='likes'>" + data[i].likeCount + "</span><span>명이 좋아합니다.</span>") );
						var likes = $("<p class='likePersons'></p>");
						for(var j in data[i].like) {							
							if(data[i].like[j] != null) {
								likes.append($("<span>" + data[i].like[j].userName + ", </span>"));
							}
						}
						feed.append(likes);
						
						feed.append($("<hr/>"));
						feed.append($("<input type='text' class='reply' />"));
						feed.append($("<input type='button' class='replyBtn' value='등록' />"));
						feed.append($("<hr/>"));
						feed.append($("<span class='replyCount'>" + data[i].replies.length + "</span><span>개의 댓글이 존재합니다.</span><br/>"));
						
						
						var replyContent = $("<p class='replyContent'></p>");
						for(var j in data[i].replies) {
							if(data[i].replies[j] != null) {
								replyContent.append($("<div>" + data[i].replies[j].user.userName + "</div>"));
								replyContent.append($("<div>" + data[i].replies[j].message + ", </div>"));
							}
						}
						feed.append(replyContent);
						
						$("#feeds").append(feed);
						$("#feeds").append( $("<hr/>") );
					}
				}
		);
	}
	
</script>
</head>
<body>
	
	<h3>무슨 생각을 하고 계신가요?</h3>
	<form method="post" action="<c:url value="/feed/post" />">
		<textarea name="message" style="width:100%; height: 200px;"></textarea>
		<input type="submit" value="포스팅" />
	</form>
	
	<hr/>
	
	<div id="feeds">
	</div>
</body>
</html>