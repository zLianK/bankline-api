package com.dio.santander.bankline.api.service;

import java.time.LocalDateTime;

import com.dio.santander.bankline.api.dto.MovimentacaoDto;
import com.dio.santander.bankline.api.model.Correntista;
import com.dio.santander.bankline.api.model.Movimentacao;
import com.dio.santander.bankline.api.model.MovimentacaoTipo;
import com.dio.santander.bankline.api.repository.CorrentistaRepository;
import com.dio.santander.bankline.api.repository.MovimentacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private CorrentistaRepository correntistaRepository;

    public void save(MovimentacaoDto movimentacaoDto) {
        Movimentacao movimentacao = new Movimentacao();

        Double valor = movimentacaoDto.getValor();
        if (movimentacaoDto.getTipo() == MovimentacaoTipo.DESPESA) {
            valor = valor * -1;
        }

        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setDescricao(movimentacaoDto.getDescricao());
        movimentacao.setDescricao(movimentacaoDto.getDescricao());
        movimentacao.setIdConta(movimentacaoDto.getIdConta());
        movimentacao.setTipo(movimentacaoDto.getTipo());
        movimentacao.setValor(valor);

        Correntista correntista = correntistaRepository
                .findById(movimentacaoDto.getIdConta())
                .orElse(null);

        if (correntista != null) {
            correntista.getConta().setSaldo(correntista.getConta().getSaldo() + valor);
            correntistaRepository.save(correntista);
        }

        movimentacaoRepository.save(movimentacao);

    }
}
