/**
 * 영화진흥위원회 API - XML 로 데이터를 받는 경우
 */
$(function(){
	init();
	
	$("#btn1").click(function(){
		let url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=f5eef3421c602c6cb7ea224104795888&targetDt=";
		url += $("#txtYear").val()+$("#selMon").val()+$("#selDay").val();
		
		let str = "";
		
		$.getJSON({
			url : url,
		
			success:function(data){
				console.log(data);
						
				$(data).find("dailyBoxOffice").each(function() {
					
					//순위					
					str += $(this).find("rank").text()+" 위";
					//증감
					var rankInten = parseInt($(this).find("rank").text());
					
					if(rankInten > 0) {
						str = str + "(▲";
					}else if(rankInten < 0) {
						str = str + "(▼";
					}else{
						str = str + "(";
					}
					
					str += rankInten + ")";
					
					//영화코드
					var movieCd = $(this).find("movieCd").text();
					
					//영화명
					var movieNm =  $(this).find("movieNm").text()+"<br>";
					str += "<a href='#' onclick='javascript:info("+movieCd+")'>"+movieNm+"</a>";
					
					$("#msg").html(str);

				})

			}			
		})
	})
})
function info(movieCd){
	//영화 상세 정보 요청
	var url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.xml?key=f5eef3421c602c6cb7ea224104795888&movieCd="+movieCd;
	
	$.get({
		url : url,
		success:function(data){
			console.log(data);
			
			var str = "<ul>";
			
			//한글제목
			var movieNm = $(data).find("movieNm").text();
			//영어제목
			var movieNmEn = $(data).find("movieNmEn").text();
			//상영시간
			var showTm = $(data).find("showTm").text();
			//감독
			var peopleNm = $(data).find("director").find("peopleNm").text();					
			
			//출연배우
/*			var actors = "";
				$(data).find("actor").each(function(){
					actors += $(this).find("peopleNm").text();					
			})	*/	
			
			var length = $(data).find("actor").find("peopleNm").length;
			
			var actors = "";
				$(data).find("actor").each(function(idx,tem){ //for(i in 배열)
					if(idx == length-1) {
						actors = actors += $(this).find("peopleNm").text();	
					}else{
						actors = actors += $(this).find("peopleNm").text()+", ";	
						
					}									
			})		
			
			str += "<li>영화제목 : "+movieNm+"</li>";
			str += "<li>영어제목 : "+movieNmEn+"</li>";
			str += "<li>상영시간 : "+showTm+"</li>";
			str += "<li>감독 : "+peopleNm+"</li>";
			str += "<li>출연배우 : "+actors+"</li></ul>";
			
			$(".box3").html(str);
			
		}
	})
}


function init(){
	//어제 날짜 세팅
	var newDate = new Date();
	var year = newDate.getFullYear();
	var month = newDate.getMonth()+1; // 월은 0부터 시작
	//var day = newDate.getDate()-1; //어제날짜 때문에 -1 한 것
	var day = newDate.getDate()-1; //어제날짜 때문에 -1 한 것
	
	$("#txtYear").val(year);
	
	if(month<10){
		month = "0"+month; //option value에서 1~9까지 숫자에 01 이런식으로 붙여 놔서 "0" 쓴거임
	}
	
	if(day<10){
		day="0"+day;
	}
	
	$("#selMon").val(month); // 이거 오류나서 뒤의 날짜도 오류난 거였네
	//$("#selDay").val(day);
	$("#selDay").val(day);
}

