package be.vdab.school.repositories;

import be.vdab.school.domain.Leerling;

import java.util.List;

public interface LeerlingRepository {
    List<Leerling> findAll();
}
