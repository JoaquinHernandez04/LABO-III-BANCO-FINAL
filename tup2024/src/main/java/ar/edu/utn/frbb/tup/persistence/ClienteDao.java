package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.persistence.entity.ClienteEntity;

public class ClienteDao extends AbstractBaseDao{

    // Este método busca un Cliente en la base de datos por su DNI
    public Cliente findByDni(long dni) {
        return getInMemoryDatabase().values().stream()
            .filter(entity -> entity instanceof ClienteEntity)
            .map(entity -> (ClienteEntity) entity)
            .filter(clienteEntity -> clienteEntity.getId() == dni) // Acceder al ID en BaseEntity
            .map(ClienteEntity::toCliente)
            .findFirst()
            .orElse(null);
    }
    
    // Este método guarda un Cliente en la base de datos
    public void save(Cliente cliente) {
        ClienteEntity entity = new ClienteEntity(cliente);
        getInMemoryDatabase().put(entity.getId(), entity);
    }

    @Override   //Este método indica el nombre de la tabla en la base de datos
    protected String getEntityName() {
        return "CLIENTE";
    }
}
