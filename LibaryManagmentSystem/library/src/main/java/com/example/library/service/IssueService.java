package com.example.library.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.library.entity.Book;
import com.example.library.entity.Issue;
import com.example.library.entity.Member;
import com.example.library.repository.BookRepository;
import com.example.library.repository.IssueRepository;
import com.example.library.repository.MemberRepository;

@Service
public class IssueService {


private final IssueRepository issueRepository;
private final BookRepository bookRepository;
private final MemberRepository memberRepository;

public IssueService(IssueRepository issueRepository,
                    BookRepository bookRepository,
                    MemberRepository memberRepository) {
    this.issueRepository = issueRepository;
    this.bookRepository = bookRepository;
    this.memberRepository = memberRepository;
}

// Issue a book
@Transactional
public Issue issueBook(Integer bookId, Integer memberId) {

    Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new RuntimeException("Book not found."));

    if (book.isDeleted()) {
        throw new RuntimeException("This book has been deleted.");
    }

    if (!book.isAvailable()) {
        throw new RuntimeException("This book is not available.");
    }

    Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new RuntimeException("Member not found."));

    Issue issue = new Issue();
    issue.setBook(book);
    issue.setMember(member);
    issue.setIssueDate(LocalDate.now());
    issue.setReturnDate(null);
    issue.setStatus("ISSUED");

    book.setAvailable(false);

    bookRepository.save(book);
    return issueRepository.save(issue);
}

// Get all issue records
public List<Issue> getAllIssues() {
    return issueRepository.findAll();
}

// Get issue by ID
public Issue getIssueById(Integer id) {
    return issueRepository.findById(id).orElse(null);
}

// Return a book
@Transactional
public Issue returnBook(Integer id) {

    Issue issue = issueRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Issue record not found."));

    if ("RETURNED".equalsIgnoreCase(issue.getStatus())) {
        throw new RuntimeException("This book has already been returned.");
    }

    Book book = issue.getBook();

    issue.setReturnDate(LocalDate.now());
    issue.setStatus("RETURNED");

    // Do not restore availability for a soft-deleted book
    if (!book.isDeleted()) {
        book.setAvailable(true);
        bookRepository.save(book);
    }

    return issueRepository.save(issue);
}

// Get issue history by member
public List<Issue> getIssuesByMember(Integer memberId) {
    return issueRepository.findByMember_Id(memberId);
}

// Get issue history by book
public List<Issue> getIssuesByBook(Integer bookId) {
    return issueRepository.findByBook_Id(bookId);
}

// Get issues by status
public List<Issue> getIssuesByStatus(String status) {
    return issueRepository.findByStatus(status);
}


}
