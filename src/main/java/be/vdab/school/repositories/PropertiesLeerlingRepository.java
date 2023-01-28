package be.vdab.school.repositories;

import be.vdab.school.domain.Leerling;
import be.vdab.school.exceptions.RepositoryException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
@Primary
public class PropertiesLeerlingRepository implements LeerlingRepository {
    @Override
    public List<Leerling> findAll() {
        try (var stream = Files.lines(Path.of("/data/leerlingen.properties"))) {
            return stream
                    .map(regel -> regel.split(":"))
                    .map(regelOnderdelen -> {
                        var onderdelenNaDubbelePunt = regelOnderdelen[1].split(",");
                        return new Leerling(Long.parseLong(regelOnderdelen[0]),
                                onderdelenNaDubbelePunt[0], onderdelenNaDubbelePunt[1]);
                    })
                    .toList();
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new RepositoryException(ex);
        }
    }
}
