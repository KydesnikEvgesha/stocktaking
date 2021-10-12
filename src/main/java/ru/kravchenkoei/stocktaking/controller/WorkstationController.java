package ru.kravchenkoei.stocktaking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kravchenkoei.stocktaking.dto.WorkstationDto;
import ru.kravchenkoei.stocktaking.service.WorkstationService;

import java.util.List;

@RestController
@RequestMapping("/workstation")
@CrossOrigin("*")
@RequiredArgsConstructor
public class WorkstationController {

    private final WorkstationService workstationService;

    @GetMapping("/all")
    public ResponseEntity<List<WorkstationDto>> getAllWorkstation(){
        List<WorkstationDto> workstationDtos = workstationService.findAllWorkstations();
        return new ResponseEntity<>(workstationDtos, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<WorkstationDto> addWorkstation(@RequestBody WorkstationDto workstationDto){
        WorkstationDto newWorkstation = workstationService.addWorkstation(workstationDto);
        return new ResponseEntity<>(newWorkstation,HttpStatus.OK);
    }
}
