
package com.example.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.library.entity.Issue;
import com.example.library.service.IssueService;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    // Get all issues
    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {
        return ResponseEntity.ok(issueService.getAllIssues());
    }

    // Get issue by ID
    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Integer id) {
        return ResponseEntity.ok(issueService.getIssueById(id));
    }

    // Issue a book
    @PostMapping
    public ResponseEntity<Issue> issueBook(
            @RequestParam Integer bookId,
            @RequestParam Integer memberId) {

        Issue issue = issueService.issueBook(bookId, memberId);

        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }

    // Return a book
    @PutMapping("/{id}/return")
    public ResponseEntity<Issue> returnBook(@PathVariable Integer id) {
        return ResponseEntity.ok(issueService.returnBook(id));
    }

    // Get issue history by member ID
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Issue>> getIssuesByMember(
            @PathVariable Integer memberId) {

        return ResponseEntity.ok(
                issueService.getIssuesByMember(memberId));
    }

    // Get issue history by book ID
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Issue>> getIssuesByBook(
            @PathVariable Integer bookId) {

        return ResponseEntity.ok(
                issueService.getIssuesByBook(bookId));
    }

    // Get issues by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Issue>> getIssuesByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                issueService.getIssuesByStatus(status.toUpperCase()));
    }
}
