package com.app.eventos.dao;

import com.app.eventos.model.Evento;
import com.app.eventos.model.Staff;
import com.app.eventos.model.Usuario;

import java.util.HashMap;
import java.util.Map;

public class StaffDAO {
    private Staff staff;

    public StaffDAO() {}

    public void salvarStaff(String idUser, String idEvento) {
        String id = ConfiguracaoFirebase.getDatabaseReference().child("staffs").push().getKey();
        staff = new Staff(id, idUser, idEvento);

        Map<String, Object> staffValues = staff.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/staffs/" + id, staffValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
    }


    public void recuperarListaStaff(){

    }

    public void recuperarStaff(){

    }

    public void deletarStaff(){

    }
}

