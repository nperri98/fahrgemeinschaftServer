package com.nperri.fahrgemeinschaftServer;

import com.nperri.fahrgemeinschaftServer.entity.User;
import com.nperri.fahrgemeinschaftServer.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;

@SpringBootApplication
public class FahrgemeinschaftServerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext= SpringApplication.run(FahrgemeinschaftServerApplication.class, args);

	     User nperri = new User();
		 nperri.setUsername("nperri");
	     nperri.setAdress("Ludwigstraße 25");
		 nperri.setBirthday(new Date(1998,12,16));
		 nperri.setCity("Puettlingen");
		 nperri.setFirstName("Nico");
		 nperri.setSurname("Perri");
		 nperri.setPostalcode(66346L);
		 nperri.setTeacher(false);



		 UserRepository userRepository= applicationContext.getBean(UserRepository.class);
		 userRepository.save(nperri);

	}



}
