package com.kust.edujunction.controllers;

import com.kust.edujunction.dtos.InstructorDTO;
import com.kust.edujunction.entities.InstructorEntity;
import com.kust.edujunction.services.InstructorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructors")
public class InstructorController {
    static final Logger logger = LoggerFactory.getLogger(InstructorController.class);
    private final InstructorService instructorService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Page<InstructorDTO>> getAll(@RequestParam(value = "page", required = false) int page,
                                               @RequestParam(value = "size", required = false) int size,
                                               @RequestParam(value = "sort", required = false) String sort) {
        logger.info("Get request for Instructor GetAll");
        return ResponseEntity.ok(this.instructorService.getPaginated(page, size, sort));
    }

    @PostMapping("/")
    ResponseEntity<InstructorEntity> save(@RequestBody InstructorDTO instructorDTO) {
        var savedEntity = this.instructorService.save(instructorDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedEntity.getId()).toUri()).body(savedEntity);
    }

    @GetMapping("/{id}")
    ResponseEntity<InstructorDTO> getById(@PathVariable("id") String id) {
        logger.info("Get request for Instructor ById {}", id);
        return ResponseEntity.ok(this.instructorService.getById(id));
    }
}
