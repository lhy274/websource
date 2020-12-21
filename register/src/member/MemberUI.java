package member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberUI {

	public static void main(String[] args) {

		boolean isStop =false; // 반복문 종료
		
		Scanner sc = new Scanner(System.in); //키보드에서 입력을 받기 위해 사용
		MemberConsoletUtil util = new MemberConsoletUtil(); // 객체 생성
		List<Member> list = new ArrayList<Member>(); // 배열에 담아		
		
		do {
			System.out.println("==== 회원관리 프로그램 ====");
			System.out.println("1. 회원등록");
			System.out.println("2. 회원목록보기");
			System.out.println("3. 회원정보수정");
			System.out.println("4. 회원정보삭제");
			System.out.println("5. 프로그램 종료");
			System.out.print("메뉴번호 : ");
			//사용자한테 번호 입력받기
			int menu = sc.nextInt();
			
			//메뉴번호에 따라 출력문 제시
			
			
			switch (menu) { //메뉴가 1번이면 실행하고 break
			case 1: //회원등록				
				Member member=util.getNewmember(sc);
				list.add(member);
				util.printAddSuccessMessage(member);
				break;
			case 2: //회원목록보기
				util.printMemberList(list);
				break;
			case 3: //회원정보수정
				member = util.getUpdateMember(sc, list);
				if(member == null) {
					util.printModifyFaillMessage();
				}else {
					util.printModifySuccessMessage(member);
				}
				break;
			case 4: //회원정보삭제
				member=util.removeMember(sc, list);				
				if(member==null) {
					util.printRemoveFaillMessage();
				}else {
					util.printRemoveSuccessMessage();
				}
				break;
			case 5: //프로그램 종료
				System.out.println("프로그램 종료");
				isStop = true;
				break;
			default:
				System.out.println("메뉴를 다시 확인해 주세요");
				break;
			}
	
		}while(!isStop);//isStop 트루가 아닐 때까지 돌릴거야.

		
	}

}
