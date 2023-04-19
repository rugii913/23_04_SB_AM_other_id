package com.KoreaIT.kjs.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.KoreaIT.kjs.demo.repository.ArticleRepository;
import com.KoreaIT.kjs.demo.util.Ut;
import com.KoreaIT.kjs.demo.vo.Article;
import com.KoreaIT.kjs.demo.vo.ResultData;

@Service
public class ArticleService {
	
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	//서비스 메서드
	public ResultData<Integer> writeArticle(int memberId, String title, String body) {
		
		articleRepository.writeArticle(memberId, title, body);
		
		int id = articleRepository.getLastInsertId();
		
		return ResultData.from("S-1", Ut.f("%d번 글이 생성되었습니다.", id), "id", id);
	}

	public Article getArticle(int id) {

		return articleRepository.getArticle(id);
	}

	public void deleteArticle(int id) {
		
		articleRepository.deleteArticle(id);
	}

	public ResultData modifyArticle(int id, String title, String body) {
		
		articleRepository.modifyArticle(id, title, body);
		
		Article article = getArticle(id);
		
		return ResultData.from("S-1", Ut.f("%d번 글을 수정했습니다.", id), "article", article);
	}
	
	public ResultData actorCanModify(Integer memberId, Article article) {
		if (article.getMemberId() != memberId) {
			return ResultData.from("F-2", "해당 글에 대한 권한이 없습니다.");
		}
		return ResultData.from("S-1", "수정 가능");
	}

	public List<Article> getArticles() {
		
		return articleRepository.getArticles();
	}

}
