package fr.mission5.gsb.callbacks;

import java.util.List;

import fr.mission5.gsb.objects.Praticien;

public interface PraticienListCallback {

    void onPraticienListOk(List<Praticien> lesPraticiens);

    void onPraticienListFailed();

}
