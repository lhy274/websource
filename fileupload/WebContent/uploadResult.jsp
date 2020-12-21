<%@page import="java.util.UUID"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/* 	request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String file1 = request.getParameter("file1");
	String file2 = request.getParameter("file2");
	
	out.print("<ul>");
	out.print("<li>title : "+title+"</li>");
	out.print("<li>content : "+content+"</li>");
	out.print("<li>file1 : "+file1+"</li>");
	out.print("<li>file2 : "+file2+"</li>");
	out.print("</ul>"); */

	//file upload 요청 파악하기
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	
	if(isMultipart) {
		//전송된 파일을 디스크에 저장하기 위한 객체 생성
		DiskFileItemFactory foctory = new DiskFileItemFactory();
		//파일 업로드 handler
		ServletFileUpload upload = new ServletFileUpload(foctory);
		//request 파싱 위해 담기
		List<FileItem> fileItems = upload.parseRequest(request);
		//for(MemberVO vo : list) 향상된 for문, Iterator
		
		
		
		String fieldName = null, fileName=null, value = null;
		Iterator<FileItem> iter = fileItems.iterator();
		while(iter.hasNext()) {
			FileItem item = iter.next();
			
			if(item.isFormField()) {//type=file 이 아닌 것들
				fieldName = item.getFieldName();
				value = item.getString("utf-8");
				out.print("<h3>일반</h3>");
				out.print(fieldName+" : "+value+"<br>");
			}else{
				fieldName = item.getFieldName();
				fileName = item.getName();
				long size = item.getSize();
				
				out.print("<h3>파일 데이터</h3>");
				out.print(fieldName+" : "+fileName+"<br>");
				out.print("파일 크기 : "+size);
				
			 	//파일 저장하기
				String path="C:\\upload";
				if(!fileName.isEmpty()) {
					
					UUID uuid = UUID.randomUUID();
					
					
					File uploadFile = new File(path+"\\"+uuid.toString()+"_"+fileName);
					item.write(uploadFile);
					
					
				//파일 다운로드를 위한 경로 설정
				out.print("<p>");
				out.print("<a href='download.jsp?fileName="+uploadFile.getName()+"'>"+fileName+"</a>");
				out.print("</p>");
				
				
				
				
				
				} 
				
			}
	
		}
	
	}


%>