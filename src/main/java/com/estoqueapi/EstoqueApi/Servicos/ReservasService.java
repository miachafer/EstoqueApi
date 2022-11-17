package com.estoqueapi.EstoqueApi.Servicos;

import com.estoqueapi.EstoqueApi.Entidades.Reservas;
import com.estoqueapi.EstoqueApi.Repositorios.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReservasService {
    @Autowired
    ReservasRepository reservasRepository;
    public List<Reservas> consultar(){
        return reservasRepository.findAll();
    }

    public Reservas consultarById(Long id){
        Optional<Reservas> obj = reservasRepository.findById(id);
        Reservas res = null;// obj.orElseThrow(()-> new EntityNotFoundException("Reserva não encontrada"));
        try{
            res = obj.get();
        }
        catch (NoSuchElementException e){
            throw new EntityNotFoundException("Reserva não encontrada");
        }
        return res;
    }

    public List<Reservas> consultarByIdItem(Long idItem){
        return reservasRepository.ConsultarByIdItem(idItem);
    }

        /*public List<Reservas> consultarByFinalizada(Boolean finalizada){
        return reservasRepository.consultarByFinalizada(finalizada);
    }*/

    public List<Reservas> findByDataPrevista(boolean finalizada) {
        if (finalizada == true) {
            return reservasRepository.findByDataPrevistaVencidos();
        } else
            return reservasRepository.findByDataPrevistaAVencer();
    }

    @Transactional
    public Reservas salvar(Reservas reservas){
        return reservasRepository.save(reservas);
    }

    public Reservas alterar(Long idreserva, Reservas reservas){
        Reservas res = this.consultarById(idreserva);

        res.setFinalizada(reservas.isFinalizada());
        res.setQuantidadeReserva(reservas.getQuantidadeReserva());
        res.setDataPrevista(reservas.getDataPrevista());
        res.setOrdem(reservas.getOrdem());
        res.setUsuario(reservas.getUsuario());
        res.setItem(reservas.getItem());

        return this.salvar(res);
    }

    @Transactional
    public void excluir(Long idreserva){
        Reservas reservas = this.consultarById(idreserva);
        reservasRepository.delete(reservas);
    }

}
