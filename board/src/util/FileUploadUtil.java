package util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadUtil {
	public void uploadFile(HttpServletRequest request) {
		//file upload 요청 파악하기
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if(isMultipart) {
			//전송된 파일을 디스크에 저장하기 위한 객체 생성
			DiskFileItemFactory foctory = new DiskFileItemFactory();
			//파일 업로드 handler
			ServletFileUpload upload = new ServletFileUpload(foctory);
			//request 파싱 위해 담기
			List<FileItem> fileItems = null;
			try {
				fileItems = upload.parseRequest(request);
			} catch (FileUploadException e1) {
				e1.printStackTrace();
			}			
			
			String fieldName = null, fileName=null, value = null;
			Iterator<FileItem> iter = fileItems.iterator();
			while(iter.hasNext()) {
				FileItem item = iter.next();
				
				if(item.isFormField()) {//type=file 이 아닌 것들
					fieldName = item.getFieldName();
					try {
						value = item.getString("utf-8");
					} catch (UnsupportedEncodingException e) {						
						e.printStackTrace();
					}

				}else{
					fieldName = item.getFieldName();
					fileName = item.getName();
					long size = item.getSize();
					
				 	//파일 저장하기
					String path="C:\\upload";
					if(!fileName.isEmpty()) {
						
						UUID uuid = UUID.randomUUID();
			
						File uploadFile = new File(path+"\\"+uuid.toString()+"_"+fileName);
						try {
							item.write(uploadFile);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} 					
				}//else{		
			}//while(iter.hasNext())
		}//if		
	}//FileUploadUtil 
	
}
