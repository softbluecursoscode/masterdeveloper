package br.com.softblue.bluefood;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.softblue.bluefood.domain.restaurante.RestauranteRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RestauranteRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Test
	public void t1() {
		
	}
}
