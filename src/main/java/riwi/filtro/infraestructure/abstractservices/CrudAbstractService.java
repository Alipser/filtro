package riwi.filtro.infraestructure.abstractservices;

import org.springframework.data.domain.Page;

public interface CrudAbstractService <Req, Res, ID> {

    Page<Res> getAll(int page, int size);

    Res create(Req request);

    Res update(Req request, ID id);

    void delete(ID id);

    Res getById(ID id);

}
