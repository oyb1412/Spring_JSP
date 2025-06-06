package kr.co.myproject.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.myproject.entity.Board;
import kr.co.myproject.entity.BoardType;
import kr.co.myproject.entity.Comment;
import kr.co.myproject.entity.Notice;
import kr.co.myproject.entity.User;
import kr.co.myproject.service.BoardService;
import kr.co.myproject.service.CommentService;
import kr.co.myproject.service.NoticeService;
import kr.co.myproject.service.UserService;


@Controller
public class PageController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/board-list-page")
	public String boardList(@RequestParam(defaultValue = "1") int page, Model model)
	{
		int pageSize = 10;
		int start = (page -1) * pageSize;
		int totalCount = boardService.getList().size();
		int totalPage = (int)Math.ceil((double)totalCount / pageSize);
		List<Board> boardList = boardService.getPagedList(start, pageSize);
		model.addAttribute("boardList", boardList);
		model.addAttribute("currentBoardPage", page);
		model.addAttribute("totalBoardPage", totalPage);
		return "boardList/index";
	}

	@GetMapping("/")
	public String noticeList(@RequestParam(defaultValue = "1") int page, Model model)
	{
		int pageSize = 10;
		int start = (page -1) * pageSize;

		int totalCount = noticeService.getList().size();
		int totalPage = (int)Math.ceil((double)totalCount / pageSize);
		List<Notice> noticeList = noticeService.getPagedList(start, pageSize);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("currentNoticePage", page);
		model.addAttribute("totalNoticePage", totalPage);

		return "noticeList/index";
	}
	
	@GetMapping("/login-page")
	public String loginPage()
	{
		return "login/index";
	}
	
	@GetMapping("/register-page")
	public String registerPage()
	{
		return "register/index";
	}
	
	@GetMapping("/board-add-page")
	public String boardAddPage(Model model, Authentication authentication, RedirectAttributes redirectAttributes)
	{
		if(authentication.getName() == null || authentication.getName().isEmpty())
		{
			redirectAttributes.addFlashAttribute("result", "먼저 로그인을 진행해주세요");
        	return "redirect:/board-list-page";
		}

		String writer = userService.findWriter(authentication.getName());

		if(writer == null)
		{
			redirectAttributes.addFlashAttribute("result", "유저 정보를 불러올 수 없습니다");
        	return "redirect:/board-list-page";
		}

		model.addAttribute("writer", writer);
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	String formattedNow = now.format(formatter);
		model.addAttribute("today", formattedNow);
		return "boardAdd/index";
	}	
	
	@GetMapping("/board-check-page")
	public String boardCheckPage(@RequestParam("idx") int idx, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
		Board board = boardService.findBoard(idx);
		model.addAttribute("board", board);
		int queryCount = boardService.plusBoardViewCount(idx);
		if(queryCount == 0)
		{
			redirectAttributes.addFlashAttribute("result", "글 정보를 불러올 수 없습니다");
        	return "redirect:/board-list-page";
		}

		List<Comment> comments = commentService.findComment(idx, BoardType.FREE);

		if(authentication != null)
		{
			String username = authentication.getName();
        	User user = userService.findByUsername(username); 
        	model.addAttribute("writer", user.getWriter());
		}
		
		model.addAttribute("comments", comments);
		return "boardCheck/index";
	}
	
	@GetMapping("/board-modify-page")
	public String boardModifyPage(@RequestParam("idx") int idx,Model model, RedirectAttributes redirectAttributes) {
		Board board = boardService.findBoard(idx);

		if(board == null)
		{
			redirectAttributes.addFlashAttribute("result", "글 정보를 불러올 수 없습니다");
        	return "redirect:/board-list-page";
		}

		model.addAttribute("board", board);
		
		return "boardModify/index";
	}

	@GetMapping("/notice-add-page")
	public String noticeAddPage(Model model, Authentication authentication, RedirectAttributes redirectAttributes)
	{
		String writer = userService.findWriter(authentication.getName());

		if(writer == null)
		{
			redirectAttributes.addFlashAttribute("result", "유저 정보를 불러올 수 없습니다");
        	return "redirect:/";
		}

		model.addAttribute("writer", writer);
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	String formattedNow = now.format(formatter);
		model.addAttribute("today", formattedNow);
		return "noticeAdd/index";
	}	
	
	@GetMapping("/notice-check-page")
	public String noticeCheckPage(@RequestParam("idx") int idx, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
		Notice notice = noticeService.findNotice(idx);
		model.addAttribute("notice", notice);
		int queryCount = noticeService.plusNoticeViewCount(idx);

		if(queryCount == 0)
		{
			redirectAttributes.addFlashAttribute("result", "글 정보를 불러올 수 없습니다");
        	return "redirect:/";
		}

		List<Comment> comments = commentService.findComment(idx,BoardType.NOTICE);
		if(authentication != null)
		{
			String username = authentication.getName();
        	User user = userService.findByUsername(username); 
        	model.addAttribute("writer", user.getWriter());
		}
		
		model.addAttribute("comments", comments);
		return "noticeCheck/index";
	}
	
	@GetMapping("/notice-modify-page")
	public String noticeModifyPage(@RequestParam("idx") int idx,Model model, RedirectAttributes redirectAttributes) {
		if(idx == 0)
		{
			redirectAttributes.addFlashAttribute("result", "글 정보를 불러올 수 없습니다");
        	return "redirect:/";
		}

		Notice notice = noticeService.findNotice(idx);

		if(notice == null)
		{
			redirectAttributes.addFlashAttribute("result", "글 정보를 불러올 수 없습니다");
        	return "redirect:/";
		}

		model.addAttribute("notice", notice);
		return "noticeModify/index";
	}

	@GetMapping("/my-page")
	public String myPage(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
		User user = userService.findByUsername(authentication.getName());

		if(user == null)
		{
			redirectAttributes.addFlashAttribute("result", "유저 정보가 올바르지 않습니다");
        	return "redirect:/";
		}

		model.addAttribute("user", user);

		return "myPage/index";
	}

	@GetMapping("/find-password-page")
	public String findPasswordPage() {
		return "findPassword/index";
	}
}
