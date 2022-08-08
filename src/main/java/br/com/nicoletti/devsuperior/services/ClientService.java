package br.com.nicoletti.devsuperior.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.nicoletti.devsuperior.entity.to.ClientTO;
import br.com.nicoletti.devsuperior.entity.vo.ClientVO;
import br.com.nicoletti.devsuperior.exceptions.DatabaseException;
import br.com.nicoletti.devsuperior.exceptions.ResourceNotFoundException;
import br.com.nicoletti.devsuperior.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public Page<ClientTO> findAll(PageRequest pageRequest) {
		Page<ClientVO> list = clientRepository.findAll(pageRequest);
		return list.map(x -> new ClientTO(x));
	}

	public ClientTO findById(Long id) {
		Optional<ClientVO> optional = clientRepository.findById(id);
		ClientVO entity = optional.orElseThrow();
		return parseClientVOtoTO(entity);
	}

	public ClientTO insert(ClientTO to) {
		ClientVO out = clientRepository.save(parseClientTOtoVO(to));
		return parseClientVOtoTO(out);
	}

	public ClientTO update(Long id, ClientTO to) {
		try {
			ClientVO vo = clientRepository.getReferenceById(to.getId());
			vo.setBirthDate(to.getBirthDate());
			vo.setChildren(to.getChildren());
			vo.setCpf(vo.getCpf());
			vo.setIncome(to.getIncome());
			vo.setName(vo.getName());
			return parseClientVOtoTO(clientRepository.save(vo));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			clientRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}

	}

	private ClientTO parseClientVOtoTO(ClientVO vo) {
		ClientTO to = new ClientTO();
		to.setBirthDate(vo.getBirthDate());
		to.setChildren(vo.getChildren());
		to.setCpf(vo.getCpf());
		to.setId(vo.getId());
		to.setIncome(vo.getIncome());
		to.setName(vo.getName());
		return to;
	}

	private ClientVO parseClientTOtoVO(ClientTO to) {
		ClientVO vo = new ClientVO();
		vo.setBirthDate(to.getBirthDate());
		vo.setChildren(to.getChildren());
		vo.setCpf(to.getCpf());
		vo.setIncome(to.getIncome());
		vo.setName(to.getName());
		return vo;
	}

}
