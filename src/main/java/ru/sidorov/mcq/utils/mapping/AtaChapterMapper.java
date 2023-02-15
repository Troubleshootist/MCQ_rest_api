package ru.sidorov.mcq.utils.mapping;

import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.AtaChapterDto;
import ru.sidorov.mcq.model.AtaChapter;

@Service
public class AtaChapterMapper {

    public AtaChapterDto mapToDto(AtaChapter entity) {
        AtaChapterDto ataChapterDto = new AtaChapterDto(
                entity.getId(),
                entity.getAtaDigit(),
                entity.getAtaDescription()
        );
        return ataChapterDto;
    }
}
