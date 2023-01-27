package be.vdab.school.console;

import be.vdab.school.exceptions.RepositoryException;
import be.vdab.school.repositories.LeerlingRepository;
import be.vdab.school.repositories.LesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {
    private final LeerlingRepository leerlingRepository;
    private final LesRepository lesRepository;

    public MyRunner(LeerlingRepository leerlingRepository, LesRepository lesRepository) {
        this.leerlingRepository = leerlingRepository;
        this.lesRepository = lesRepository;
    }

    @Override
    public void run(String... args) {
        try {
            leerlingRepository.findAll().forEach(leerling ->
                    System.out.println(leerling.getNummer() + ": " + leerling.getVoornaam() + " " + leerling.getFamilienaam()));
            lesRepository.findAll().forEach(les ->
                    System.out.println(les.getCode() + ": " + les.getNaam()));
        } catch (RepositoryException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
