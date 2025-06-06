package kr.co.myproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.myproject.entity.Notice;
import kr.co.myproject.mapper.NoticeMapper;

@Service
public class NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	public int noticeInsert(Notice notice)
	{
		return noticeMapper.noticeInsert(notice);
	}

	public List<Notice> getPagedList(int start, int pageSize){
		return noticeMapper.getPagedList(start, pageSize);
	}
	
	public List<Notice> getList()
	{
		return noticeMapper.getList();
	}
	
	public int noticeUpdate(Notice notice)
	{
		return noticeMapper.noticeUpdate(notice);
	}
	
	public int plusNoticeViewCount(int idx)
	{
		return noticeMapper.plusNoticeViewCount(idx);
	}
	
	public int noticeDelete(int idx)
	{
		return noticeMapper.noticeDelete(idx);
	}

	public Notice findNotice(int idx)
	{
		return noticeMapper.findNotice(idx);
	}
}
