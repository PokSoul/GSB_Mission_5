package fr.mission5.gsb.callbacks;

import java.util.List;

import fr.mission5.gsb.objects.Mois;

public interface MoisListCallback {

    void onMoisListOk(List<Mois> mois);

    void onMoisListFailed();

}


