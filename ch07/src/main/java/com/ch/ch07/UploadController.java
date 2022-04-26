package com.ch.ch07;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	@RequestMapping(value = "upload.do", method = RequestMethod.GET)
	public String uploadForm() {
		return "upload";
	}

	@RequestMapping(value = "upload.do", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile mf, Model model, HttpSession session)
			throws IllegalStateException, IOException {
		
		String fileName = mf.getOriginalFilename();	// 첨부 파일명
		int fileSize = (int) mf.getSize();	// 첨부 파일크기(단위:Byte) 8 -> 4
		
		// mf.transferTo(new File("/upload/"+fileName));	// 업로드 방법1
		
		
		// 업로드 방법2
		String path = session.getServletContext().getRealPath("/upload");
		System.out.println("path:"+path);
		
		FileOutputStream fos = new FileOutputStream(path + "/" + fileName);
		fos.write(mf.getBytes());
		fos.close();
		
		// cos.jar를 쓰지 않기 때문에 사진 중복 처리를 하지 않아서 중복된 사진을 넣을수 없다.
		
		model.addAttribute("fileName", fileName);
		model.addAttribute("fileSize", fileSize);
		return "uploadResult";
	}

	@RequestMapping("loginForm.do")
	public String loginForm() {
		return "loginForm";
	}

	@RequestMapping("login.do")
	public String login(String id, String pass, HttpSession session, Model model) {
		if (id.equals("java") && pass.equals("1234")) {	// 회원인증 성공
			session.setAttribute("id", id);
			return "loginSuccess";
		} else {
			model.addAttribute("msg", "똑바로 입력해");
			return "loginForm";
		}
	}
}