package creatboard11;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import article.controller.ArticleController;
import article.controller.MemberController;
import creatboard11.dto.Article;
import creatboard11.dto.Member;
import creatboard11.util.Util;

public class App {
	private List<Article> articles;
	private List<Member> members;

	// 작성한 대로 공간을 증가하기 위해 ArraytList를 사용해랴한다
	// makeTestDate()와 main 메서드를 둘다 조종하기 위해서는 두개의 메서드가 묶여있는 main class 안에 있어야 한다
	// 또한 ArrayList의 타입이 public static인 이유는 상위 개념이 static 이면 그 안의 하위 개념 역시 static
	// 이어야 하기 때문이다
	App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void run() {
		System.out.println("==프로그램 시작==");

		// 스캐너 타입의 sc라는 변수를 만들고 스캐너객체를 만들어서 연결한다
		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);
		makeTestDate();

		int lastArticleId = 3;
		// while문 밖에 있는 이유는 매번 0이 되면 안되기때문이다

		while (true) {
			// 무한루프
			System.out.println("명령어) ");
			String cmd = sc.nextLine();
			System.out.println("입력된명령어 > " + cmd);

			// 이 안에 있는 명령어가 계속 실행된다

			if (cmd.equals("exit")) {
				// 조건문 cmd라는변수의 값이 exit라는 문자와 같다면
				break;
				// 탈출해라 break를 하지 않으면 무한반복문으로 실행된다
			}
			if (cmd.equals("member join")) {
				memberController.doJoin();
			} else if (cmd.equals("article write")) {
				
				articleController.doWrite();
			}

			else if (cmd.startsWith("article list")) {

				articleController.showList(cmd);
			}

			else if (cmd.startsWith("article detail ")) {
				articleController.showdetail(cmd);
			}

			else if (cmd.startsWith("article modify ")) {
				articleController.domodify(cmd);

			} else if (cmd.startsWith("article delete ")) {
			articleController.dodelete(cmd);	
		}
			else {
				System.out.println("존재하디 않는 명령어 입니다");
			}
		}

		sc.close(); // 스캐너기능을 하용하면 반드시 이 실행문을 추가햐야한다

		System.out.println("==프로그램 종료==");

	}

	

	private void makeTestDate() {

		System.out.println("테스트 데이터를 생성합니다");
		articles.add(new Article(1, Util.getNowDateStr(), "테스트1", "테스트1", 10));
		articles.add(new Article(2, Util.getNowDateStr(), "테스트2", "테스트2", 20));
		articles.add(new Article(3, Util.getNowDateStr(), "테스트3", "테스트3", 30));
	}

}
