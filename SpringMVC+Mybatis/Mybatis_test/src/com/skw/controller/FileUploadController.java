package com.skw.controller;

import java.io.File;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import com.skw.domain.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	
	
	@RequestMapping(value="/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request,
			@RequestParam("filename") String filename,
			@RequestHeader("User-Agent") String userAgent
			)throws Exception{
		//下载文件路径
		String path = request.getServletContext().getRealPath("/images/");
		//构建file
		File file = new File(path+File.separator+filename+".jpg");
		//ok表示Http协议中的状态200
		BodyBuilder builder = ResponseEntity.ok();
		//内容长度
		builder.contentLength(file.length());
		//application/octet-stream:二进制流数据（最常见的文件下载）；
		builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
		//使用URLDecoder。decode对文件名进行解码
		filename = URLEncoder.encode(filename,"UTF-8");
		//设置实际的响应文件名，告诉浏览器文件要以附件的形式打开
		//不同的浏览器，处理方式不同，要根据浏览器版本进行区别判断
		if(userAgent.indexOf("MSIE") > 0) {
			//如果是IE，只需要用UTF-9字符集进行URL编码即可
			builder.header("Content-disposition","attachment; filename="+filename);
		}else {
			//而FireFox、Chrome等浏览器，则需要说明编码的字符集
			//注意filename后面有个*号，在UTF-8后面又两个单引号
			builder.header("Content-disposition", "attachment; filename*=UTF-8''"+filename);
		}
		//将文件转为byte数组返回，浏览器会自动下载
		return builder.body(FileUtils.readFileToByteArray(file));
		
		
	}

}
