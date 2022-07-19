package br.com.nicoletti.devsuperior.entity.to;

import java.time.Instant;

import br.com.nicoletti.devsuperior.entity.vo.ClientVO;

public class ClientTO {

	private Long id;

	private String name;

	private String cpf;

	private Double income;

	private Instant birthDate;

	private Integer children;

	public ClientTO() {
	}

	public ClientTO(Long id, String name, String cpf, Double income, Instant birthDate, Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}

	public ClientTO(ClientVO vo) {
		this.id = vo.getId();
		this.name = vo.getName();
		this.cpf = vo.getCpf();
		this.income = vo.getIncome();
		this.birthDate = vo.getBirthDate();
		this.children = vo.getChildren();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}

}
