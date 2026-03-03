package app.nursing.specimen.service;

import app.nursing.record.dto.RecordDTO;
import app.nursing.specimen.dto.SpecimenDTO;

import java.util.List;

public interface SpecimenService {

    List<SpecimenDTO> searchSpecimen(String searchType, String searchValue);
    List<SpecimenDTO> findSpecimenList();
    SpecimenDTO findSpecimenDetail(String id);
    SpecimenDTO registerSpecimen(SpecimenDTO specimenDTO);
    SpecimenDTO modifySpecimen(String id, SpecimenDTO specimenDTO);
    void deleteSpecimen(String id);




}


