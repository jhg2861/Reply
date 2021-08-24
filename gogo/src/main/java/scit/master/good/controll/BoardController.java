package scit.master.good.controll;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import scit.master.good.dao.BoardRepository;
import scit.master.good.util.FileService;
import scit.master.good.util.PageNavigator;
import scit.master.good.vo.Board;

@Controller
public class BoardController {
	
	private static Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardRepository repository;
	
	final String uploadPath = "/boardfile";
	
	
	
	@RequestMapping("/listboard")
	public String listboard(
			@RequestParam(value="currentPage", defaultValue="1") int currentPage,
			@RequestParam(value="searchItem", defaultValue="title") String searchItem,
			@RequestParam(value="searchWord", defaultValue="") String searchWord,			
			Model model) {
		
		logger.info("searchItem : {}", searchItem);
		logger.info("searchWord : {}", searchWord);
		logger.info("요청한 페이지 : " + currentPage);
		
		int totalRecordCount = repository.getBoardCount(searchItem, searchWord);
		logger.info("글 전체 개수 : " + totalRecordCount);
		
		PageNavigator navi = new PageNavigator(currentPage, totalRecordCount);
		
		int totalPageCount = navi.getTotalPageCount();
		int srow = navi.getSrow();
		int erow = navi.getErow();
		
		// 1) DB에 접속해서 글내용을 전부 가져옴
		List<Board> list = repository.selectAll(srow, erow, searchItem, searchWord);

		// 2) 글내용을 Model에 넣는 작업 수행
		model.addAttribute("list", list);
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("totalRecordCount", totalRecordCount);  // JSP에서 사용하지 않음
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("currentPage", navi.getCurrentPage());
		model.addAttribute("navi", navi);

		
		
		return "board/listBoard";
	}
	
	@RequestMapping("/writeboard")
	public String writeboard() {
		
		return "board/writeBoard";
	}
	
	@RequestMapping(value="/writeboard", method=RequestMethod.POST)
	public String writeboard(Board board, MultipartFile upload) {
		
		//폴더 생성하기(파일을 첨부했으면)
		if(!upload.isEmpty()) {
			File path = new File(uploadPath);
			
			if(!path.isDirectory()) {
				path.mkdirs();
			}
		
		
			String originalFilename = upload.getOriginalFilename();
			String uid = UUID.randomUUID().toString();	
			
			
			
			//원본 파일명에서 파일명과 확장자 분리
			String filename;
			String ext;
			String savedFilename;
			
			int lastIndex = originalFilename.lastIndexOf('.'); // -1이면 .을 못찾은거임
			
			
			//확장자가 없는 경우
			if(lastIndex == -1) {
				filename = originalFilename;
				ext="";
			} else {
				filename = originalFilename.substring(0, lastIndex);
				ext= originalFilename.substring(lastIndex + 1);
			}
			
			savedFilename = filename +"_"+ uid + "."+ext;
		
			// 업로드된 파일을 하드디스크에 저장
			// 디렉토리명 + 파일명을 트랜스퍼해줘야함
			File serverFile = null;
			
			serverFile = new File(uploadPath +"/"+ savedFilename);
			try {
				upload.transferTo(serverFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			board.setOriginalfile(originalFilename);
			board.setSavedfile(savedFilename);
		}
		
		int result = repository.insert(board);
		logger.info("게시글 등록 여부 : {}",result);
		
		return "redirect:listboard";
	}
	
	@RequestMapping("/detailboard")
	public String detailboard(int boardnum, Model model) {
		
		Board board = repository.selectOne(boardnum);
		repository.updateHitCount(boardnum);
		
		model.addAttribute("board",board);
		
		
		return "board/detailBoard";
	}
	
	@RequestMapping("/deleteboard")
	public String deliteboard(int boardnum) {
		logger.info("삭제할 글번호 : " + boardnum);
		
		Board b = repository.selectOne(boardnum);
		String oldfile = b.getSavedfile();
		
		if(oldfile != null) {
			String fullPath = uploadPath + "/" + oldfile;
			boolean result = FileService.deleteFile(fullPath);
			if(result) System.out.println("파일 삭제 완료");
		}
		
		repository.delete(boardnum);
		
		return "redirect:listboard";
	}
	
	@RequestMapping("/updateboard")
	public String updateboard(int boardnum, Model model) {
		
		Board board = repository.selectOne(boardnum);
		model.addAttribute("board",board);
		
		return "board/updateBoard";
	}
	
	@RequestMapping(value="/updateboard", method=RequestMethod.POST)
	public String updateboard(Board board, MultipartFile upload) {
		
		Board b = repository.selectOne(board.getBoardnum());
		String oldfile = b.getSavedfile();
		
		if(oldfile != null) {
			String fullPath = uploadPath + "/" + oldfile;
			boolean result = FileService.deleteFile(fullPath);
			if(result) System.out.println("파일 삭제 완료");
		}
		
		if(!upload.isEmpty()) {
			String originalFilename = upload.getOriginalFilename();
			String savedFilename = FileService.saveFile(upload, uploadPath);
			
			board.setOriginalfile(originalFilename);
			board.setSavedfile(savedFilename);
		}
		
		int result = repository.update(board);
		
		return "redirect:listboard";
	}
	
	@RequestMapping("/download")
	public String download(int boardnum, HttpServletResponse response) {
		Board board = repository.selectOne(boardnum);
		
		//원래 파일명
		String originalFile = board.getOriginalfile();
		
		try {
			response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(originalFile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String fullPath = uploadPath +"/"+ board.getSavedfile();
		
		FileInputStream filein = null;		//서버가 하드디스크에서 메모리로 파일을 업로드할 때 사용
		ServletOutputStream fileout = null; //클라이언트한테 통신으로 내보낼때 사용하는 객체
	
		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();
			
			FileCopyUtils.copy(filein, fileout);
			
			filein.close();
			fileout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	
	
	
}
































