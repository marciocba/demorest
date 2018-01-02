package com.teslusko.demorest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("pessoas")
public class PessoaResource {
	PessoaRepository repo=new PessoaRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Pessoa> getPessoa() {
		System.out.println("ok List<pessoa>");
		
		return repo.getPessoas();
		
	}
	@GET
	@Path("pessoa/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Pessoa getPessoa(@PathParam("id")int id) {
		
		return repo.getPessoa(id);
	}
	@POST
	@Path("pessoa/add")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Pessoa createPessoa(Pessoa a1) {
		System.out.println(a1);
		System.out.println("ok create pessoa <pessoa>");
		repo.create(a1);
		return a1;
	}
	
	@PUT
	@Path("pessoa/up")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Pessoa updatePessoa(Pessoa a1) {
		System.out.println(a1);
		System.out.println("ok update pessoa <pessoa>");
		if (repo.getPessoa(a1.getId())!=null) {
			repo.updade(a1);	
		}else {
			repo.create(a1);
		}
		
		return a1;
	}
	@DELETE
	@Path("pessoa/del/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Pessoa deletePessoa(@PathParam("id")int id) {
		Pessoa p=null;
		p=repo.getPessoa(id);
		if (p!=null) {
			repo.delete(id);	
		}
		return p;
	}

}
