package ru.sidorov.mcq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.AtaChapterDto;
import ru.sidorov.mcq.model.AtaChapter;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.repository.AtaChapterRepository;
import ru.sidorov.mcq.utils.mapping.AtaChapterMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtaChapterService {

    private AtaChapterRepository ataChapterRepository;
    private AtaChapterMapper ataChapterMapper;

    @Autowired
    public void setAtaChapterMapper(AtaChapterMapper ataChapterMapper) {
        this.ataChapterMapper = ataChapterMapper;
    }

    @Autowired
    public void setAtaChapterRepository(AtaChapterRepository ataChapterRepository) {
        this.ataChapterRepository = ataChapterRepository;
    }


    public List<AtaChapterDto> findByAtaDigitIn(List<String> checkedAtaDigitList) {
        List<AtaChapter> ataChapterList = ataChapterRepository.findByAtaDigitIn(checkedAtaDigitList);
        return ataChapterList
                .stream()
                .map(ataChapter -> ataChapterMapper.mapToDto(ataChapter))
                .collect(Collectors.toList());

    }

    public List<AtaChapterDto> mapExamAtaChaptersToDto(Exam examDao) {
        return examDao.getAtaChapters().stream()
                .map(ataChapter -> {
                    AtaChapterDto ataChapterDto = ataChapterMapper.mapToDto(ataChapter);
                    return ataChapterDto;
                })
                .collect(Collectors.toList());
    }
}
