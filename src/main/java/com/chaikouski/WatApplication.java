package com.chaikouski;

import com.chaikouski.model.ad.*;
import com.chaikouski.model.user.Owner;
import com.chaikouski.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;
import java.time.LocalDate;

@SpringBootApplication
public class WatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatApplication.class, args);
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("12000KB");
		factory.setMaxRequestSize("12000KB");
		return factory.createMultipartConfig();
	}

	@Bean
	public CommandLineRunner demo(AdsRepository ad, OwnerRepository owner, MarkRepository mark, ModelRepository model,
								  CharacteristicsRepository characteristics, ComplectationRepository complectation) {
		return (args) -> {
			owner.save(new Owner(1L, "John", "Williams", "example@gmail.com", "123", "123"));
			owner.save(new Owner(2L, "Ozzy", "Osburne", "example@yandex.ru", "123", "123"));
			owner.save(new Owner(3L, "Jack", "Osburne", "example@yahoo.ru", "123", "123"));
			mark.save(new Mark(1L, "BMW"));
			mark.save(new Mark(2L, "Audi"));
			model.save(new Model(1L, mark.getOne(1L), "f06"));
			model.save(new Model(2L, mark.getOne(1L), "f10"));
			model.save(new Model(3L, mark.getOne(2L), "a4"));
			model.save(new Model(4L, mark.getOne(2L), "a6"));
			ad.save(new Ad(1L, owner.getOne(1L), mark.getOne(1L), model.getOne(1L),
					"This is a fake ad", LocalDate.now().plusMonths(1).toString(), "", 0));
			ad.save(new Ad(2L, owner.getOne(2L), mark.getOne(1L), model.getOne(2L),
					"This is a fake ad", LocalDate.now().toString(), "", 0));
			ad.save(new Ad(3L, owner.getOne(1L), mark.getOne(2L), model.getOne(3L),
					"This is a fake ad", LocalDate.now().plusMonths(1).toString(), "", 0));
			ad.save(new Ad(4L, owner.getOne(2L), mark.getOne(2L), model.getOne(4L),
					"This is a fake ad", LocalDate.now().plusMonths(1).toString(), "", 0));
			characteristics.save(new Characteristics(1L, ad.getOne(1L), "Black", 4.3,
					8, "2017-01-01"));
			characteristics.save(new Characteristics(2L, ad.getOne(2L), "Black", 4.3,
					8, "2017-01-01"));
			characteristics.save(new Characteristics(3L, ad.getOne(3L), "Black", 4.3,
					8, "2017-01-01"));
			characteristics.save(new Characteristics(4L, ad.getOne(4L), "Black", 4.3,
					8, "2017-01-01"));
			complectation.save(new Complectation(1L, ad.getOne(1L), true, true, false));
			complectation.save(new Complectation(2L, ad.getOne(2L), true, true, false));
			complectation.save(new Complectation(3L, ad.getOne(3L), true, true, false));
			complectation.save(new Complectation(4L, ad.getOne(4L), true, true, false));
		};
	}
}
