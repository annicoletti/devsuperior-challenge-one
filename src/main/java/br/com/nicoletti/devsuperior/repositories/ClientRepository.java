package br.com.nicoletti.devsuperior.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nicoletti.devsuperior.entity.vo.ClientVO;

@Repository
public interface ClientRepository extends JpaRepository<ClientVO, Long> {

}
