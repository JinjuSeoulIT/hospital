package app.nursing.imaging.service;

import app.nursing.imaging.dto.ImagingExamDTO;

import java.util.List;

public interface ImagingExamService {

    List<ImagingExamDTO> findImagingExamList();

    ImagingExamDTO findImagingExamDetail(String id);

    ImagingExamDTO registerImagingExam(ImagingExamDTO imagingExamDTO);

    ImagingExamDTO modifyImagingExam(String id, ImagingExamDTO imagingExamDTO);

    void deleteImagingExam(String id);

    List<ImagingExamDTO> searchImagingExam(String searchBy, String keyword);
}
