package com.dio.santander.bankline.api.controller;

import java.util.List;

import com.dio.santander.bankline.api.dto.MovimentacaoDto;
import com.dio.santander.bankline.api.model.Movimentacao;
import com.dio.santander.bankline.api.repository.MovimentacaoRepository;
import com.dio.santander.bankline.api.service.MovimentacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping
    public List<Movimentacao> findAll() {
        return movimentacaoRepository.findAll();
    }

    @PostMapping
    public void save(@RequestBody MovimentacaoDto movimentacaoDto) {
        movimentacaoService.save(movimentacaoDto);
    }
}