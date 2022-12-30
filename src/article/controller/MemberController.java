package article.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import creatboard11.dto.Article;
import creatboard11.dto.Member;
import creatboard11.util.Util;

public class MemberController {
	
	private List<Member> members;
	private Scanner sc;
	
	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc =sc;
	}

	public void doJoin() {
		

		String regDate = Util.getNowDateStr();
		int id = members.size()+ 1;
		
		String loginId; 
		String loginPw;
		String loginPwChk;
		
		while(true) {
			System.out.println("아이디 : ");
			loginId = sc.nextLine();
			
			if(isJoinableLoginId(loginId) == false) {
				System.out.printf("%s는 이미 사용중인 아이디 입니다\n",loginId);
				continue;
			}
			
			break;
		}
		
		while(true) {
			
			System.out.println("비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.println("비밀번호 확인: ");
			loginPwChk = sc.nextLine();
			
			if(!(loginPw.equals(loginPwChk))) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}
		System.out.println("이름 : ");
		String name = sc.nextLine();
		
		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.printf("%d번 회원이 생성되었습니다\n", id);

	}
	private int getMemberIndexByloginId(String loginId) {
		int i = 0;
		
		for (Member member : members) {
			// 향상된 for문으로 사용 하여 처음부터 끝까지 확인 가능하다
			
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private boolean isJoinableLoginId(String loginId) {

		int index = getMemberIndexByloginId(loginId);

		if (index != -1) {
			return true;
		}

		return false;
	}

}
