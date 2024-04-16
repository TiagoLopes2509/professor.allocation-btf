package com.project.professor.allocation.btf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.professor.allocation.btf.entity.Allocation;
import com.project.professor.allocation.btf.service.AllocationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Allocations")
@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {
	
	private final AllocationService allocationService;
	
	public AllocationController(AllocationService allocationService) {
		super();
		this.allocationService = allocationService;
	}
	
	@Operation(summary = "Find all Allocations")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK")
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findAll(@RequestParam(name = "name", required = false) String name){
		
		List<Allocation> allocations = allocationService.findAll();
		
		return new ResponseEntity<>(allocations, HttpStatus.OK);
	}
	
	@Operation(summary = "Find a Allocation")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
		@ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content)
	})
	@GetMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> findById(@PathVariable(name = "allocation_id", required = true) Long id){
		
		Allocation allocation = allocationService.findById(id);
		
		if(allocation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(allocation, HttpStatus.OK);
		}
		
	}
	
	@Operation(summary = "Find allocations by Professor")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "OK"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
	@GetMapping(path = "/professors/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findByProfessor(@PathVariable(name = "professor_id", required = true) Long id){

		List<Allocation> allocations = allocationService.findByProfessor(id);
		
		return new ResponseEntity<>(allocations, HttpStatus.OK);

	}
	
	@Operation(summary = "Find allocations by courses")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "OK"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
	@GetMapping(path = "/courses/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findByCourse(@PathVariable(name = "course_id", required = true) Long id){
		
		List<Allocation> allocations = allocationService.findByCourse(id);
		
		return new ResponseEntity<>(allocations, HttpStatus.OK);
			
	}
	
	@Operation(summary = "Save a Allocation")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Created"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> save(@RequestBody Allocation allocation){
		
		try {
			allocation = allocationService.create(allocation);
			
			return new ResponseEntity<>(allocation, HttpStatus.CREATED);
			
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
		
	}
	
	@Operation(summary = "Update a Allocation")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "OK"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    	@ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
	@PutMapping(path = "/{allocation_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> update(@PathVariable(name = "allocation_id", required = true) Long id,@RequestBody Allocation allocation){
		
		allocation.setId(id);
		
		try {
			
			allocation = allocationService.update(allocation);
			
			if(allocation == null) {
				 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(allocation, HttpStatus.OK);
			}
		} catch (Exception e) {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	
	@Operation(summary = "Delete a Allocation")
    @ApiResponses({
    	@ApiResponse(responseCode = "204", description = "No Content"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
	@DeleteMapping(path = "/{allocation_id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name = "allocation_id", required = true) Long id){
		
		allocationService.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@Operation(summary = "Delete all Allocations")
    @ApiResponses({
    	@ApiResponse(responseCode = "204", description = "No Content")
    })
	@DeleteMapping
	public ResponseEntity<Void> deleteAll(){
		
		allocationService.deleteAll();;
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
