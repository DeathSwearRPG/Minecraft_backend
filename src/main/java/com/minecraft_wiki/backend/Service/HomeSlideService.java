package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.HomeSlide;
import com.minecraft_wiki.backend.Repo.HomeSlideMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HomeSlideService {

    private final HomeSlideMongoRepository homeSlideMongoRepository;

    public List<HomeSlide> getHomeSlides() {
        return homeSlideMongoRepository.findAllByOrderByDisplayOrderAsc();
    }

}
