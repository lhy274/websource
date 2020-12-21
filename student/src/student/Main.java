package student;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Student> list = new ArrayList<Student>();
		
		
		boolean flag = false;
		
		do {
			System.out.println("--------- <학생 정보 관리 프로그램> ---------");
			System.out.println("\t 1. 학생정보 입력");
			System.out.println("\t 2. 학생정보 전체 조회");
			System.out.println("\t 3. 학생정보 개별 조회");
			System.out.println("\t 4. 프로그램 종료");
			System.out.print("  선택 : ");
			
			int menu = sc.nextInt();
			Main m = new Main();
			
			switch (menu) {
			case 1:
				//Student s = m.addStudent(sc);
				//list.add(s);
				list.add(m.addStudent(sc));//바로 넣는 건 변수를 만들고 넣을 필요 없이 다이렉트로 넣으면 되는구나.
				break;
			case 2:
				m.studentList(list);
				break;
			case 3:
				m.aloneList(sc, list);
				break;
			case 4:
				System.out.println("프로그램 종료");
				flag = true;
				break;

			default:
				System.out.println("메뉴 번호를 다시 확인해 주세요");
				break;
			}
		
		}while(!flag);

	}//main(String[] args)
	

	public Student addStudent(Scanner sc) {
		Student s = new Student();
		System.out.println("---- 새로운 학생 정보 입력 -----");		
		System.out.print("학 번 : ");
		s.setNumber(sc.nextInt());
		//int number = sc.nextInt();				
		System.out.print("이 름 : ");
		s.setName(sc.next());
		//String name = sc.next();				
		System.out.print("학 년 : ");
		s.setGrade(sc.nextInt());
		//int grade = sc.nextInt();	
		sc.nextLine();	
		System.out.print("주 소 : ");
		s.setAddr(sc.nextLine());
		//String addr = sc.nextLine();	
		System.out.print("생 일(예시 : 05/11) : ");
		s.setBirthday(sc.nextLine());
		//String birthday = sc.nextLine();
		sc.nextLine();
		
		System.out.println(s.getName()+" 학생 정보가 입력되었습니다.");
		//System.out.println(name+" 학생 정보가 입력되었습니다.");
		System.out.println();
		
		return s;
		//return new Student(number, name, grade, addr, birthday);
		
	}
	
	public void studentList(ArrayList<Student> list) {
		System.out.println("---- 학생 정보 보기 -----");
		System.out.println("학 번\t 이 름\t 학 년");
		System.out.println("---------------------");
		
		for(Student vo : list) {
			System.out.printf("%d",vo.getNumber());
			System.out.printf("%8s",vo.getName());
			System.out.printf("%5d",vo.getGrade());
			System.out.println();
		}
	
	}
	
	public void aloneList(Scanner sc, ArrayList<Student> list) {
		System.out.print("검색하고자 하는 학생의 학번을 입력하세요\t");
		int num = sc.nextInt();
		
		Student student = null;
		for(int i=0; i<list.size();i++) {
			student = list.get(i); 
			if(student.getNumber() == num) { 
				System.out.println("---- 학생 개별 정보 보기 -----");		
				System.out.printf("이 름 : %s\n",student.getName());		
				System.out.printf("학 년 : %d\n",student.getGrade());				
				System.out.printf("주 소 : %s\n",student.getAddr());								
				System.out.printf("생 일(예시 : 05/11) : %s\n",student.getBirthday());
				System.out.println();
				
			}
		}	
	}
	
	
	

}
