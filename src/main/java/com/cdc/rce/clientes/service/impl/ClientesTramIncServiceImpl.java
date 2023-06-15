package com.cdc.rce.clientes.service.impl;

import com.cdc.rce.clientes.beans.Cliente;
import com.cdc.rce.clientes.dao.IClientesTramInconclusoDao;
import com.cdc.rce.clientes.service.IClientesTramIncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesTramIncServiceImpl implements IClientesTramIncService {


    @Autowired
    IClientesTramInconclusoDao tramInconclusoDao;

    @Override
    public List<Cliente> getClientesTramInc() {
        return tramInconclusoDao.getDatosClienteConTramiteInconcluso();
    }
}
