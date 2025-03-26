package org.example.services;

import org.example.entitys.book.Book;
import org.example.repositorys.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll(); // Recupera todos os livros do banco
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(Integer id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new RuntimeException("Book not found with ID: " + id);
        }
    }

    public Book updateBook(int id, Book updatedBook) {
        Optional<Book> existingBookOptional = bookRepository.findById(id);

        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();

            // Atualiza os campos do livro com os novos valores
            existingBook.setName(updatedBook.getName());
            existingBook.setAutor(updatedBook.getAutor());
            existingBook.setGenero(updatedBook.getGenero());

            return bookRepository.save(existingBook);  // Salva e retorna o livro atualizado
        } else {
            throw new RuntimeException("Book not found with ID: " + id);
        }
    }

}
