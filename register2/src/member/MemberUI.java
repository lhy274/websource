package member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberUI {


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);//메뉴선택
		List<Member> list = new ArrayList<Member>();//2번 목록보기 // new를 빼먹었네
		MemberConsoletUtil action = new MemberConsoletUtil();//클래스 - 메서드 불러올 때
		boolean flag=false;
		
		do {
			System.out.println("==== 회원관리 프로그램 ====");
			System.out.println("1. 회원등록");
			System.out.println("2. 회원목록보기");
			System.out.println("3. 회원정보수정");
			System.out.println("4. 회원정보삭제");
			System.out.println("5. 프로그램 종료");
			System.out.println("메뉴번호 : ");
			
			
			int no = sc.nextInt();
			
			switch (no) {
			case 1:
				Member member=action.getNewmember(sc);
				list.add(member);
				action.printAddSuccessMessage(member);
				break;
			case 2:
				action.printMemberList(list);
				break;
			case 3:
				
				member=action.getUpdateMembe(sc, list);
				if(member == null) {
					action.printModifyFaillMessage();
				}else {
					action.printModifySuccessMessage(member);
				}
				break;
			case 4:
				member=action.removeMember(sc, list);// 변수를 줘야 이 액션에 대한 걸 써먹지
				if(member == null) {//이걸 할라면 먼저 액션의 변수가 있어야하네
					action.printRemoveFaillMessage();
				}else {
					action.printRemoveSuccessMessage();
				}
				break;
			case 5:
				flag=true;
				System.out.println("프로그램을 종료합니다");
				break;
			default:
				System.out.println("메뉴를 다시 확인해 주세요");
				break;
			}
	
		}while(!flag);
		
	}

}
