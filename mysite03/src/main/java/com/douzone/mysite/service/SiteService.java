package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.repository.SiteRepository;
import com.douzone.mysite.vo.SiteVo;

@Service
public class SiteService {
	private static final String SAVE_PATH = "/mysite-uploads"; //리눅스 식으로 내꺼는 c드라이브에 올라간다.
	private static final String URL = "/images";

	@Autowired
	private SiteRepository siteRepository;
	
	public SiteVo getContents() {
//		SiteVo siteVo = new SiteVo();
		return siteRepository.findAll();
	}
	
	public int update(SiteVo siteVo) {
		int count = siteRepository.update(siteVo);
		return count;
	}

	public String restore(String profile) {
		
		return null;
	}
	
	public String restore(MultipartFile multipartFile) {
		String url = "";
		
		try {
			// 핵심! 컨트롤러에서 넘어온 파일을 처리하는 부분

			if (multipartFile.isEmpty()) {
				return url;
			}

			String originFilename = multipartFile.getOriginalFilename();
			
			String extName = originFilename.substring(originFilename.lastIndexOf('.')+1); //확장자만 가져오기
			
			String saveFilename = gernerateSaveFilename(extName);
			
			long fileSize = multipartFile.getSize();

			System.out.println("############# " + originFilename);
			System.out.println("############# " + fileSize);
			System.out.println("############# " + saveFilename);

			byte[] fileData = multipartFile.getBytes();
			
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename); //파일 오픈
			os.write(fileData);
			os.close();
			
			url = URL + "/" +saveFilename;
			
		} catch (IOException e) {
			throw new RuntimeException("file upload error : " + e); //나중에 mysite적용할때는 fileuploadException 만들어서 던져야 한다.
		}
		
		return url;

	}

	private String gernerateSaveFilename(String extName) {
		String filename = "";
		
		//시간을 넣어서 saveFilename을 생성한다. 
		//시간+확장자명 = saveFilename
		
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}
	
	
	
}
