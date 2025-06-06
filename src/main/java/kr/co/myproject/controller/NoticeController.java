package kr.co.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.myproject.entity.Notice;
import kr.co.myproject.service.NoticeService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @PostMapping("/notice-add")
    public String NoticeAdd(@ModelAttribute Notice notice, RedirectAttributes redirectAttributes) {
        if(notice == null)
        {
            redirectAttributes.addFlashAttribute("result", "글 정보가 올바르지 않습니다");
        	return "redirect:/";
        }

        int queryCount = noticeService.noticeInsert(notice);
        
        if(queryCount == 0)
        {
            redirectAttributes.addFlashAttribute("result", "글 작성에 실패했습니다");
        	return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("result", "글 작성에 성공했습니다");
        return "redirect:/";
    }

    @PostMapping("/notice-delete")
    public String NoticeDelete(@RequestParam int idx, RedirectAttributes redirectAttributes) {
        if(idx == 0)
        {
            redirectAttributes.addFlashAttribute("result", "정보가 올바르지 않습니다");
        	return "redirect:/";
        }

        int queryCount = noticeService.noticeDelete(idx);

        if(queryCount == 0)
        {
            redirectAttributes.addFlashAttribute("result", "글 삭제에 실패했습니다");
        	return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("result", "글 삭제에 성공했습니다");
        return "redirect:/";
    }

    @PostMapping("/notice-modify")
    public String NoticeModify(@ModelAttribute Notice notice, RedirectAttributes redirectAttributes) {
        if(notice == null)
        {
            redirectAttributes.addFlashAttribute("result", "글 정보가 올바르지 않습니다");
        	return "redirect:/";
        }

        int queryCount = noticeService.noticeUpdate(notice);

        if(queryCount == 0)
        {
            redirectAttributes.addFlashAttribute("result", "글 수정에 실패했습니다");
        	return "redirect:/";
        }
        
        redirectAttributes.addFlashAttribute("result", "글 수정에 성공했습니다");
        return "redirect:/";
    }
}
