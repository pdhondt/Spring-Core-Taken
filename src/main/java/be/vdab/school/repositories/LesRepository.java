package be.vdab.school.repositories;

import be.vdab.school.domain.Les;
import be.vdab.school.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
@Component
public class LesRepository {
    private final String pad;
    public LesRepository(@Value("${lessenCsvPad}") String pad) {
        this.pad = pad;
    }
    public List<Les> findAll() {
        try (var stream = Files.lines(Path.of(pad))) {
            return stream
                    .map(regel -> regel.split(","))
                    .map(regelOnderdelen -> new Les(regelOnderdelen[0], regelOnderdelen[1]))
                    .toList();
        } catch (IOException | ArrayIndexOutOfBoundsException ex) {
            throw new RepositoryException(ex);
        }
    }
}
