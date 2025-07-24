package com.example.api.musicaService;

import com.example.api.entity.Musica;
import com.example.api.musicaRepository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;

    public Musica save(Musica musica) {
        return musicaRepository.save(musica);
    }

    public List<Musica> findAll() {
        return musicaRepository.findAll();
    }

    public Musica update(Long id, Musica nova) {
        return musicaRepository.findById(id).map(m -> {
            m.setNome(nova.getNome());
            m.setArtista(nova.getArtista());
            m.setGenero(nova.getGenero());
            return musicaRepository.save(m);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Música não encontrada"));
    }

    public String deleteById(Long id) {
        if (!musicaRepository.existsById(id)) {
            return "Música nao encontrada";
        }
        musicaRepository.deleteById(id);
        return "Musica deletada";
    }
}
