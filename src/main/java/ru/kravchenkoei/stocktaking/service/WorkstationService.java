package ru.kravchenkoei.stocktaking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kravchenkoei.stocktaking.dto.WorkstationDto;
import ru.kravchenkoei.stocktaking.exception.EntityNotFoundException;
import ru.kravchenkoei.stocktaking.mapper.WorkstationMapper;
import ru.kravchenkoei.stocktaking.model.Workstation;
import ru.kravchenkoei.stocktaking.repo.WorkstationRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkstationService {
  private final WorkstationRepo workstationRepo;
  private final WorkstationMapper workstationMapper;

  public WorkstationDto addWorkstation(WorkstationDto workstationDto) {
    return Optional.of(workstationDto)
        .map(workstationMapper::toDomain)
        .map(workstationRepo::save)
        .map(workstationMapper::toDto)
        .get();
  }

  public List<WorkstationDto> findAllWorkstations() {
    List<Workstation> workstationArrayList = new ArrayList<>();
    workstationRepo.findAll().forEach(workstationArrayList::add);
    return workstationArrayList.stream().map(workstationMapper::toDto).collect(Collectors.toList());
  }

  public WorkstationDto findWorkstationById(Long id) {
    return Optional.of(workstationRepo.findById(id))
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    "Рабочая машина с уникальным номером " + id + " не была найдена"))
        .map(workstationMapper::toDto)
        .get();
  }

  public WorkstationDto updateWorkstation(WorkstationDto workstationDto) {
    return addWorkstation(workstationDto);
  }

  public void deleteWorkstationById(Long id) {
    workstationRepo.deleteById(id);
  }
}
