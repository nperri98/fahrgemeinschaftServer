package com.nperri.fahrgemeinschaftServer;

import com.nperri.fahrgemeinschaftServer.entity.User;
import com.nperri.fahrgemeinschaftServer.help.Sha256;
import com.nperri.fahrgemeinschaftServer.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class FahrgemeinschaftServerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext= SpringApplication.run(FahrgemeinschaftServerApplication.class, args);

	     User nperri = new User();
		 User userTest = new User();

		 nperri.setUsername("nperri");
	     nperri.setAdress("Ludwigstraße 25");
		 nperri.setPassword(Sha256.hashPassword("test123"));
		 nperri.setBirthday(new Date(1998, Calendar.DECEMBER,16));
		 nperri.setCity("Puettlingen");
		 nperri.setFirstName("Nico");
		 nperri.setSurname("Perri");
		 nperri.setPostalcode(66346L);
		 nperri.setTeacher(false);




		userTest.setUsername("userTest");
		userTest.setAdress("Ludwigstraße 25");
		userTest.setPassword(Sha256.hashPassword("testPasswort"));
		userTest.setBirthday(new Date(1998,Calendar.DECEMBER,16));
		userTest.setCity("Puettlingen");
		userTest.setFirstName("Pico");
		userTest.setSurname("Nerri");
		userTest.setPostalcode(66346L);
		userTest.setTeacher(false);

		 UserRepository userRepository= applicationContext.getBean(UserRepository.class);
		 userRepository.save(nperri);
		 userRepository.save(userTest);

	}



}
