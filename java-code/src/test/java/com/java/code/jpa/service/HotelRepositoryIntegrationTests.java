package com.java.code.jpa.service;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.code.jpa.domain.City;
import com.java.code.jpa.domain.Hotel;
import com.java.code.jpa.domain.HotelSummary;
import com.java.code.jpa.domain.Rating;
import com.java.code.jpa.domain.RatingCount;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link HotelRepository}.
 *
 * @author Oliver Gierke
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelRepositoryIntegrationTests {

	@Autowired
	CityRepository cityRepository;
	@Autowired
	HotelRepository repository;

	@Test
	public void executesQueryMethodsCorrectly() {
		City city = this.cityRepository
				.findAll(new PageRequest(0, 1, Direction.ASC, "name")).getContent()
				.get(0);
		assertThat(city.getName()).isEqualTo("Atlanta");

		Page<HotelSummary> hotels = this.repository.findByCity(city,
				new PageRequest(0, 10, Direction.ASC, "name"));
		Hotel hotel = this.repository.findByCityAndName(city,
				hotels.getContent().get(0).getName());
		assertThat(hotel.getName()).isEqualTo("Doubletree");

		List<RatingCount> counts = this.repository.findRatingCounts(hotel);
		assertThat(counts).hasSize(1);
		assertThat(counts.get(0).getRating()).isEqualTo(Rating.AVERAGE);
		assertThat(counts.get(0).getCount()).isGreaterThan(1L);
	}
}
