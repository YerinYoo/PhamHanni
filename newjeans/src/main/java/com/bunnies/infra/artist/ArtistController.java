package com.bunnies.infra.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ArtistController {

	@Autowired
	ArtistService service;
}
