package ru.kravchenkoei.stocktaking.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kravchenkoei.stocktaking.data.model.Workstation;
import ru.kravchenkoei.stocktaking.data.repo.WorkstationRepo;
import ru.kravchenkoei.stocktaking.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkstationService {
  private final WorkstationRepo workstationRepo;

  public Workstation addWorkstation(Workstation workstationDto) {
    return Optional.of(workstationDto)
        .map(workstationRepo::save)
        .get();
  }

  public List<Workstation> findAllWorkstations() {
    return (List<Workstation>) workstationRepo.findAll();
  }

  public Workstation findWorkstationById(Long id) {
    return Optional.of(workstationRepo.findById(id))
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    "Рабочая машина с уникальным номером " + id + " не была найдена"))
        .get();
  }

  public Workstation updateWorkstation(Workstation workstation) {
    return addWorkstation(workstation);
  }

  public void deleteWorkstationById(Long id) {
    workstationRepo.deleteById(id);
  }
}
