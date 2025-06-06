package kr.co.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.myproject.entity.Board;
import kr.co.myproject.service.BoardService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping("/board-add")
    public String BoardAdd(@ModelAttribute Board board,  RedirectAttributes redirectAttributes) {
        if(board == null)
        {
            redirectAttributes.addFlashAttribute("result", "글 정보가 올바르지 않습니다");
        	return "redirect:/board-list-page";
        }

        if((board.getTitle() == null || board.getTitle().isEmpty())||
        (board.getContent() == null || board.getContent().isEmpty()))
        {
            redirectAttributes.addFlashAttribute("result", "제목과 내용을 모두 작성해 주세요");
        	return "redirect:/board-list-page";
        }

        int queryCount = boardService.boardInsert(board);

        if(queryCount == 0)
        {
            redirectAttributes.addFlashAttribute("result", "글 작성에 실패했습니다");
        	return "redirect:/board-list-page";
        }
        
        redirectAttributes.addFlashAttribute("result", "글 작성에 성공했습니다");
        return "redirect:/board-list-page";
    }

    @PostMapping("/board-delete")
    public String BoardDelete(@RequestParam int idx,  RedirectAttributes redirectAttributes) {
        if(idx == 0)
        {
            redirectAttributes.addFlashAttribute("result", "글 정보가 올바르지 않습니다");
        	return "redirect:/board-list-page";
        }

        int queryCount = boardService.boardDelete(idx);

        if(queryCount == 0)
        {
            redirectAttributes.addFlashAttribute("result", "글 삭제에 실패했습니다");
        	return "redirect:/board-list-page";
        }

        redirectAttributes.addFlashAttribute("result", "글 삭제에 성공했습니다");
        return "redirect:/board-list-page";
    }

    @PostMapping("/board-modify")
    public String BoardModify(@ModelAttribute Board board,  RedirectAttributes redirectAttributes) {
        if(board == null)
        {
            redirectAttributes.addFlashAttribute("result", "글 정보가 올바르지 않습니다");
        	return "redirect:/board-list-page";
        }

        if((board.getTitle() == null || board.getTitle().isEmpty())||
        (board.getContent() == null || board.getContent().isEmpty()))
        {
            redirectAttributes.addFlashAttribute("result", "제목과 내용을 모두 작성해 주세요");
        	return "redirect:/board-list-page";
        }

        int queryCount = boardService.boardUpdate(board);

        if(queryCount == 0)
        {
            redirectAttributes.addFlashAttribute("result", "글 수정에 실패했습니다");
        	return "redirect:/board-list-page";
        }

        redirectAttributes.addFlashAttribute("result", "글 수정에 성공했습니다");
        return "redirect:/board-list-page";
    }
}
