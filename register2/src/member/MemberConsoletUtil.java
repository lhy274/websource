package member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberConsoletUtil {

	public Member getNewmember(Scanner sc) {

		System.out.println("등록할 회원 정보를 입력하세요");
		System.out.println("아이디 : ");
		int id = sc.nextInt();
		System.out.println("이름 : ");
		String name = sc.next();
		System.out.println("주소 : ");
		String addr = sc.next();
		System.out.println("이메일 : ");
		String email = sc.next();
		System.out.println("국가 : ");
		String nation = sc.next();
		System.out.println("나이 : ");
		int age = sc.nextInt();

		return new Member(id, name, addr, nation, email, age);

	}

	public void printMemberList(List<Member> list) {

		System.out.println("회원아이디\t    이름\t주소\t이메일\t국가\t나이");

		for (Member member : list) {
			System.out.printf("%5d", member.getId());
			System.out.printf("%7s", member.getName());
			System.out.printf("%8s", member.getAddr());
			System.out.printf("%18s", member.getEmail());
			System.out.printf("%6s", member.getNation());
			System.out.printf("%8d", member.getAge());
			System.out.println();
		}
	}

	public Member getUpdateMembe(Scanner sc, List<Member> list) {

		Member vo = null;

		System.out.print("수정할 회원 아이디를 입력하세요");
		int id = sc.nextInt();
		for(int i = 0; i < list.size(); i++) {
			vo = list.get(i); //멤버에 list 목록을 get 주는거야.
			if (vo.getId() == id) {
				System.out.println("수정할 주소를 입력하세요");
				String addr = sc.next();
				System.out.println("수정할 이메일을 입력하세요 ");
				String email = sc.next();
				
				vo.setAddr(addr);
				vo.setEmail(email);
				return vo;
			}		
		}
		return null;
	}

	public Member removeMember(Scanner sc, List<Member> list) {

		System.out.println("삭제할 회원 아이디를 입력하세요");
		int id = sc.nextInt();

		Member member = null;
		for (int i = 0; i < list.size(); i++) {
			member = list.get(i);
			if (member.getId() == id) {
				System.out.println("정말로 삭제하시겠습니까? 예(1) 아니오(2)");
				int no = sc.nextInt();
				list.remove(i);
				return member;
			}
		}
		return null;
	}

	public void printAddSuccessMessage(Member member) {

		System.out.println(member.getId() + "회원 정보 추가 성공");
	}

	public void printModifySuccessMessage(Member member) {
		System.out.println("회원정보수정 성공");
	}

	public void printModifyFaillMessage() {
		System.out.println("회원 아이디를 확인해 주세요");
	}

	public void printRemoveFaillMessage() {
		System.out.println("회원정보 삭제에 실패하였습니다");
	}

	public void printRemoveSuccessMessage() {
		System.out.println("회원정보 삭제에 성공하였습니다");
	}

}
