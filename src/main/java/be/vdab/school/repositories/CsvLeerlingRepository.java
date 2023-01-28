package be.vdab.school.repositories;

import be.vdab.school.domain.Leerling;
import be.vdab.school.exceptions.RepositoryException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class CsvLeerlingRepository implements LeerlingRepository {
    @Override
    public List<Leerling> findAll() {
        try (var stream = Files.lines(Path.of("/data/leerlingen.csv"))) {
            return stream
                    .map(regel -> regel.split(","))
                    .map(leerlingOnderdelen -> new Leerling(Long.parseLong(leerlingOnderdelen[0]),
                            leerlingOnderdelen[1], leerlingOnderdelen[2]))
                    .toList();
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new RepositoryException(ex);
        }
    }
}
