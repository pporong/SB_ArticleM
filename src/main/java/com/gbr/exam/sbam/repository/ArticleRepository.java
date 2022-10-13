package com.gbr.exam.sbam.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gbr.exam.sbam.vo.Article;

@Mapper
public interface ArticleRepository {

	// INSERT INTO article SET regDate = NOW(), updateDate = NOW(), title = ?, `body` = ?;
	public void writeArticle(String title, String body);

	// SELECT * FROM article WHERE id = ?;
	public Article getArticle(int id);

	// SELECT * FROM article ORDER BY id DESC;
	public List<Article> getArticles();

	// DELETE FROM article WHERE id = ?;
	public void deleteArticle(int id);

	// UPDATE article SET title = ?, `body` = ?, updateDate = NOW() WHERE id = ?;
	public void modifyArticle(int id, String title, String body);
	
	public int getLastInsertId();
	
}
