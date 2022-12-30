package article.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import creatboard11.dto.Article;
import creatboard11.util.Util;

public class ArticleController {

	private List<Article> articles;
	private Scanner sc;
	private int id;
	private int lastArticleId;

	public ArticleController(List<Article> articles, Scanner sc) {
		this.articles = articles;
		this.sc = sc;
	}

	public void doWrite() {

		int id = lastArticleId + 1;
		lastArticleId = id;
		String regDate = Util.getNowDateStr();

		System.out.println(regDate);

		System.out.println("제목) ");
		String title = sc.nextLine();
		System.out.println("내용) ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, title, body);
		// 제목과 내용을 쓰기 위해 article write 기능의 조건문에 있어야 한다
		articles.add(article);
		// ArrayList의 요소를 추가 하기 위함이디

		System.out.printf("%d번글이 생성되었습니다\n", id);

	}

	public void showList(String cmd) {
		if (articles.size() == 0) {

			System.out.println("게시글이 없습니다");
			return;
		} else {
			System.out.println("게시물이 있습니다");
		}

		String searchKeyword = cmd.substring("article list".length()).trim();

		List<Article> forPrintArticles = articles;

		if (searchKeyword.length() > 0) {
			// searchkeyword가 존재 항때
			forPrintArticles = new ArrayList<>();
			// forPrintArticles 에 ArrayList라는 객체를 만들어 넣겠다
			for (Article article : articles) {
				// 처음부터 끝까지 돌린다
				if (article.title.contains(searchKeyword)) {
					// contains는 문자열이 들어있는지 유무 확인 하는것인데
					// searchKeyword가 title이라는 변수에 문자열이 있으면 이라는 조건문
					forPrintArticles.add(article);
					// forPrintfArticles라는 변수에 article을 추가한다
				}
			}

			if (forPrintArticles.size() == 0) {
				System.out.println("검색결과가 없습니다");
			}
		}

		System.out.println("번호	|	제목	|	조회");
		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
			// 게시물이 저장되고 보여줄때 1번 부터 보여주게 하기 위함
			Article article = forPrintArticles.get(i);

			System.out.printf("%d	|	%s	|	%d\n", article.id, article.title, article.hit);		
	}

	}

	public void showdetail(String cmd) {
		// 앞에있는 cmd가 뒤에있는 문자열로 시작할경우

		String[] cmdBit = cmd.split(" ");
		int id = Integer.parseInt(cmdBit[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			// 게시물이 없는 경우
			System.out.printf("%d번 게시물이 존재하지 않습니다\n", id);
			return;
		}

		foundArticle.increaseHit();
		// 증가 하는 것이 게시물이 만들어 질때 증가 해야 하므로 여기에 있어야 한다

//		System.out.printf("%d번 게시물은 존재 합니다\n",id);
		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.regDate);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회 : %d\n", foundArticle.hit);
		
	}
	private int getArticleIndexById(int id) {
		int i = 0;

		for (Article article : articles) {
			// 향상된 for문으로 사용 하여 처음부터 끝까지 확인 가능하다

			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;

	}

	private Article getArticleById(int id) {

		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;

	}

	public void domodify(String cmd) {
		// 앞에있는 cmd가 뒤에있는 문자열로 시작할경우

		String[] cmdBit = cmd.split(" ");
		// split은 안에있는 인자를 기준으로 나눈다
		int id = Integer.parseInt(cmdBit[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			// 게시물이 없는 경우
			System.out.printf("%d번 게시물이 존재하지 않습니다\n", id);
			return;
		}
		System.out.println("수정할 제목) ");
		String title = sc.nextLine();
		System.out.println("수정할 내용) ");
		String body = sc.nextLine();

		foundArticle.title = title;
		foundArticle.title = body;

		System.out.printf("게시물이 수정되었습니다\n", id);		
	}

	public void dodelete(String cmd) {
		// 앞에있는 cmd가 뒤에있는 문자열로 시작할경우

		String[] cmdBit = cmd.split(" ");
		int id = Integer.parseInt(cmdBit[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			// 게시물이 없는 경우
			System.out.printf("%d번 게시물이 존재하지 않습니다\n", id);
			return;
		}

		articles.remove(foundArticle);

		System.out.printf("%d번 게시물이 삭제 되었습니다\n", id);

	}

			
	}


