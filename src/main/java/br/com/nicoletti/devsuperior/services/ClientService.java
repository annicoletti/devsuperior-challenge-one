package br.com.nicoletti.devsuperior.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.nicoletti.devsuperior.entity.to.ClientTO;
import br.com.nicoletti.devsuperior.entity.vo.ClientVO;
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

	private ClientVO parseClientTOtoVO(ClientTO to) {
		ClientVO vo = new ClientVO();
		vo.setBirthDate(to.getBirthDate());
		vo.setChildren(to.getChildren());
		vo.setCpf(to.getCpf());
		vo.setIncome(to.getIncome());
		vo.setName(vo.getName());
		return vo;
	}

	private ClientTO parseClientVOtoTO(ClientVO item) {
		ClientTO to = new ClientTO();
		to.setBirthDate(item.getBirthDate());
		to.setChildren(item.getChildren());
		to.setCpf(item.getCpf());
		to.setId(item.getId());
		to.setIncome(item.getIncome());
		to.setName(item.getName());
		return to;
	}

	public ClientTO update(Long id, ClientTO to) {
		ClientVO vo = clientRepository.getReferenceById(to.getId());
		vo.setBirthDate(to.getBirthDate());
		vo.setChildren(to.getChildren());
		vo.setCpf(vo.getCpf());
		vo.setIncome(to.getIncome());
		vo.setName(vo.getName());
		return parseClientVOtoTO(clientRepository.save(vo));
	}

	public void delete(Long id) {
		clientRepository.deleteById(id);
	}

}
