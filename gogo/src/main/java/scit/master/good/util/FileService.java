package scit.master.good.util;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 관련 유틸
 * 업로드한 파일의 저장 & 서버에 저장된 파일 삭제 등의 기능 제공
 */
public class FileService {

	/**
	 * 업로드 된 파일을 지정된 경로에 저장하고, 저장된 파일명을 리턴
	 * @param mfile 업로드된 파일
	 * @param path 저장할 경로
	 * @return 저장된 파일명
	 */
	public static String saveFile(MultipartFile upload, String uploadPath) {
		// 원본 파일명에서 파일명과 확장자 분리
		String filename = null ;
		String ext = null;
		String savedFilename = null; 

		// 폴더 생성하기 (파일을 첨부했으면)
		if(!upload.isEmpty()) {
			File path = new File(uploadPath);

			if(!path.isDirectory()) {
				path.mkdirs();
			}

			String originalFilename = upload.getOriginalFilename(); 
			String uid = UUID.randomUUID().toString();


			int lastIndex = originalFilename.lastIndexOf('.');  // -1이면 .을 못찾음 즉, 확장명이 없는 경우

			if(lastIndex == -1) {
				filename = originalFilename;	// 확장자가 없는 경우
				ext = "";
			} else {							// 확장자가 있는 경우
				filename = originalFilename.substring(0, lastIndex);
				ext = originalFilename.substring(lastIndex + 1);
			}

			savedFilename = filename + "_" + uid + "." + ext;

			// 업로드된 파일을 HDD에 저장
			// 디렉토리명 + 파일명
			File serverFile = null;

			serverFile = new File(uploadPath + "/" + savedFilename);

			try {
				upload.transferTo(serverFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return savedFilename;
	}
	
	/**
	 * 서버에 저장된 파일의 전체 경로를 전달받아, 해당 파일을 삭제
	 * @param fullpath 삭제할 파일의 경로
	 * @return 삭제 여부
	 */
	public static boolean deleteFile(String fullpath) {
		//파일 삭제 여부를 리턴할 변수
		boolean result = false;
		
		//전달된 전체 경로로 File객체 생성
		File delFile = new File(fullpath);
		
		//해당 파일이 존재하면 삭제
		if (delFile.isFile()) {
			delFile.delete();
			result = true;
		}
		
		return result;
	}
}
