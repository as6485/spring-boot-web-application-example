package org.ayan.springboot.web.service;

import java.util.List;

import org.ayan.springboot.web.dao.BookDao;
import org.ayan.springboot.web.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BookService {

	@Autowired
	BookDao bookDao;

/*	public BookService() {
		bookDao = new BookDao();
	}*/

	public void persist(Book entity) {
		bookDao.openCurrentSessionwithTransaction();
		bookDao.persist(entity);
		bookDao.closeCurrentSessionwithTransaction();
	}

	public void update(Book entity) {
		bookDao.openCurrentSessionwithTransaction();
		bookDao.update(entity);
		bookDao.closeCurrentSessionwithTransaction();
	}

	public Book findById(String id) {
		bookDao.openCurrentSession();
		Book book = bookDao.findById(id);
		bookDao.closeCurrentSession();
		return book;
	}

	public List<Book> findByTitle(String title) {
		bookDao.openCurrentSession();
		List<Book> books = bookDao.findByTitle(title);
		bookDao.closeCurrentSession();
		return books;
		
	}
	
	public void delete(String id) {
		bookDao.openCurrentSessionwithTransaction();
		Book book = bookDao.findById(id);
		bookDao.delete(book);
		bookDao.closeCurrentSessionwithTransaction();
	}

	public List<Book> findAll() {
		bookDao.openCurrentSession();
		List<Book> books = bookDao.findAll();
		//System.out.println(books.toString());
		bookDao.closeCurrentSession();
		return books;
	}

	public void deleteAll() {
		bookDao.openCurrentSessionwithTransaction();
		bookDao.deleteAll();
		bookDao.closeCurrentSessionwithTransaction();
	}

/*	public BookDao bookDao() {
		return bookDao;
	}*/


}
